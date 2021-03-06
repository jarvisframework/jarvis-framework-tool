package com.github.jarvisframework.tool.log.level;

/**
 * <p>DEBUG级别日志接口</p>
 *
 * @author Doug Wang
 * @since 1.0, 2020-08-03 16:38:43
 */
public interface DebugLog {

    /**
     * DEBUG 等级是否开启
     *
     * @return 判断结果
     */
    boolean isDebugEnabled();

    /**
     * 打印 DEBUG 等级的日志
     *
     * @param t 错误对象
     */
    void debug(Throwable t);

    /**
     * 打印 DEBUG 等级的日志
     *
     * @param format    消息模板
     * @param arguments 参数
     */
    void debug(String format, Object... arguments);

    /**
     * 打印 DEBUG 等级的日志
     *
     * @param t         错误对象
     * @param format    消息模板
     * @param arguments 参数
     */
    void debug(Throwable t, String format, Object... arguments);

    /**
     * 打印 DEBUG 等级的日志
     *
     * @param fqcn      完全限定类名(Fully Qualified Class Name)，用于定位日志位置
     * @param t         错误对象
     * @param format    消息模板
     * @param arguments 参数
     */
    void debug(String fqcn, Throwable t, String format, Object... arguments);
}
