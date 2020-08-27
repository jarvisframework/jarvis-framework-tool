package com.github.jarvisframework.tool.log.dialect.console;

import com.github.jarvisframework.tool.core.date.DateUtils;
import com.github.jarvisframework.tool.core.lang.Assert;
import com.github.jarvisframework.tool.core.lang.Console;
import com.github.jarvisframework.tool.core.lang.Dictionary;
import com.github.jarvisframework.tool.core.util.StringUtils;
import com.github.jarvisframework.tool.log.AbstractLog;
import com.github.jarvisframework.tool.log.level.LevelEnum;

/**
 * <p>利用System.out.println()打印日志</p>
 *
 * @author 王涛
 * @since 1.0, 2020-08-03 16:59:37
 */
public class ConsoleLog extends AbstractLog {
    private static final long serialVersionUID = -6843151523380063975L;

    private static final String logFormat = "[{date}] [{level}] {name}: {msg}";
    private static LevelEnum currentLevel = LevelEnum.DEBUG;

    private final String name;

    //------------------------------------------------------------------------- Constructor

    /**
     * 构造
     *
     * @param clazz 类
     */
    public ConsoleLog(Class<?> clazz) {
        this.name = (null == clazz) ? StringUtils.NULL : clazz.getName();
    }

    /**
     * 构造
     *
     * @param name 类名
     */
    public ConsoleLog(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * 设置自定义的日志显示级别
     *
     * @param customLevel 自定义级别
     * @since 4.1.10
     */
    public static void setLevel(LevelEnum customLevel) {
        Assert.notNull(customLevel);
        currentLevel = customLevel;
    }

    //------------------------------------------------------------------------- Trace
    @Override
    public boolean isTraceEnabled() {
        return isEnabled(LevelEnum.TRACE);
    }

    @Override
    public void trace(String fqcn, Throwable t, String format, Object... arguments) {
        log(fqcn, LevelEnum.TRACE, t, format, arguments);
    }

    //------------------------------------------------------------------------- Debug
    @Override
    public boolean isDebugEnabled() {
        return isEnabled(LevelEnum.DEBUG);
    }

    @Override
    public void debug(String fqcn, Throwable t, String format, Object... arguments) {
        log(fqcn, LevelEnum.DEBUG, t, format, arguments);
    }

    //------------------------------------------------------------------------- Info
    @Override
    public boolean isInfoEnabled() {
        return isEnabled(LevelEnum.INFO);
    }

    @Override
    public void info(String fqcn, Throwable t, String format, Object... arguments) {
        log(fqcn, LevelEnum.INFO, t, format, arguments);
    }

    //------------------------------------------------------------------------- Warn
    @Override
    public boolean isWarnEnabled() {
        return isEnabled(LevelEnum.WARN);
    }

    @Override
    public void warn(String fqcn, Throwable t, String format, Object... arguments) {
        log(fqcn, LevelEnum.WARN, t, format, arguments);
    }

    //------------------------------------------------------------------------- Error
    @Override
    public boolean isErrorEnabled() {
        return isEnabled(LevelEnum.ERROR);
    }

    @Override
    public void error(String fqcn, Throwable t, String format, Object... arguments) {
        log(fqcn, LevelEnum.ERROR, t, format, arguments);
    }

    //------------------------------------------------------------------------- Log
    @Override
    public void log(String fqcn, LevelEnum level, Throwable t, String format, Object... arguments) {
        // fqcn 无效
        if (false == isEnabled(level)) {
            return;
        }

        final Dictionary dict = Dictionary.create()
                .set("date", DateUtils.now())
                .set("level", level.toString())
                .set("name", this.name)
                .set("msg", StringUtils.format(format, arguments));

        final String logMsg = StringUtils.format(logFormat, dict);

        //WARN以上级别打印至System.err
        if (level.ordinal() >= LevelEnum.WARN.ordinal()) {
            Console.error(t, logMsg);
        } else {
            Console.log(t, logMsg);
        }
    }

    @Override
    public boolean isEnabled(LevelEnum level) {
        return currentLevel.compareTo(level) <= 0;
    }
}
