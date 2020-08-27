package com.github.jarvisframework.tool.core.io.watch.watcher;

import com.github.jarvisframework.tool.core.io.watch.Watcher;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * 跳过所有事件处理Watcher<br>
 * 用户继承此类后实现需要监听的方法
 *
 * @author 王涛
 * @since 1.0, 2020-08-03 10:32:20
 */
public class IgnoreWatcher implements Watcher {

    @Override
    public void onCreate(WatchEvent<?> event, Path currentPath) {
    }

    @Override
    public void onModify(WatchEvent<?> event, Path currentPath) {
    }

    @Override
    public void onDelete(WatchEvent<?> event, Path currentPath) {
    }

    @Override
    public void onOverflow(WatchEvent<?> event, Path currentPath) {
    }
}
