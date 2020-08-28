package com.github.jarvisframework.tool.core.exception;

/**
 * <p>枚举解析异常类</p>
 *
 * @author Doug Wang
 * @date 2020-03-09 12:48:43
 */
public class EnumParseException extends BusinessException {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -4471157714349647135L;

    public EnumParseException(String msg) {
        super(msg);
    }
}
