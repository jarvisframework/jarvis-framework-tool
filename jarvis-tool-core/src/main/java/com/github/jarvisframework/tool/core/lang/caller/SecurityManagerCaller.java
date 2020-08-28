package com.github.jarvisframework.tool.core.lang.caller;

import com.github.jarvisframework.tool.core.util.ArrayUtils;

import java.io.Serializable;

/**
 * <p>{@link SecurityManager} 方式获取调用者</p>
 *
 * @author Doug Wang
 * @since 1.0, 2020-08-03 16:33:32
 */
public class SecurityManagerCaller extends SecurityManager implements Caller, Serializable {
    private static final long serialVersionUID = 1L;

    private static final int OFFSET = 1;

    @Override
    public Class<?> getCaller() {
        final Class<?>[] context = getClassContext();
        if (null != context && (OFFSET + 1) < context.length) {
            return context[OFFSET + 1];
        }
        return null;
    }

    @Override
    public Class<?> getCallerCaller() {
        final Class<?>[] context = getClassContext();
        if (null != context && (OFFSET + 2) < context.length) {
            return context[OFFSET + 2];
        }
        return null;
    }

    @Override
    public Class<?> getCaller(int depth) {
        final Class<?>[] context = getClassContext();
        if (null != context && (OFFSET + depth) < context.length) {
            return context[OFFSET + depth];
        }
        return null;
    }

    @Override
    public boolean isCalledBy(Class<?> clazz) {
        final Class<?>[] classes = getClassContext();
        if(ArrayUtils.isNotEmpty(classes)) {
            for (Class<?> contextClass : classes) {
                if (contextClass.equals(clazz)) {
                    return true;
                }
            }
        }
        return false;
    }
}
