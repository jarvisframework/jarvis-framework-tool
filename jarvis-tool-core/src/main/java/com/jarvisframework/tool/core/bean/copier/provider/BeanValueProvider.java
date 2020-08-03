package com.jarvisframework.tool.core.bean.copier.provider;

import com.jarvisframework.tool.core.bean.BeanDesc.PropDesc;
import com.jarvisframework.tool.core.bean.BeanUtils;
import com.jarvisframework.tool.core.convert.Convert;
import com.jarvisframework.tool.core.exception.UtilException;
import com.jarvisframework.tool.core.bean.copier.ValueProvider;
import com.jarvisframework.tool.core.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * <p>Bean的值提供者</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-29 19:48:03
 */
public class BeanValueProvider implements ValueProvider<String> {

    private final Object source;
    private final boolean ignoreError;
    final Map<String, PropDesc> sourcePdMap;

    /**
     * 构造
     *
     * @param bean        Bean
     * @param ignoreCase  是否忽略字段大小写
     * @param ignoreError 是否忽略字段值读取错误
     */
    public BeanValueProvider(Object bean, boolean ignoreCase, boolean ignoreError) {
        this.source = bean;
        this.ignoreError = ignoreError;
        sourcePdMap = BeanUtils.getBeanDesc(source.getClass()).getPropMap(ignoreCase);
    }

    @Override
    public Object value(String key, Type valueType) {
        PropDesc sourcePd = sourcePdMap.get(key);
        if (null == sourcePd && (Boolean.class == valueType || boolean.class == valueType)) {
            //boolean类型字段字段名支持两种方式
            sourcePd = sourcePdMap.get(StringUtils.upperFirstAndAddPre(key, "is"));
        }

        Object result = null;
        if (null != sourcePd) {
            final Method getter = sourcePd.getGetter();
            if (null != getter) {
                try {
                    result = getter.invoke(source);
                } catch (Exception e) {
                    if (false == ignoreError) {
                        throw new UtilException(e, "Inject [{}] error!", key);
                    }
                }

                // 尝试将结果转换为目标类型，如果转换失败，返回原类型。
                final Object convertValue = Convert.convertWithCheck(valueType, result, null, ignoreError);
                if (null != convertValue) {
                    result = convertValue;
                }
            }
        }
        return result;
    }

    @Override
    public boolean containsKey(String key) {
        return sourcePdMap.containsKey(key) || sourcePdMap.containsKey(StringUtils.upperFirstAndAddPre(key, "is"));
    }

}
