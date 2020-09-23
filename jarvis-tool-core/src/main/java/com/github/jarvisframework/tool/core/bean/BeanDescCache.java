package com.github.jarvisframework.tool.core.bean;

import com.github.jarvisframework.tool.core.func.Func0;
import com.github.jarvisframework.tool.core.lang.SimpleCache;

/**
 * Bean属性缓存<br>
 * 缓存用于防止多次反射造成的性能问题
 *
 * @author Doug Wang
 * @since 1.0, 2020-07-29 18:20:24
 */
public enum BeanDescCache {

    INSTANCE;

    private final SimpleCache<Class<?>, BeanDesc> bdCache = new SimpleCache<>();

    /**
     * 获得属性名和{@link BeanDesc}Map映射
     *
     * @param beanClass Bean的类
     * @param supplier  对象不存在时创建对象的函数
     * @return 属性名和{@link BeanDesc}映射
     */
    public BeanDesc getBeanDesc(Class<?> beanClass, Func0<BeanDesc> supplier) {
        return bdCache.get(beanClass, supplier);
    }
}
