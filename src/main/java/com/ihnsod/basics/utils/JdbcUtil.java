package com.ihnsod.basics.utils;


import com.ihnsod.basics.pojo.TestInsert;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author: Ihnsod
 * @create: 2019/01/19 13:22
 **/
@Slf4j
public class JdbcUtil {

    private static int len = 100;

    private static Map<Connection, PreparedStatement> map = new ConcurrentHashMap<>(16);

    private static List<Connection> connectionList = new ArrayList<>();

    private static Connection connection;

    private static CountDownLatch downLatch = new CountDownLatch(80);

    private static int batchSize = 100000;

    private static String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8&allowMultiQueries=true&rewriteBatchedStatements=true&useSSL=false&serverTimezone=UTC";

    private static String username = "root";

    private static String password = "zxx123456";


    static {
        try {
            for (int i = 0; i < 10; i++) {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
                connectionList.add(connection);
            }
        } catch (Exception e) {

        }
    }

    // jdbc + 事务 + 多线程 + 数据库连接池 百万数据7秒左右入库 千万数据数据入库 68.3秒
    @Test
    public void testInsert() throws InterruptedException {
        String sql = "insert into test_insert (name,age,sex,address) values (?,?,?,?)";
        int processors = Runtime.getRuntime().availableProcessors();

        ExecutorService executorService = Executors.newFixedThreadPool(processors);

        List<List<TestInsert>> segments = segmentation(createData(), processors);

        System.err.println("开始入库!!");
        long start = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < segments.size(); j++) {
                executorService.execute(new InsertRunnable(connectionList.get(j), sql, segments.get(j)));
            }
//        }
        downLatch.await();
        long end = System.currentTimeMillis();
        System.err.println("完成了入库!!,总花费时长" + (end - start) + "毫秒");
        closeConnection();
    }

    private void closeConnection() {
        map.entrySet().forEach(map -> {
            PreparedStatement value = map.getValue();
            Connection key = map.getKey();

            if (key != null) {
                try {
                    key.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (value != null) {
                try {
                    value.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<List<TestInsert>> segmentation(List<TestInsert> inserts, int processors) {
        Limit<TestInsert> limit = new Limit(inserts, processors);
        List<List<TestInsert>> lists = new ArrayList<>();
        while (limit.hasLimit()) {
            lists.add(limit.getMore());
        }
        return lists;
    }

    private void insert(List<TestInsert> inserts, String sql, Connection conn) {
        long start = System.currentTimeMillis();
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            map.put(conn, ps);

            int index = 1;
            for (TestInsert insert : inserts) {
                ps.setString(1, insert.getName());
                ps.setInt(2, insert.getAge());
                ps.setInt(3, insert.getSex());
                ps.setString(4, insert.getAddress());
                // 放到batch中
                ps.addBatch();
                if (index % batchSize == 0) {
                    ps.executeBatch();
                    conn.commit();
                    System.err.println(index + ":添加" + batchSize + "条");
                    ResultSet keys = ps.getGeneratedKeys();
                }
            }
            if ((index + 1) % batchSize != 0) {
                ps.executeBatch();
                conn.commit();
            }
            long end = System.currentTimeMillis();
            System.err.println(inserts.size() + "条数据入库耗时" + (end - start) + "毫秒");
            downLatch.countDown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private List<TestInsert> createData() {
        List<TestInsert> list = new ArrayList<>(len);
        IntStream.range(0, len).forEach(value -> {
            list.add(new TestInsert().setName("name" + value)
                    .setAge(value).setSex(value % 2).setAddress("address" + value));
        });
        return list;
    }

    class InsertRunnable implements Runnable {

        private Connection connection;
        private String sql;
        private List<TestInsert> inserts;

        public InsertRunnable(Connection connection, String sql, List<TestInsert> inserts) {
            this.inserts = inserts;
            this.sql = sql;
            this.connection = connection;
        }

        @Override
        public void run() {
            insert(inserts, sql, connection);
        }
    }

}
