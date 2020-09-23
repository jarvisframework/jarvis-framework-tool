package com.github.jarvisframework.tool.core.bean;

import com.github.jarvisframework.tool.core.annotation.AnnotationUtils;
import com.github.jarvisframework.tool.core.annotation.PropIgnore;
import com.github.jarvisframework.tool.core.convert.Convert;
import com.github.jarvisframework.tool.core.util.ClassUtils;
import com.github.jarvisframework.tool.core.util.ModifierUtils;
import com.github.jarvisframework.tool.core.util.ReflectUtils;
import com.github.jarvisframework.tool.core.util.TypeUtils;

import java.beans.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * <p>属性描述，包括了字段、getter、setter和相应的方法执行</p>
 *
 * @author Doug Wang
 * @since 1.0, 2020-09-23 14:51:10
 */
public class PropDesc {

    /**
     * 字段
     */
    final Field field;
    /**
     * Getter方法
     */
    protected Method getter;
    /**
     * Setter方法
     */
    protected Method setter;

    /**
     * 构造<br>
     * Getter和Setter方法设置为默认可访问
     *
     * @param field  字段
     * @param getter get方法
     * @param setter set方法
     */
    public PropDesc(Field field, Method getter, Method setter) {
        this.field = field;
        this.getter = ClassUtils.setAccessible(getter);
        this.setter = ClassUtils.setAccessible(setter);
    }

    /**
     * 获取字段名，如果存在Alias注解，读取注解的值作为名称
     *
     * @return 字段名
     */
    public String getFieldName() {
        return ReflectUtils.getFieldName(this.field);
    }

    /**
     * 获取字段名称
     *
     * @return 字段名
     * @since 5.1.6
     */
    public String getRawFieldName() {
        return null == this.field ? null : this.field.getName();
    }

    /**
     * 获取字段
     *
     * @return 字段
     */
    public Field getField() {
        return this.field;
    }

    /**
     * 获得字段类型<br>
     * 先获取字段的类型，如果字段不存在，则获取Getter方法的返回类型，否则获取Setter的第一个参数类型
     *
     * @return 字段类型
     */
    public Type getFieldType() {
        if (null != this.field) {
            return TypeUtils.getType(this.field);
        }
        return findPropType(getter, setter);
    }

    /**
     * 获得字段类型<br>
     * 先获取字段的类型，如果字段不存在，则获取Getter方法的返回类型，否则获取Setter的第一个参数类型
     *
     * @return 字段类型
     */
    public Class<?> getFieldClass() {
        if (null != this.field) {
            return TypeUtils.getClass(this.field);
        }
        return findPropClass(getter, setter);
    }

    /**
     * 获取Getter方法，可能为{@code null}
     *
     * @return Getter方法
     */
    public Method getGetter() {
        return this.getter;
    }

    /**
     * 获取Setter方法，可能为{@code null}
     *
     * @return {@link Method}Setter 方法对象
     */
    public Method getSetter() {
        return this.setter;
    }

    /**
     * 检查属性是否可读（即是否可以通过{@link #getValue(Object)}获取到值）
     *
     * @param checkTransient 是否检查Transient关键字或注解
     * @return 是否可读
     * @since 5.4.2
     */
    public boolean isReadable(boolean checkTransient) {
        // 检查是否有getter方法或是否为public修饰
        if (null == this.getter && false == ModifierUtils.isPublic(this.field)) {
            return false;
        }

        // 检查transient关键字和@Transient注解
        if (checkTransient && isTransientForGet()) {
            return false;
        }

        // 检查@PropIgnore注解
        return false == isIgnoreGet();
    }

    /**
     * 获取属性值<br>
     * 首先调用字段对应的Getter方法获取值，如果Getter方法不存在，则判断字段如果为public，则直接获取字段值<br>
     * 此方法不检查任何注解，使用前需调用 {@link #isReadable(boolean)} 检查是否可读
     *
     * @param bean Bean对象
     * @return 字段值
     * @since 4.0.5
     */
    public Object getValue(Object bean) {
        if (null != this.getter) {
            return ReflectUtils.invoke(bean, this.getter);
        } else if (ModifierUtils.isPublic(this.field)) {
            return ReflectUtils.getFieldValue(bean, this.field);
        }

        return null;
    }

    /**
     * 获取属性值，自动转换属性值类型<br>
     * 首先调用字段对应的Getter方法获取值，如果Getter方法不存在，则判断字段如果为public，则直接获取字段值
     *
     * @param bean        Bean对象
     * @param targetType  返回属性值需要转换的类型，null表示不转换
     * @param ignoreError 是否忽略错误，包括转换错误和注入错误
     * @return this
     * @since 5.4.2
     */
    public Object getValue(Object bean, Type targetType, boolean ignoreError) {
        Object result = null;
        try {
            result = getValue(bean);
        } catch (Exception e) {
            if (false == ignoreError) {
                throw new BeanException(e, "Get value of [{}] error!", getFieldName());
            }
        }

        if (null != result && null != targetType) {
            // 尝试将结果转换为目标类型，如果转换失败，返回原类型。
            final Object convertValue = Convert.convertWithCheck(targetType, result, null, ignoreError);
            if (null != convertValue) {
                result = convertValue;
            }
        }
        return result;
    }

    /**
     * 检查属性是否可读（即是否可以通过{@link #getValue(Object)}获取到值）
     *
     * @param checkTransient 是否检查Transient关键字或注解
     * @return 是否可读
     * @since 5.4.2
     */
    public boolean isWritable(boolean checkTransient) {
        // 检查是否有getter方法或是否为public修饰
        if (null == this.setter && false == ModifierUtils.isPublic(this.field)) {
            return false;
        }

        // 检查transient关键字和@Transient注解
        if (checkTransient && isTransientForSet()) {
            return false;
        }

        // 检查@PropIgnore注解
        return false == isIgnoreSet();
    }

