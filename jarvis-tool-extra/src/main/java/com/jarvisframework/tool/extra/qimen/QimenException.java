package com.jarvisframework.tool.extra.qimen;

import com.jarvisframework.tool.core.exception.BusinessException;

/**
 * <p>奇门业务异常类</p>
 *
 * @author 王涛
 * @date 2019年11月04日 11:10:03
 */
public class QimenException extends BusinessException {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 8863711771774657551L;

    public QimenException() {
        super();
    }

    public QimenException(String msg) {
        super(msg);
    }

    public QimenException(Exception e) {
        super(e.getMessage());
    }

    public QimenException(String code, String msg) {
        super(code, msg);
    }

    public QimenException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }
}
