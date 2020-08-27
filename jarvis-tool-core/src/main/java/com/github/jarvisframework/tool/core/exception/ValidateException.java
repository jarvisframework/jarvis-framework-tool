package com.github.jarvisframework.tool.core.exception;

import com.github.jarvisframework.tool.core.util.StringUtils;

/**
 * <p>验证异常</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-24 19:59:43
 */
public class ValidateException extends StatefulException {

    private static final long serialVersionUID = 6057602589533840889L;

    public ValidateException() {
    }

    public ValidateException(String msg) {
        super(msg);
    }

    public ValidateException(String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params));
    }

    public ValidateException(Throwable throwable) {
        super(throwable);
    }

    public ValidateException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public ValidateException(int status, String msg) {
        super(status, msg);
    }

    public ValidateException(int status, Throwable throwable) {
        super(status, throwable);
    }

    public ValidateException(int status, String msg, Throwable throwable) {
        super(status, msg, throwable);
    }
}
