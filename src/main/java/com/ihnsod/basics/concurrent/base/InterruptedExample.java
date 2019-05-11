package com.ihnsod.basics.concurrent.base;

/**
 * @author: Ihnsod
 * @create: 2018/12/04 09:19
 **/
public class InterruptedExample {

    /**
     * 如果一个线程的 run() 方法执行一个无限循环，并且没有执行 sleep() 等会抛出 InterruptedException 的操作，
     * 那么调用线程的 interrupt() 方法就无法使线程提前结束。
     *
     * 但是调用 interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true。
     * 因此可以在循环体中使用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程。
     */

    private static class MyThread2 extends Thread {
        @Override
        public void run() {
            while (!interrupted()) {
                System.err.println("Thread run");
            }
            System.out.println("Thread end");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread2 = new MyThread2();
        thread2.start();
        Thread.sleep(1);
        thread2.interrupt();
    }
}
