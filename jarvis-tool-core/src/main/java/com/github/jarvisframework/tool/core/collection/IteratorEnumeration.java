package com.github.jarvisframework.tool.core.collection;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * <p>{@link Iterator}对象转{@link Enumeration}</p>
 *
 * @param <E> 元素类型
 * @author 王涛
 * @since 1.0, 2020-07-30 11:35:04
 */
public class IteratorEnumeration<E> implements Enumeration<E>, Serializable {

    private static final long serialVersionUID = 1L;

    private final Iterator<E> iterator;

    /**
     * 构造
     *
     * @param iterator {@link Iterator}对象
     */
    public IteratorEnumeration(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    @Override
    public E nextElement() {
        return iterator.next();
    }

}
