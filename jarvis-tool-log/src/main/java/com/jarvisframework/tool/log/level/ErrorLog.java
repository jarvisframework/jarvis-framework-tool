package com.jarvisframework.tool.log.level;

/**
 * <p>ERROR级别日志接口</p>
 *
 * @author 王涛
 * @since 1.0, 2020-08-03 16:41:23
 */
public interface ErrorLog {

    /**
     * ERROR 等级是否开启
     *
     * @return 判断结果
     */
    boolean isErrorEnabled();

    /**
     * 打印 ERROR 等级的日志
     *
     * @param t 错误对象
     */
    void error(Throwable t);

    /**
     * 打印 ERROR 等级的日志
     *
     * @param format    消息模板
     * @param arguments 参数
     */
    void error(String format, Object... arguments);

    /**
     * 打印 ERROR 等级的日志
     *
     * @param t         错误对象
     * @param format    消息模板
     * @param arguments 参数
     */
    void error(Throwable t, String format, Object... arguments);

    /**
     * 打印 ERROR 等级的日志
     *
     * @param fqcn      完全限定类名(Fully Qualified Class Name)，用于定位日志位置
     * @param t         错误对象
     * @param format    消息模板
     * @param arguments 参数
     */
    void error(String fqcn, Throwable t, String format, Object... arguments);
}
