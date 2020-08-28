package com.github.jarvisframework.tool.core.text;

import com.github.jarvisframework.tool.core.util.CharUtils;
import com.github.jarvisframework.tool.core.util.HexUtils;
import com.github.jarvisframework.tool.core.util.StringUtils;

/**
 * <p>提供Unicode字符串和普通字符串之间的转换</p>
 *
 * @author Doug Wang
 * @since 1.0, 2020-07-13 10:53:47
 */
public class UnicodeUtils {

    /**
     * Unicode字符串转为普通字符串<br>
     * Unicode字符串的表现方式为：\\uXXXX
     *
     * @param unicode Unicode字符串
     * @return 普通字符串
     */
    public static String toString(String unicode) {
        if (StringUtils.isBlank(unicode)) {
            return unicode;
        }

        final int len = unicode.length();
        StringBuilder sb = StringBuilder.create(len);
        int i;
        int pos = 0;
        while ((i = StringUtils.indexOfIgnoreCase(unicode, "\\u", pos)) != -1) {
            // 写入Unicode符之前的部分
            sb.append(unicode, pos, i);
            pos = i;
            if (i + 5 < len) {
                char c;
                try {
                    c = (char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16);
                    sb.append(c);
                    // 跳过整个Unicode符
                    pos = i + 6;
                } catch (NumberFormatException e) {
                    //非法Unicode符，跳过
                    //写入"\\u"
                    sb.append(unicode, pos, i + 2);
                    pos = i + 2;
                }
            } else {
                //非Unicode符，结束
                break;
            }
        }

        if (pos < len) {
            sb.append(unicode, pos, len);
        }
        return sb.toString();
    }

    /**
     * 字符串编码为Unicode形式
     *
     * @param str 被编码的字符串
     * @return Unicode字符串
     */
    public static String toUnicode(String str) {
        return toUnicode(str, true);
    }

    /**
     * 字符串编码为Unicode形式
     *
     * @param str         被编码的字符串
     * @param isSkipAscii 是否跳过ASCII字符（只跳过可见字符）
     * @return Unicode字符串
     */
    public static String toUnicode(String str, boolean isSkipAscii) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }

        final int len = str.length();
        final StringBuilder unicode = StringBuilder.create(str.length() * 6);
        char c;
        for (int i = 0; i < len; i++) {
            c = str.charAt(i);
            if (isSkipAscii && CharUtils.isAsciiPrintable(c)) {
                unicode.append(c);
            } else {
                unicode.append(HexUtils.toUnicodeHex(c));
            }
        }
        return unicode.toString();
    }
}
