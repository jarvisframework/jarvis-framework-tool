package com.github.jarvisframework.tool.extra.ftp;

import com.github.jarvisframework.tool.core.exception.ExceptionUtils;
import com.github.jarvisframework.tool.core.util.StringUtils;

/**
 * <p>Ftp异常类</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-14 11:58:21
 */
public class FtpException extends RuntimeException {

    private static final long serialVersionUID = 8034331991006644355L;

    public FtpException(Throwable e) {
        super(ExceptionUtils.getMessage(e), e);
    }

    public FtpException(String message) {
        super(message);
    }

    public FtpException(String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params));
    }

    public FtpException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public FtpException(Throwable throwable, String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params), throwable);
    }
}
