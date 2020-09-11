package com.github.jarvisframework.tool.core.bean.copier.provider;

import com.github.jarvisframework.tool.core.bean.DynaBean;
import com.github.jarvisframework.tool.core.bean.copier.ValueProvider;
import com.github.jarvisframework.tool.core.convert.Convert;

import java.lang.reflect.Type;

/**
 * <p>DynaBean值提供者</p>
 *
 * @author 王涛
 * @since 1.0, 2020-09-11 16:40:49
 */
public class DynaBeanValueProvider implements ValueProvider<String> {

    private final DynaBean dynaBean;

    private final boolean ignoreError;

    /**
     * 构造
     *
     * @param dynaBean    DynaBean
     * @param ignoreError 是否忽略错误
     */
    public DynaBeanValueProvider(DynaBean dynaBean, boolean ignoreError) {
        this.dynaBean = dynaBean;
        this.ignoreError = ignoreError;
    }

    @Override
    public Object value(String key, Type valueType) {
        final Object value = dynaBean.get(key);
        return Convert.convertWithCheck(valueType, value, null, this.ignoreError);
    }

    @Override
    public boolean containsKey(String key) {
        return dynaBean.containsProp(key);
    }

}
