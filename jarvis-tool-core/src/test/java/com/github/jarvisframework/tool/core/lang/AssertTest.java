package com.github.jarvisframework.tool.core.lang;

import com.github.jarvisframework.tool.core.util.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>断言测试类</p>
 *
 * @author Doug Wang
 * @since 1.0, 2020-11-19 10:26:05
 */
public class AssertTest {

    @Test
    public void notNullTest() {
        String nullable = "";
        Assert.notNull(nullable, () -> new RuntimeException("Parameter is null"));
    }

    @Test
    public void notBlankTest() {
       String text = "";
       Assert.notBlank(text, () -> {
           System.out.println("init....");
           return new RuntimeException("The text must not be null");
       });
    }

    @Test
    public void notEmptyTest() {
        List<String> list = Arrays.asList("1");
        Assert.notEmpty(list, () -> new RuntimeException("Collection must have elements"));
        Map map = new HashMap(){{put("a","a");}};
        Assert.notEmpty(map, () -> new RuntimeException("Map must have entries"));
    }

    /**
     * 每次调用都会创建一个异常对象浪费资源
     * @param text
     * @param x
     * @param <T>
     * @param <X>
     * @return
     * @throws X
     */
    public static <T extends CharSequence, X extends Throwable> T notBlank(T text, X x) throws X {
        if (StringUtils.isBlank(text)) {
            throw x;
        } else {
            return text;
        }
    }

}
