package com.github.jarvisframework.tool.core.lang;

import org.junit.Test;

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
       String text = null;
       Assert.notBlank(text, () -> new RuntimeException("The text must not be null"));
    }

}
