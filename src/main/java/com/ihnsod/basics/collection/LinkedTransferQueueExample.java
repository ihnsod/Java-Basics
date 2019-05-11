package com.ihnsod.basics.collection;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author: Ihnsod
 * @create: 2019/05/02 11:00
 **/
public class LinkedTransferQueueExample {

    LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

    class Producer implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            for (int i = 0; i < 2; i++) {
                try {
                    System.out.println("Producer waiting to transfer: " + i);
                    queue.transfer("" + i);
                    System.out.println("Producer transfered: " + i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            for (int i = 0; i < 2; i++) {
                try {
                    Thread.sleep(2000);
                    System.out.println("Consumer waiting to comsume: " + i);
                    queue.take();
                    System.out.println("Consumer consumed: " + i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]) {
        LinkedTransferQueueExample example = new LinkedTransferQueueExample();
        new Thread(example.new Producer()).start();
        new Thread(example.new Consumer()).start();
    }

}
