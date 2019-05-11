package com.ihnsod.basics.basic;

/**
 * @author Ihnsod
 * @create 2018/07/02
 **/
public class ThreadLocalDemo {

    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static  int value = 0;

    public static class ThreadLocalThread implements Runnable {
        @Override
        public void run() {
            threadLocal.set((int) (Math.random() * 100));
            value = (int) (Math.random() * 100);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf(Thread.currentThread().getName() + ": threadLocal=%d, value=%d\n", threadLocal.get(), value);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadLocalThread());
        Thread thread2 = new Thread(new ThreadLocalThread());
        Thread thread3 = new Thread(new ThreadLocalThread());
        Thread thread4 = new Thread(new ThreadLocalThread());
        Thread thread5 = new Thread(new ThreadLocalThread());
        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread.join();
        thread2.join();
    }

}
