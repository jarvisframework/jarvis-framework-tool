package com.jarvisframework.tool.core.lang;

/**
 * <p>过滤器接口</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-29 17:37:57
 */
@FunctionalInterface
public interface Filter<T> {
    /**
     * 是否接受对象
     *
     * @param t 检查的对象
     * @return 是否接受对象
     */
    boolean accept(T t);
}
