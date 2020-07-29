package com.jarvisframework.tool.cache.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * {@link cn.hutool.cache.impl.AbstractCache} 的CacheObj迭代器.
 *
 * @param <K> 键类型
 * @param <V> 值类型
 * @author 王涛
 * @since 1.0, 2020-07-29 11:43:18
 */
public class CacheObjectIterator<K, V> implements Iterator<CacheObject<K, V>>, Serializable {
    private static final long serialVersionUID = 1L;

    private final Iterator<CacheObject<K, V>> iterator;

    private CacheObject<K, V> nextValue;

    /**
     * 构造
     *
     * @param iterator 原{@link Iterator}
     */
    CacheObjectIterator(Iterator<CacheObject<K, V>> iterator) {
        this.iterator = iterator;
        nextValue();
    }

    /**
     * @return 是否有下一个值
     */
    @Override
    public boolean hasNext() {
        return nextValue != null;
    }

    /**
     * @return 下一个值
     */
    @Override
    public CacheObject<K, V> next() {
        if (false == hasNext()) {
            throw new NoSuchElementException();
        }
        final CacheObject<K, V> cachedObject = nextValue;
        nextValue();
        return cachedObject;
    }

    /**
     * 从缓存中移除没有过期的当前值，此方法不支持
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cache values Iterator is not support to modify.");
    }

    /**
     * 下一个值，当不存在则下一个值为null
     */
    private void nextValue() {
        while (iterator.hasNext()) {
            nextValue = iterator.next();
            if (nextValue.isExpired() == false) {
                return;
            }
        }
        nextValue = null;
    }
}
