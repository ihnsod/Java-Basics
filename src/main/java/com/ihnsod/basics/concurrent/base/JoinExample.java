package com.ihnsod.basics.concurrent.base;

/**
 * @author: Ihnsod
 * @create: 2018/12/04 10:12
 **/
public class JoinExample {
    private class A extends Thread {
        @Override
        public void run() {
            System.out.println("A");
            throw new RuntimeException();
        }
    }

    private class B extends Thread {

        private A a;

        B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                a.join();  // a 调用join时 B类线程会等待  A线程执行完再往下执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    }

    public void test() {
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
    }

    public static void main(String[] args) {
        JoinExample example = new JoinExample();
        example.test();
    }
}
