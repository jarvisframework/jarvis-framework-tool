package com.jarvis.framework.core.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * <p>Spring解析的属性配置对象</p>
 *
 * @author 王涛
 * @since 2018/1/17 13:53
 */
@Component("global")
public class SystemGlobal {

    @Value("#{configProperties}")
    private Properties properties;

    public String getSxbbsGlobal(String key) {
        return (String) properties.get(key);
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}