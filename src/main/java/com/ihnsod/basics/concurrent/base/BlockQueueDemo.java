package com.ihnsod.basics.concurrent.base;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author: Ihnsod
 * @create: 2019/02/02 20:46
 **/
public class BlockQueueDemo {


    // ArrayBlockingQueue 有界队列
    private static BlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(8);
    // ArrayBlockingQueue 无界队列 (实际队列大小为Integer.MAX_VALUE)
    private static BlockingQueue<Integer> linkedBlockingDeque = new LinkedBlockingDeque<>();

    private static Random random = new Random();

    @Test
    public void test1() {

    }

    public static void main(String[] args) {

        new Thread(new Customer()).start();
//        TimeUnit.MILLISECONDS.sleep(1);
        new Thread(new Producter()).start();


    }


    static class Producter implements Runnable {

        @Override
        public void run() {
            for (; ; ) {
                // offer方法在当队列已满时会阻塞住此线程 直到队列中的数据被取走
                while (arrayBlockingQueue.offer(random.nextInt(Integer.MAX_VALUE))) {
                    System.err.println("添加成功!!");

                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class Customer implements Runnable {

        @Override
        public void run() {
            try {
                for (; ; ) {
                    //take 方法会阻塞住此线程 直到队列中有元素被取出 此线程结束(死循环中除外)
                    Integer take = arrayBlockingQueue.take();
                    System.err.println(take);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
