package com.github.jarvisframework.tool.core.io;

/**
 * <p>行处理器</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-30 11:44:47
 */
@FunctionalInterface
public interface LineHandler {

    /**
     * 处理一行数据，可以编辑后存入指定地方
     *
     * @param line 行
     */
    void handle(String line);
}
