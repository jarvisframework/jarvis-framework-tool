package com.github.jarvisframework.tool.email;

import com.github.jarvisframework.tool.core.lang.Console;
import org.junit.Test;

/**
 * <p>邮件工具测试类</p>
 *
 * @author 王涛
 * @since 1.0, 2020-08-31 14:51:50
 */
public class MailUtilsTest {

    @Test
    public void sendTextTest() {
        String result = MailUtils.sendText("***9551*@qq.com","这是一封测试邮件","测试一下下啦~~~");
        Console.log(result);
    }

}
