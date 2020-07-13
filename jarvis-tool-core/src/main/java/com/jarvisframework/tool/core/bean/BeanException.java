package com.jarvisframework.tool.core.bean;


import com.jarvisframework.tool.core.exception.ExceptionUtils;
import com.jarvisframework.tool.core.util.StringUtils;

/**
 * <p>Bean异常</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-13 11:01:38
 */
public class BeanException extends RuntimeException {

    public BeanException(Throwable e) {
        super(ExceptionUtils.getMessage(e), e);
    }

    public BeanException(String message) {
        super(message);
    }

    public BeanException(String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params));
    }

    public BeanException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BeanException(Throwable throwable, String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params), throwable);
    }

}
