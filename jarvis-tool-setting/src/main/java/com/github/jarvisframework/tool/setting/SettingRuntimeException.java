package com.github.jarvisframework.tool.setting;

import com.github.jarvisframework.tool.core.util.StringUtils;

/**
 * <p>设置异常</p>
 *
 * @author 王涛
 * @since 1.0, 2020-08-03 16:04:43
 */
public class SettingRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 7941096116780378387L;

    public SettingRuntimeException(Throwable e) {
        super(e);
    }

    public SettingRuntimeException(String message) {
        super(message);
    }

    public SettingRuntimeException(String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params));
    }

    public SettingRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public SettingRuntimeException(Throwable throwable, String messageTemplate, Object... params) {
        super(StringUtils.format(messageTemplate, params), throwable);
    }
}
