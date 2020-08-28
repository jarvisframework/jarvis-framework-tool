package com.github.jarvisframework.tool.core.lang.copier;

/**
 * <p>拷贝接口</p>
 *
 * @param <T> 拷贝目标类型
 * @author Doug Wang
 * @since 1.0, 2020-07-29 19:41:55
 */
@FunctionalInterface
public interface Copier<T> {

    /**
     * 执行拷贝
     *
     * @return 拷贝的目标
     */
    T copy();
}