    /**
     * 设置Bean的字段值<br>
     * 首先调用字段对应的Setter方法，如果Setter方法不存在，则判断字段如果为public，则直接赋值字段值<br>
     * 此方法不检查任何注解，使用前需调用 {@link #isWritable(boolean)} 检查是否可写
     *
     * @param bean  Bean对象
     * @param value 值，必须与字段值类型匹配
     * @return this
     * @since 4.0.5
     */
    public PropDesc setValue(Object bean, Object value) {
        if (null != this.setter) {
            ReflectUtils.invoke(bean, this.setter, value);
        } else if (ModifierUtils.isPublic(this.field)) {
            ReflectUtils.setFieldValue(bean, this.field, value);
        }
        return this;
    }

    /**
     * 设置属性值，可以自动转换字段类型为目标类型
     *
     * @param bean        Bean对象
     * @param value       属性值，可以为任意类型
     * @param ignoreNull  是否忽略{@code null}值，true表示忽略
     * @param ignoreError 是否忽略错误，包括转换错误和注入错误
     * @return this
     * @since 5.4.2
     */
    public PropDesc setValue(Object bean, Object value, boolean ignoreNull, boolean ignoreError) {
        if (ignoreNull && null == value) {
            return this;
        }

        // 当类型不匹配的时候，执行默认转换
        if (null != value) {
            final Class<?> propClass = getFieldClass();
            if (false == propClass.isInstance(value)) {
                value = Convert.convertWithCheck(propClass, value, null, ignoreError);
            }
        }

        // 属性赋值
        if (null != value || false == ignoreNull) {
            try {
                this.setValue(bean, value);
            } catch (Exception e) {
                if (false == ignoreError) {
                    throw new BeanException(e, "Set value of [{}] error!", getFieldName());
                }
                // 忽略注入失败
            }
        }

        return this;
    }

    //------------------------------------------------------------------------------------ Private method start

    /**
     * 通过Getter和Setter方法中找到属性类型
     *
     * @param getter Getter方法
     * @param setter Setter方法
     * @return {@link Type}
     */
    private Type findPropType(Method getter, Method setter) {
        Type type = null;
        if (null != getter) {
            type = TypeUtils.getReturnType(getter);
        }
        if (null == type && null != setter) {
            type = TypeUtils.getParamType(setter, 0);
        }
        return type;
    }

    /**
     * 通过Getter和Setter方法中找到属性类型
     *
     * @param getter Getter方法
     * @param setter Setter方法
     * @return {@link Type}
     */
    private Class<?> findPropClass(Method getter, Method setter) {
        Class<?> type = null;
        if (null != getter) {
            type = TypeUtils.getReturnClass(getter);
        }
        if (null == type && null != setter) {
            type = TypeUtils.getFirstParamClass(setter);
        }
        return type;
    }

    /**
     * 检查字段是否被忽略写，通过{@link PropIgnore} 注解完成，规则为：
     * <pre>
     *     1. 在字段上有{@link PropIgnore} 注解
     *     2. 在setXXX方法上有{@link PropIgnore} 注解
     * </pre>
     *
     * @return 是否忽略写
     * @since 5.4.2
     */
    private boolean isIgnoreSet() {
        return AnnotationUtils.hasAnnotation(this.field, PropIgnore.class)
                || AnnotationUtils.hasAnnotation(this.setter, PropIgnore.class);
    }

    /**
     * 检查字段是否被忽略读，通过{@link PropIgnore} 注解完成，规则为：
     * <pre>
     *     1. 在字段上有{@link PropIgnore} 注解
     *     2. 在getXXX方法上有{@link PropIgnore} 注解
     * </pre>
     *
     * @return 是否忽略读
     * @since 5.4.2
     */
    private boolean isIgnoreGet() {
        return AnnotationUtils.hasAnnotation(this.field, PropIgnore.class)
                || AnnotationUtils.hasAnnotation(this.getter, PropIgnore.class);
    }

    /**
     * 字段和Getter方法是否为Transient关键字修饰的
     *
     * @return 是否为Transient关键字修饰的
     * @since 5.3.11
     */
    private boolean isTransientForGet() {
        boolean isTransient = ModifierUtils.hasModifier(this.field, ModifierUtils.ModifierType.TRANSIENT);

        // 检查Getter方法
        if (false == isTransient && null != this.getter) {
            isTransient = ModifierUtils.hasModifier(this.getter, ModifierUtils.ModifierType.TRANSIENT);

            // 检查注解
            if (false == isTransient) {
                isTransient = AnnotationUtils.hasAnnotation(this.getter, Transient.class);
            }
        }

        return isTransient;
    }

    /**
     * 字段和Getter方法是否为Transient关键字修饰的
     *
     * @return 是否为Transient关键字修饰的
     * @since 5.3.11
     */
    private boolean isTransientForSet() {
        boolean isTransient = ModifierUtils.hasModifier(this.field, ModifierUtils.ModifierType.TRANSIENT);

        // 检查Getter方法
        if (false == isTransient && null != this.setter) {
            isTransient = ModifierUtils.hasModifier(this.setter, ModifierUtils.ModifierType.TRANSIENT);

            // 检查注解
            if (false == isTransient) {
                isTransient = AnnotationUtils.hasAnnotation(this.setter, Transient.class);
            }
        }

        return isTransient;
    }
    //------------------------------------------------------------------------------------ Private method end
}
