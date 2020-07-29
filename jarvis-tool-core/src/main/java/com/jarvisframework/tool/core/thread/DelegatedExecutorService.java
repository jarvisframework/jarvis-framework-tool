package com.jarvisframework.tool.core.thread;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * <p>ExecutorService代理</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-29 13:53:07
 */
public class DelegatedExecutorService extends AbstractExecutorService {
    private final ExecutorService e;

    DelegatedExecutorService(ExecutorService executor) {
        e = executor;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void execute(Runnable command) {
        e.execute(command);
    }

    @Override
    public void shutdown() {
        e.shutdown();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public List<Runnable> shutdownNow() {
        return e.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return e.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return e.isTerminated();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return e.awaitTermination(timeout, unit);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Future<?> submit(Runnable task) {
        return e.submit(task);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return e.submit(task);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return e.submit(task, result);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return e.invokeAll(tasks);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException {
        return e.invokeAll(tasks, timeout, unit);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException {
        return e.invokeAny(tasks);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        return e.invokeAny(tasks, timeout, unit);
    }
}
