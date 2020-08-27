package com.github.jarvisframework.tool.core.thread;

import java.util.concurrent.ExecutorService;

/**
 * <p> 保证ExecutorService在对象回收时正常结束 </p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-29 13:50:49
 */
public class FinalizableDelegatedExecutorService extends DelegatedExecutorService {
    FinalizableDelegatedExecutorService(ExecutorService executor) {
        super(executor);
    }

    @Override
    protected void finalize() {
        super.shutdown();
    }
}
