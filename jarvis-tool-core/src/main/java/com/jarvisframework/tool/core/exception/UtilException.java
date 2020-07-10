package com.jarvisframework.tool.core.exception;

import com.jarvisframework.tool.core.util.StringUtils;

/**
 * <p>工具类的异常类</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-08 17:16:05
 */
public class UtilException extends RuntimeException {

    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable e) {
        super(ExceptionUtils.getMessage(e), e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params));
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UtilException(Throwable throwable, String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params), throwable);
    }
}
