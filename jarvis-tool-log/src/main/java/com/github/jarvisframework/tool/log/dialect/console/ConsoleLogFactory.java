package com.github.jarvisframework.tool.log.dialect.console;

import com.github.jarvisframework.tool.log.Log;
import com.github.jarvisframework.tool.log.LogFactory;

/**
 * <p>利用System.out.println()打印日志</p>
 *
 * @author Doug Wang
 * @since 1.0, 2020-08-03 16:58:17
 */
public class ConsoleLogFactory extends LogFactory {

    public ConsoleLogFactory() {
        super("Jarvis Console Logging");
    }

    @Override
    public Log createLog(String name) {
        return new ConsoleLog(name);
    }

    @Override
    public Log createLog(Class<?> clazz) {
        return new ConsoleLog(clazz);
    }

}
