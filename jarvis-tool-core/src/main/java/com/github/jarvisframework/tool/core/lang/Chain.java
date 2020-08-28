package com.github.jarvisframework.tool.core.lang;

/**
 * <p>责任链接口</p>
 *
 * @param <E> 元素类型
 * @param <T> 目标类类型，用于返回this对象
 * @author Doug Wang
 * @since 1.0, 2020-08-03 15:50:39
 */
public interface Chain<E, T> extends Iterable<E> {
    /**
     * 加入责任链
     *
     * @param element 责任链新的环节元素
     * @return this
     */
    T addChain(E element);
}
