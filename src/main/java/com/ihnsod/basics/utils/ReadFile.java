package com.ihnsod.basics.utils;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author: Ihnsod
 * @create: 2019/01/20 12:49
 **/
public class ReadFile {

    CountDownLatch downLatch = new CountDownLatch(8);

    @Test
    public void testRead() {

//        int processors = Runtime.getRuntime().availableProcessors();
//
//        ExecutorService service = Executors.newFixedThreadPool(processors);
//
//        for (int i = 0; i < processors; i++) {
//            service.execute(new ReadFileRunnable());
//        }
//        downLatch.wait();
//        System.err.println("读取完毕");

//        readFile();
        readFileChannel();

    }

    private void readFile() {

        long start = System.currentTimeMillis();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader
                (new FileInputStream(new File("C:\\Users\\Ihnsod\\Desktop\\test_insert.txt"))), 1024 * 1024)) {
            while (reader.readLine() != null) {
                String s = reader.readLine();
//                System.err.println(s);
            }

            System.err.println("读取大小为500M文件花费时间" + (System.currentTimeMillis() - start));

//            downLatch.countD+own();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFileChannel() {
        File file = new File("C:\\Users\\Ihnsod\\Desktop\\test_insert.txt");
        long len = file.length();
        byte[] bytes = new byte[(int) len];
        long start = System.currentTimeMillis();
        try {
            MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "rw")
                    .getChannel().map(FileChannel.MapMode.READ_ONLY, 0, len);
            for (int offset = 0; offset < len; offset++) {
                byte b = mappedByteBuffer.get();
                bytes[offset] = b;
            }

            System.err.println("500M数据花费时间" + (System.currentTimeMillis() - start));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    class ReadFileRunnable implements Runnable {
        @Override
        public void run() {
            readFile();
        }
    }

    @Test
    public void test() {
        File file = new File("C:\\Users\\Ihnsod\\Desktop\\test_insert.txt");
        System.err.println(file.length());
    }

    @Test
    public void test1() {

        long start = System.currentTimeMillis();

        try (BufferedInputStream inputStream = new BufferedInputStream(
                new FileInputStream(new File("C:\\Users\\Ihnsod\\Desktop\\test_insert.txt")))) {

            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File("C:\\Users\\Ihnsod\\Desktop\\test_insert_copy.txt")));

            IOUtils.copy(inputStream, outputStream);

            // 3.4s
            System.err.println("复制500M文件花费时间：" + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2() {

        long start = System.currentTimeMillis();

        try {

            File file = new File("C:\\Users\\Ihnsod\\Desktop\\test_insert.txt");

            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[1024 * 1024];

//            List<String> list = FileUtils.readLines(file, "utf-8");

//            IOUtils.read(inputStream, bytes);

            IOUtils.readLines(inputStream, "gbk");

            // 3.4s
            System.err.println("读取500M文件花费时间：" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void copyFile() throws IOException {
        long start = System.currentTimeMillis();
        File source = new File("C:\\Users\\Ihnsod\\Desktop\\test_insert.txt");
        File dest = new File("C:\\Users\\Ihnsod\\Desktop\\test_insert_1.txt");
        FileChannel in = null, out = null;
        try {
            in = new FileInputStream(source).getChannel();
            out = new FileOutputStream(dest).getChannel();
            long size = in.size();
            MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, 0, size);
            out.write(buf);
            in.close();
            out.close();
//            source.delete();//文件复制完成后，删除源文件
            System.err.println("使用内存映射复制500M大小的文件花费时间" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
        }
    }


    @Test
    public void test3() throws Exception {
        ByteBuffer byteBuf = ByteBuffer.allocate(1024 * 14 * 1024);
        byte[] bbb = new byte[14 * 1024 * 1024];
        FileInputStream fis = new FileInputStream("C:\\Users\\Ihnsod\\Desktop\\test_insert.txt");
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Ihnsod\\Desktop\\test_insert_3.txt");
        FileChannel fc = fis.getChannel();
        long timeStar = System.currentTimeMillis();// 得到当前的时间
        fc.read(byteBuf);// 1 读取
        //MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
        System.out.println(fc.size() / 1024);
        long timeEnd = System.currentTimeMillis();// 得到当前的时间
        System.out.println("Read time :" + (timeEnd - timeStar) + "ms");
        timeStar = System.currentTimeMillis();
        fos.write(bbb);//2.写入
        //mbb.flip();
        timeEnd = System.currentTimeMillis();
        System.out.println("Write time :" + (timeEnd - timeStar) + "ms");
        fos.flush();
        fc.close();
        fis.close();

    }
}
