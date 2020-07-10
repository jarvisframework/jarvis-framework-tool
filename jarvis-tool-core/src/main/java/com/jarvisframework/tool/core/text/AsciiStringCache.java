package com.jarvisframework.tool.core.text;

/**
 * <p>ASCII字符对应的字符串缓存</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-08 18:10:52
 */
public class AsciiStringCache {

    private static final int ASCII_LENGTH = 128;

    private static final String[] CACHE = new String[ASCII_LENGTH];

    static {
        for (char c = 0; c < ASCII_LENGTH; c++) {
            CACHE[c] = String.valueOf(c);
        }
    }

    /**
     * 字符转为字符串<br>
     * 如果为ASCII字符，使用缓存
     *
     * @param c 字符
     * @return 字符串
     */
    public static String toString(char c) {
        return c < ASCII_LENGTH ? CACHE[c] : String.valueOf(c);
    }
}
