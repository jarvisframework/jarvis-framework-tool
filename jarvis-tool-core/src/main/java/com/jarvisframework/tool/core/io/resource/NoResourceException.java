package com.jarvisframework.tool.core.io.resource;

import com.jarvisframework.tool.core.exception.ExceptionUtils;
import com.jarvisframework.tool.core.io.IORuntimeException;
import com.jarvisframework.tool.core.util.StringUtils;

/**
 * <p>资源文件或资源不存在异常</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-29 17:41:16
 */
public class NoResourceException extends IORuntimeException {
    private static final long serialVersionUID = -623254467603299129L;

    public NoResourceException(Throwable e) {
        super(ExceptionUtils.getMessage(e), e);
    }

    public NoResourceException(String message) {
        super(message);
    }

    public NoResourceException(String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params));
    }

    public NoResourceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public NoResourceException(Throwable throwable, String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params), throwable);
    }

    /**
     * 导致这个异常的异常是否是指定类型的异常
     *
     * @param clazz 异常类
     * @return 是否为指定类型异常
     */
    @Override
    public boolean causeInstanceOf(Class<? extends Throwable> clazz) {
        final Throwable cause = this.getCause();
        return clazz.isInstance(cause);
    }
}
