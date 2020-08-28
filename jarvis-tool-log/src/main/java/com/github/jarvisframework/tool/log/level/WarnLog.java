package com.github.jarvisframework.tool.log.level;

/**
 * <p>WARN级别日志接口</p>
 *
 * @author Doug Wang
 * @since 1.0, 2020-08-03 16:40:33
 */
public interface WarnLog {

    /**
     * WARN 等级是否开启
     *
     * @return 判断结果
     */
    boolean isWarnEnabled();

    /**
     * 打印 WARN 等级的日志
     *
     * @param t 错误对象
     */
    void warn(Throwable t);

    /**
     * 打印 WARN 等级的日志
     *
     * @param format    消息模板
     * @param arguments 参数
     */
    void warn(String format, Object... arguments);

    /**
     * 打印 WARN 等级的日志
     *
     * @param t         错误对象
     * @param format    消息模板
     * @param arguments 参数
     */
    void warn(Throwable t, String format, Object... arguments);

    /**
     * 打印 WARN 等级的日志
     *
     * @param fqcn      完全限定类名(Fully Qualified Class Name)，用于定位日志位置
     * @param t         错误对象
     * @param format    消息模板
     * @param arguments 参数
     */
    void warn(String fqcn, Throwable t, String format, Object... arguments);
}
