package com.jarvisframework.tool.core.comparator;

import com.jarvisframework.tool.core.exception.ExceptionUtils;
import com.jarvisframework.tool.core.util.StringUtils;

/**
 * <p>比较异常</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-30 11:29:33
 */
public class ComparatorException extends RuntimeException{
    private static final long serialVersionUID = 4475602435485521971L;

    public ComparatorException(Throwable e) {
        super(ExceptionUtils.getMessage(e), e);
    }

    public ComparatorException(String message) {
        super(message);
    }

    public ComparatorException(String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params));
    }

    public ComparatorException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ComparatorException(Throwable throwable, String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params), throwable);
    }
}
