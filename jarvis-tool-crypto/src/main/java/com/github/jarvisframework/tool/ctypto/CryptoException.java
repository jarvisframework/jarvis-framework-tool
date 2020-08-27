package com.github.jarvisframework.tool.ctypto;

import com.github.jarvisframework.tool.core.exception.ExceptionUtils;
import com.github.jarvisframework.tool.core.util.StringUtils;

/**
 * <p>加密异常类</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-22 10:54:34
 */
public class CryptoException extends RuntimeException {

    private static final long serialVersionUID = 8068509879445395353L;

    public CryptoException(Throwable e) {
        super(ExceptionUtils.getMessage(e), e);
    }

    public CryptoException(String message) {
        super(message);
    }

    public CryptoException(String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params));
    }

    public CryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CryptoException(Throwable throwable, String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params), throwable);
    }
}