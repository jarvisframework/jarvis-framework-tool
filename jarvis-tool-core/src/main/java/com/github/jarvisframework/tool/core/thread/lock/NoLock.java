package com.github.jarvisframework.tool.core.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * <p>无锁实现</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-24 17:59:12
 */
public class NoLock implements Lock {

    @Override
    public void lock() {
    }

    @Override
    public void lockInterruptibly() {
    }

    @Override
    public boolean tryLock() {
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        return true;
    }

    @Override
    public void unlock() {
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException("NoLock`s newCondition method is unsupported");
    }

}
