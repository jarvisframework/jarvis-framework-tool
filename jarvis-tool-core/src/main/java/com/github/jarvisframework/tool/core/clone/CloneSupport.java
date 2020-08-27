package com.github.jarvisframework.tool.core.clone;

/**
 * <p>description</p>
 *
 * @param <T> 继承类的类型
 * @author 王涛
 * @since 1.0, 2020-07-29 17:16:39
 */
public class CloneSupport<T> implements Cloneable<T>{

    @SuppressWarnings("unchecked")
    @Override
    public T clone() {
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new CloneException(e);
        }
    }

}
