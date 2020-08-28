package com.github.jarvisframework.tool.core.lang;

/**
 * <p>匹配接口</p>
 *
 * @author Doug Wang
 * @since 1.0, 2020-07-30 11:24:41
 * @param <T> 匹配的对象类型
 */
@FunctionalInterface
public interface Matcher<T>{
    /**
     * 给定对象是否匹配
     * @param t 对象
     * @return 是否匹配
     */
    boolean match(T t);
}
