package com.jarvisframework.tool.core.io;

/**
 * <p>Stream进度条</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-30 11:46:50
 */
public interface StreamProgress {

    /**
     * 开始
     */
    void start();

    /**
     * 进行中
     *
     * @param progressSize 已经进行的大小
     */
    void progress(long progressSize);

    /**
     * 结束
     */
    void finish();
}
