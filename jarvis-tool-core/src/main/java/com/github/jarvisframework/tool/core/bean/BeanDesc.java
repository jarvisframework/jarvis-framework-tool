package com.github.jarvisframework.tool.core.bean;

import com.github.jarvisframework.tool.core.lang.Assert;
import com.github.jarvisframework.tool.core.map.CaseInsensitiveMap;
import com.github.jarvisframework.tool.core.util.BooleanUtils;
import com.github.jarvisframework.tool.core.util.ModifierUtils;
import com.github.jarvisframework.tool.core.util.ReflectUtils;
import com.github.jarvisframework.tool.core.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Bean信息描述做为BeanInfo替代方案，此对象持有JavaBean中的setters和getters等相关信息描述<br>
 * 查找Getter和Setter方法时会：
 *
 * <pre>
 * 1. 忽略字段和方法名的大小写
 * 2. Getter查找getXXX、isXXX、getIsXXX
 * 3. Setter查找setXXX、setIsXXX
 * 4. Setter忽略参数值与字段值不匹配的情况，因此有多个参数类型的重载时，会调用首次匹配的
 * </pre>
 *
 * @author Doug Wang
 * @since 1.0, 2020-07-29 18:11:13
 */
public class BeanDesc implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Bean类
     */
    private final Class<?> beanClass;
    /**
     * 属性Map
     */
    private final Map<String, PropDesc> propMap = new LinkedHashMap<>();

    /**
     * 构造
     *
     * @param beanClass Bean类
     */
    public BeanDesc(Class<?> beanClass) {
        Assert.notNull(beanClass);
        this.beanClass = beanClass;
        init();
    }

    /**
     * 获取Bean的全类名
     *
     * @return Bean的类名
     */
    public String getName() {
        return this.beanClass.getName();
    }

    /**
     * 获取Bean的简单类名
     *
     * @return Bean的类名
     */
    public String getSimpleName() {
        return this.beanClass.getSimpleName();
    }

    /**
     * 获取字段名-字段属性Map
     *
     * @param ignoreCase 是否忽略大小写，true为忽略，false不忽略
     * @return 字段名-字段属性Map
     */
    public Map<String, PropDesc> getPropMap(boolean ignoreCase) {
        return ignoreCase ? new CaseInsensitiveMap<>(1, this.propMap) : this.propMap;
    }

    /**
     * 获取字段属性列表
     *
     * @return {@link PropDesc} 列表
     */
    public Collection<PropDesc> getProps() {
        return this.propMap.values();
    }

    /**
     * 获取属性，如果不存在返回null
     *
     * @param fieldName 字段名
     * @return {@link PropDesc}
     */
    public PropDesc getProp(String fieldName) {
        return this.propMap.get(fieldName);
    }

    /**
     * 获得字段名对应的字段对象，如果不存在返回null
     *
     * @param fieldName 字段名
     * @return 字段值
     */
    public Field getField(String fieldName) {
        final PropDesc desc = this.propMap.get(fieldName);
        return null == desc ? null : desc.getField();
    }

    /**
     * 获取Getter方法，如果不存在返回null
     *
     * @param fieldName 字段名
     * @return Getter方法
     */
    public Method getGetter(String fieldName) {
        final PropDesc desc = this.propMap.get(fieldName);
        return null == desc ? null : desc.getGetter();
    }

    /**
     * 获取Setter方法，如果不存在返回null
     *
     * @param fieldName 字段名
     * @return Setter方法
     */
    public Method getSetter(String fieldName) {
        final PropDesc desc = this.propMap.get(fieldName);
        return null == desc ? null : desc.getSetter();
    }

    // ------------------------------------------------------------------------------------------------------ Private method start

    /**
     * 初始化<br>
     * 只有与属性关联的相关Getter和Setter方法才会被读取，无关的getXXX和setXXX都被忽略
     *
     * @return this
     */
    private BeanDesc init() {
        final Method[] methods = ReflectUtils.getMethods(this.beanClass);
        PropDesc prop;
        for (Field field : ReflectUtils.getFields(this.beanClass)) {
            if (false == ModifierUtils.isStatic(field)) {
                //只针对非static属性
                prop = createProp(field, methods);
                this.propMap.put(prop.getFieldName(), prop);
            }
        }
        return this;
    }

    /**
     * 根据字段创建属性描述<br>
     * 查找Getter和Setter方法时会：
     *
     * <pre>
     * 1. 忽略字段和方法名的大小写
     * 2. Getter查找getXXX、isXXX、getIsXXX
     * 3. Setter查找setXXX、setIsXXX
     * 4. Setter忽略参数值与字段值不匹配的情况，因此有多个参数类型的重载时，会调用首次匹配的
     * </pre>
     *
     * @param field   字段
     * @param methods 类中所有的方法
     * @return {@link PropDesc}
     * @since 4.0.2
     */
    private PropDesc createProp(Field field, Method[] methods) {
        final PropDesc prop = findProp(field, methods, false);
        // 忽略大小写重新匹配一次
        if (null == prop.getter || null == prop.setter) {
            final PropDesc propIgnoreCase = findProp(field, methods, true);
            if (null == prop.getter) {
                prop.getter = propIgnoreCase.getter;
            }
            if (null == prop.setter) {
                prop.setter = propIgnoreCase.setter;
            }
        }

        return prop;
    }

    /**
     * 查找字段对应的Getter和Setter方法
     *
     * @param field      字段
     * @param methods    类中所有的方法
     * @param ignoreCase 是否忽略大小写匹配
     * @return PropDesc
     */
    private PropDesc findProp(Field field, Method[] methods, boolean ignoreCase) {
        final String fieldName = field.getName();
        final Class<?> fieldType = field.getType();
        final boolean isBooleanField = BooleanUtils.isBoolean(fieldType);

        Method getter = null;
        Method setter = null;
        String methodName;
        Class<?>[] parameterTypes;
        for (Method method : methods) {
            parameterTypes = method.getParameterTypes();
            if (parameterTypes.length > 1) {
                // 多于1个参数说明非Getter或Setter
                continue;
            }

            methodName = method.getName();
            if (parameterTypes.length == 0) {
                // 无参数，可能为Getter方法
                if (isMatchGetter(methodName, fieldName, isBooleanField, ignoreCase)) {
                    // 方法名与字段名匹配，则为Getter方法
                    getter = method;
                }
            } else if (isMatchSetter(methodName, fieldName, isBooleanField, ignoreCase)) {
                // 只有一个参数的情况下方法名与字段名对应匹配，则为Setter方法
                setter = method;
            }
            if (null != getter && null != setter) {
                // 如果Getter和Setter方法都找到了，不再继续寻找
                break;
            }
        }
        return new PropDesc(field, getter, setter);
    }

    /**
     * 方法是否为Getter方法<br>
     * 匹配规则如下（忽略大小写）：
     *
     * <pre>
     * 字段名    -》 方法名
     * isName  -》 isName
     * isName  -》 isIsName
     * isName  -》 getIsName
     * name     -》 isName
     * name     -》 getName
     * </pre>
     *
     * @param methodName     方法名
     * @param fieldName      字段名
     * @param isBooleanField 是否为Boolean类型字段
     * @param ignoreCase     匹配是否忽略大小写
     * @return 是否匹配
     */
    private boolean isMatchGetter(String methodName, String fieldName, boolean isBooleanField, boolean ignoreCase) {
        // 全部转为小写，忽略大小写比较
        if (ignoreCase) {
            methodName = methodName.toLowerCase();
            fieldName = fieldName.toLowerCase();
        } else {
            fieldName = StringUtils.upperFirst(fieldName);
        }

        if (false == methodName.startsWith("get") && false == methodName.startsWith("is")) {
            // 非标准Getter方法
            return false;
        }
        if ("getclass".equals(methodName)) {
            //跳过getClass方法
            return false;
        }

        // 针对Boolean类型特殊检查
        if (isBooleanField) {
            if (fieldName.startsWith("is")) {
                // 字段已经是is开头
                if (methodName.equals(fieldName)
                        || methodName.equals("get" + fieldName)
                        || methodName.equals("is" + fieldName)
                ) {
                    return true;
                }
            } else if (methodName.equals("is" + fieldName)) {
                // 字段非is开头， name -》 isName
                return true;
            }
        }

        // 包括boolean的任何类型只有一种匹配情况：name -》 getName
        return methodName.equals("get" + fieldName);
    }

    /**
     * 方法是否为Setter方法<br>
     * 匹配规则如下（忽略大小写）：
     *
     * <pre>
     * 字段名    -》 方法名
     * isName  -》 setName
     * isName  -》 setIsName
     * name     -》 setName
     * </pre>
     *
     * @param methodName     方法名
     * @param fieldName      字段名
     * @param isBooleanField 是否为Boolean类型字段
     * @param ignoreCase     匹配是否忽略大小写
     * @return 是否匹配
     */
    private boolean isMatchSetter(String methodName, String fieldName, boolean isBooleanField, boolean ignoreCase) {
        // 全部转为小写，忽略大小写比较
        methodName = methodName.toLowerCase();
        fieldName = fieldName.toLowerCase();

        // 非标准Setter方法跳过
        if (false == methodName.startsWith("set")) {
            return false;
        }

        // 针对Boolean类型特殊检查
        if (isBooleanField && fieldName.startsWith("is")) {
            // 字段是is开头
            if (methodName.equals("set" + StringUtils.removePrefix(fieldName, "is"))
                    || methodName.equals("set" + fieldName)
            ) {
                return true;
            }
        }

        // 包括boolean的任何类型只有一种匹配情况：name -》 setName
        return methodName.equals("set" + fieldName);
    }
    // ------------------------------------------------------------------------------------------------------ Private method end
}
