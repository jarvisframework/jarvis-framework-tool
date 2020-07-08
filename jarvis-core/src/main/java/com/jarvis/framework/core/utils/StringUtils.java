package com.jarvis.framework.core.utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>字符串工具类</p>
 *
 * @author 王涛
 * @since 2017-4-10 15:54:54
 */
public abstract class StringUtils {

    private StringUtils() {
    }

    /**
     * 判断是否是空
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否不是空
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        if ((str != null) && !"".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 格式化模糊查询
     *
     * @param str
     * @return
     */
    public static String formatLike(String str) {
        if (isNotBlank(str)) {
            return "%" + str + "%";
        } else {
            return null;
        }
    }

    /**
     * 过滤掉集合里的空格
     *
     * @param list
     * @return
     */
    public static List<String> filterWhite(List<String> list) {
        return list.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
    }

}
