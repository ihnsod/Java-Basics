package com.ihnsod.basics.concurrent.base;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.stream.IntStream;

/**
 * @author: Ihnsod
 * @create: 2019/05/07 23:04
 **/
public class AtomicReferenceExample {

    public static AtomicReference<User> atomicUserRef = new AtomicReference<>();

    // 构造方法 传入引用和初始戳
    public static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(0, 0);

    @Test
    public void test1() {
        User user = new User("conan", 15);
        atomicUserRef.set(user);
        User updateUser = new User("Shinichi", 17);
        atomicUserRef.compareAndSet(user, updateUser);
        System.out.println(atomicUserRef.get().getName());
        System.out.println(atomicUserRef.get().getOld());
    }

    @Test
    public void test2() throws InterruptedException {
        IntStream.range(0, 1000).forEach(value -> {
            final int stamp = stampedReference.getStamp();
            new Thread(() -> {
                // 注意要使用Integer接受而不是int类型
                Integer reference = stampedReference.getReference();
                for (; ; ) {
                    if (stampedReference.compareAndSet(reference, reference + 1, stamp, stamp + 1)) {
                        break;
                    }
                }
            }).run();
        });
        TimeUnit.SECONDS.sleep(3);
        System.out.println(stampedReference.getReference());
        System.out.println(stampedReference.getStamp());
    }

    static class User {
        private String name;
        private int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }
}
