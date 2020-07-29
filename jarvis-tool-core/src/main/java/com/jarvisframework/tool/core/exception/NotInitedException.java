package com.jarvisframework.tool.core.exception;

import com.jarvisframework.tool.core.util.StringUtils;

/**
 * <p>未初始化异常</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-29 14:12:19
 */
public class NotInitedException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    public NotInitedException(Throwable e) {
        super(e);
    }

    public NotInitedException(String message) {
        super(message);
    }

    public NotInitedException(String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params));
    }

    public NotInitedException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public NotInitedException(Throwable throwable, String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params), throwable);
    }
}
