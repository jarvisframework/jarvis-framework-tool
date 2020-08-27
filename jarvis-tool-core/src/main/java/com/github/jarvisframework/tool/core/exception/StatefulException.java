package com.github.jarvisframework.tool.core.exception;

import com.github.jarvisframework.tool.core.util.StringUtils;

/**
 * <p>带有状态码的异常</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-24 20:00:47
 */
public class StatefulException extends RuntimeException {
    private static final long serialVersionUID = 6057602589533840889L;

    // 异常状态码
    private int status;

    public StatefulException() {
    }

    public StatefulException(String msg) {
        super(msg);
    }

    public StatefulException(String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params));
    }

    public StatefulException(Throwable throwable) {
        super(throwable);
    }

    public StatefulException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public StatefulException(int status, String msg) {
        super(msg);
        this.status = status;
    }

    public StatefulException(int status, Throwable throwable) {
        super(throwable);
        this.status = status;
    }

    public StatefulException(int status, String msg, Throwable throwable) {
        super(msg, throwable);
        this.status = status;
    }

    /**
     * @return 获得异常状态码
     */
    public int getStatus() {
        return status;
    }
}
