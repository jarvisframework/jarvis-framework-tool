package com.github.jarvisframework.tool.core.exception;

/**
 * <p>异常基类</p>
 *
 * @author 王涛
 * @date 2019-10-31 17:07:37
 */
public interface IException {

    /**
     * 获取异常编码
     * @return
     */
    String getErrorCode();

    /**
     * 获取本地异常信息
     * @return
     */
    String getNativeMessage();

    /**
     * 设置异常参数
     * @param objects
     */
    void setErrorArguments(Object... objects);

    /**
     * 取异常参数
     * @return
     */
    Object[] getErrorArguments();
    
}
