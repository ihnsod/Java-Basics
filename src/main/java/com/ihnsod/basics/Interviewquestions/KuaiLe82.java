package com.ihnsod.basics.Interviewquestions;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: Ihnsod
 * @create: 2019/01/24 23:05
 **/
public class KuaiLe82 {
    /**
     * 将快乐8.txt 文件按照规定入库
     * 快乐8.txt文件是爬取北京快乐8的6年历史数据，对每期的数据按照如下进行操作：
     * 北京快乐8每期开奖共开出20个数字，将这20个开奖号码按照由小到大的顺序依次排列；
     * 取其1-6位开奖号码相加，和值的末位数作为开奖第一个数值；
     * 取其7-12位开奖号码相加，和值的末位数作为开奖第二个数值，
     * 取其13-18位开奖号码相加，和值的末位数作为开奖第三个数值
     * ；三个数值相加即为得到最终的结果数。
     * 大小单双是对结果数的描述
     * 结果数大于13为大，反之为小，奇数为单，偶数为双
     */

    private static Connection connection;

    private static String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8&allowMultiQueries=true&rewriteBatchedStatements=true&useSSL=false&serverTimezone=UTC";

    private static String username = "root";

    private static String password = "zxx123456";

    private static int batchSize = 100000;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (Exception e) {

        }
    }

    private static final int sign = 13;

    private File file = new File("G:\\快乐8.txt");

    private LinkedBlockingQueue<InputDb> inputDbs = new LinkedBlockingQueue<>();

    private CountDownLatch count = new CountDownLatch(2);

//    private static ArrayList<InputDb> inputDbs = new ArrayList<>(412147);

    @Test
    public void test() throws InterruptedException {
        long start = System.currentTimeMillis();
        String sql = "INSERT INTO `kuaile8` (`id`, `winning_number`, `big_small`, `even_odd`) VALUES (?,?,?,?)";

        new Thread(new ReadThread()).start();
        TimeUnit.MILLISECONDS.sleep(800);
        new Thread(new InputDbThread(sql)).start();
        count.await();

        long end = System.currentTimeMillis();
        System.err.println(end - start);
    }


    public void readFile() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file)))) {
            String str;
            while ((str = reader.readLine()) != null) {
                processingLogic(str);
            }
            count.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processingLogic(String str) {
        List<Integer> collect = Arrays.asList(str.split(",")).subList(1, 21).stream().
                mapToInt(s -> Integer.parseInt(s)).boxed().sorted().collect(Collectors.toList());

        String oneStr = collect.subList(1, 7).stream().reduce((x, y) -> x + y).get().toString();
        String twoStr = collect.subList(7, 13).stream().reduce((x, y) -> x + y).get().toString();
        String threeStr = collect.subList(13, 19).stream().reduce((x, y) -> x + y).get().toString();

        int one = Integer.parseInt(oneStr.substring(oneStr.length() - 1));
        int two = Integer.parseInt(twoStr.substring(twoStr.length() - 1));
        int three = Integer.parseInt(threeStr.substring(threeStr.length() - 1));

        // 中奖号码
        int result = one + two + three;
        // 大小
        int bigSmall = result > sign ? 1 : 0;
        // 奇偶性
        int evenOdd = result & 1;

        inputDbs.offer(new InputDb().setId(Integer.parseInt(str.split(",")[0]))
                .setBigSmall(bigSmall).setEvenOdd(evenOdd).setWinningNumber(result));

    }

    private void putDb(String sql) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
            while (!inputDbs.isEmpty()) {
                InputDb take = inputDbs.take();
                statement.setInt(1, take.getId());
                statement.setInt(2, take.getWinningNumber());
                statement.setInt(3, take.getBigSmall());
                statement.setInt(4, take.getEvenOdd());
                statement.addBatch();
                index++;
                if (index % batchSize == 0) {
                    statement.executeBatch();
                    connection.commit();
                }
            }
            if ((index + 1) % batchSize != 0) {
                statement.executeBatch();
                connection.commit();
            }
            statement.close();
            connection.close();
            count.countDown();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    class ReadThread implements Runnable {
        @Override
        public void run() {
            readFile();
        }
    }

    class InputDbThread implements Runnable {

        private String sql;

        public InputDbThread(String sql) {
            this.sql = sql;
        }

        @Override
        public void run() {
            putDb(sql);
        }
    }


    @Data
    @ToString
    @Accessors(chain = true)
    class InputDb {
        Integer id;
        Integer winningNumber;
        Integer bigSmall;
        Integer evenOdd;
    }
}
