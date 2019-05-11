package com.ihnsod.basics.concurrent.base;

/**
 * @author: Ihnsod
 * @create: 2018/12/04 09:13
 **/
public class InterruptExample {

    /**
     * 通过调用一个线程的 interrupt() 来中断该线程，如果该线程处于阻塞(lock)、限期等待(sleep)或者无限期等待(wait)状态，
     * 那么就会抛出 InterruptedException，从而提前结束该线程。但是不能中断 I/O 阻塞和 synchronized 锁阻塞。
     *
     * 对于以下代码，在 main() 中启动一个线程之后再中断它，由于线程中调用了 Thread.sleep() 方法，
     * 因此会抛出一个 InterruptedException，从而提前结束线程，不执行之后的语句。
     */

    static class MyThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                System.err.println("Thread run...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new MyThread();
        thread.start();
        thread.interrupt();
        System.err.println("Main run...");
    }
}
