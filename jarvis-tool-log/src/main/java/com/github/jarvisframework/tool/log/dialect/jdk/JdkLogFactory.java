package com.github.jarvisframework.tool.log.dialect.jdk;

import com.github.jarvisframework.tool.core.io.IOUtils;
import com.github.jarvisframework.tool.core.lang.Console;
import com.github.jarvisframework.tool.core.util.ResourceUtils;
import com.github.jarvisframework.tool.log.Log;
import com.github.jarvisframework.tool.log.LogFactory;

import java.io.InputStream;
import java.util.logging.LogManager;

/**
 * <p>JDK日志工厂类</p>
 *
 * @author 王涛
 * @see <a href="http://java.sun.com/javase/6/docs/technotes/guides/logging/index.html">java.util.logging</a> log.
 * @since 1.0, 2020-08-03 16:48:54
 */
public class JdkLogFactory extends LogFactory {

    public JdkLogFactory() {
        super("JDK Logging");
        readConfig();
    }

    @Override
    public Log createLog(String name) {
        return new JdkLog(name);
    }

    @Override
    public Log createLog(Class<?> clazz) {
        return new JdkLog(clazz);
    }

    /**
     * 读取ClassPath下的logging.properties配置文件
     */
    private void readConfig() {
        //避免循环引用，Log初始化的时候不使用相关工具类
        InputStream in = ResourceUtils.getStreamSafe("logging.properties");
        if(null == in){
            System.err.println("[WARN] Can not find [logging.properties], use [%JRE_HOME%/lib/logging.properties] as default!");
            return;
        }

        try {
            LogManager.getLogManager().readConfiguration(in);
        } catch (Exception e) {
            Console.error(e, "Read [logging.properties] from classpath error!");
            try {
                LogManager.getLogManager().readConfiguration();
            } catch (Exception e1) {
                Console.error(e, "Read [logging.properties] from [%JRE_HOME%/lib/logging.properties] error!");
            }
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
}
