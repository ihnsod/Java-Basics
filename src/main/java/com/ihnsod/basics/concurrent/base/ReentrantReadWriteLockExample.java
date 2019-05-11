package com.ihnsod.basics.concurrent.base;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: Ihnsod
 * @date: 2018/6/17
 */
public class ReentrantReadWriteLockExample {
    /**
     * 读写锁,支持多个线程获取读锁,读锁为共享式锁,只支持一个线程获取写锁,写锁为独占式
     */
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 读锁
    ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    // 写锁
    ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    @Test
    public void structure() {
        // 读锁内部依靠AQS的获取共享式锁的方法进行获取
        readLock.lock();

        readLock.unlock();

        // 写锁内部和ReentrantLock一样使用独占式的方式获取锁
        writeLock.lock();

        writeLock.unlock();
    }

    private volatile boolean update;

    /**
     * 读写锁支持锁降级,即支持先获取写锁再获取读锁在释放写锁
     */
    public void processData() {
        readLock.lock();
        if (!update) {
            readLock.unlock(); // 必须先释放读锁
            writeLock.lock();   // 锁降级从写锁获取到开始
            try {
                if (!update) {
                    // 准备数据的流程（略）
                    update = true;
                }
                readLock.lock();
            } finally {
                writeLock.unlock(); // 锁降级完成，写锁降级为读锁
            }
        }
        try {
            // 使用数据的流程（略）
        } finally {
            readLock.unlock();
        }
    }

}
