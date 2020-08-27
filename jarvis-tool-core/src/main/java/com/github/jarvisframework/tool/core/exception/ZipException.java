package com.github.jarvisframework.tool.core.exception;

/**
 * <p>压缩异常类</p>
 *
 * @author 王涛
 * @date 2019-11月-22 11:43:37
 */
public class ZipException extends BusinessException {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 717486582122505280L;

    public ZipException(String msg) {
        super(msg);
    }

    public ZipException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
