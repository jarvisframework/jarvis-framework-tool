package com.github.jarvisframework.tool.cache.impl;

import java.io.Serializable;
import java.util.Iterator;

/**
 *  {@link AbstractCache} 的值迭代器.
 *
 * @author 王涛
 * @since 1.0, 2020-07-29 11:49:01
 */
public class CacheValuesIterator <V> implements Iterator<V>, Serializable {
    private static final long serialVersionUID = 1L;

    private final CacheObjectIterator<?, V> cacheObjIter;

    /**
     * 构造
     * @param iterator 原{@link CacheObjectIterator}
     */
    CacheValuesIterator(CacheObjectIterator<?, V> iterator) {
        this.cacheObjIter = iterator;
    }

    /**
     * @return 是否有下一个值
     */
    @Override
    public boolean hasNext() {
        return this.cacheObjIter.hasNext();
    }

    /**
     * @return 下一个值
     */
    @Override
    public V next() {
        return cacheObjIter.next().getValue();
    }

    /**
     * 从缓存中移除没有过期的当前值，不支持此方法
     */
    @Override
    public void remove() {
        cacheObjIter.remove();
    }
}
