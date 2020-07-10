package com.jarvisframework.tool.core.util;

/**
 * <p>数值操作工具类</p>
 *
 * @author 王涛
 * @since 1.0, 2018/1/16 15:40:00
 */
public abstract class NumberUtils {

    private NumberUtils() {
    }

    /**
     * 倒序输出
     *
     * @param paraNum
     * @return
     */
    public static int reverseNum(int paraNum) {

        /**
         * 如果传入的参数第一位或前几位是0将会得到错误的返回值
         */
        int revNum = 0;
        while (paraNum != 0) {
            revNum = paraNum % 10 + revNum * 10;
            paraNum /= 10;
        }
        return revNum;
    }
}

