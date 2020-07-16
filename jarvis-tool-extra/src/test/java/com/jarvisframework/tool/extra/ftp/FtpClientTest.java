package com.jarvisframework.tool.extra.ftp;

import com.jarvisframework.tool.core.lang.Console;
import com.jarvisframework.tool.core.util.StringUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * <p>FTP客户端测试类</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-14 12:08:34
 */
public class FtpClientTest {

    @Test
    public void Test() throws IOException {
        //FtpClient client = new FtpClient("61.164.128.178",12121,"CARRIER","SEMIRpASS", FtpModeEnum.Passive);
        FtpClient client = new FtpClient("ftp.unisdigital.com",21,"prd-xinyitai","xyt02@PWD");
        // System.out.println(client.cd("/download/"));
        // System.out.println(client.ls("/"));
        // InputStream stream = client.downloadStream("/","8496-20071410360367550-1.png");

        Console.log(client.listNames());
        Console.log(client.downloadDataTableByTextFile("/downloadbak/", "333.txt", StringUtils.TAB));
    }

}
