package com.github.jarvisframework.tool.core.io;

import com.github.jarvisframework.tool.core.exception.ExceptionUtils;
import com.github.jarvisframework.tool.core.util.StringUtils;

/**
 * <p>IO运行时异常，常用于对IOException的包装</p>
 *
 * @author Doug Wang
 * @since 1.0, 2020-07-13 13:39:30
 */
public class IORuntimeException extends RuntimeException {

    public IORuntimeException(Throwable e) {
        super(ExceptionUtils.getMessage(e), e);
    }

    public IORuntimeException(String message) {
        super(message);
    }

    public IORuntimeException(String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params));
    }

    public IORuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public IORuntimeException(Throwable throwable, String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params), throwable);
    }

    /**
     * 导致这个异常的异常是否是指定类型的异常
     *
     * @param clazz 异常类
     * @return 是否为指定类型异常
     */
    public boolean causeInstanceOf(Class<? extends Throwable> clazz) {
        final Throwable cause = this.getCause();
        return null != clazz && clazz.isInstance(cause);
    }

}
