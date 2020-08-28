package com.github.jarvisframework.tool.core.clone;

/**
 * <p>克隆支持接口</p>
 *
 * @param <T> 实现克隆接口的类型
 * @author Doug Wang
 * @since 1.0, 2020-07-29 17:15:31
 */
public interface Cloneable<T> extends java.lang.Cloneable {

    /**
     * 克隆当前对象，浅复制
     *
     * @return 克隆后的对象
     */
    T clone();
}
