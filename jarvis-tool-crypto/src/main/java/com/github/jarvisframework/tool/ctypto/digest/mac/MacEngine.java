package com.github.jarvisframework.tool.ctypto.digest.mac;

import com.github.jarvisframework.tool.core.io.IOUtils;

import java.io.InputStream;

/**
 * <p>MAC（Message Authentication Code）算法引擎</p>
 *
 * @author Doug Wang
 * @since 1.0, 2020-07-24 20:08:13
 */
public interface MacEngine {

    /**
     * 生成摘要
     *
     * @param data {@link InputStream} 数据流
     * @param bufferLength 缓存长度，不足1使用 {@link IOUtils#DEFAULT_BUFFER_SIZE} 做为默认值
     * @return 摘要bytes
     */
    byte[] digest(InputStream data, int bufferLength);

    /**
     * 获取MAC算法块大小
     *
     * @return MAC算法块大小
     */
    int getMacLength();

    /**
     * 获取当前算法
     *
     * @return 算法
     */
    String getAlgorithm();
}
