package com.ihnsod.basics.concurrent.base;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author: Ihnsod
 * @create: 2019/05/04 23:33
 * @desc 自定义同步组件设计一个同步工具：该工具在同一时刻，只允许至多两个线程同时访问，超过两个线程的
 * 访问将被阻塞，我们将这个同步工具命名为TwinsLock。
 **/
public class TwinsLockExample implements Lock {

    private static final Sync sync = new Sync(3);

    private static final class Sync extends AbstractQueuedSynchronizer {

        private Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large zero !");
            }
            // 设置状态
            setState(count);
        }

        @Override
        protected boolean tryAcquire(int arg) {
            return super.tryAcquire(arg);
        }

        @Override
        protected boolean tryRelease(int arg) {
            return super.tryRelease(arg);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            // 共享式获取锁
            for (; ; ) {
                // 获取状态
                int current = getState();
                // 新状态
                int newState = current - arg;
                // 如果新状态小于0(直接返回,此线程会阻塞) 或者是cas原子更新成功则返回状态值
                if (newState < 0 || compareAndSetState(current, newState)) {
                    return newState;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                // 获取状态
                int current = getState();
                // 新的状态值
                int newCount = current + arg;
                // cas更新
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }

        @Override
        protected boolean isHeldExclusively() {
            return super.isHeldExclusively();
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

