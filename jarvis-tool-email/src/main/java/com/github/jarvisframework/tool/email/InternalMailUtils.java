package com.github.jarvisframework.tool.email;

import com.github.jarvisframework.tool.core.util.ArrayUtils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>邮件内部工具类</p>
 *
 * @author Doug Wang
 * @since 1.0, 2020-08-31 14:43:36
 */
public class InternalMailUtils {

    /**
     * 将多个字符串邮件地址转为{@link InternetAddress}列表<br>
     * 单个字符串地址可以是多个地址合并的字符串
     *
     * @param addrStrs 地址数组
     * @param charset  编码（主要用于中文用户名的编码）
     * @return 地址数组
     * @since 4.0.3
     */
    public static InternetAddress[] parseAddressFromStrs(String[] addrStrs, Charset charset) {
        final List<InternetAddress> resultList = new ArrayList<>(addrStrs.length);
        InternetAddress[] addresses;
        for (String addrStr : addrStrs) {
            addresses = parseAddress(addrStr, charset);
            if (ArrayUtils.isNotEmpty(addresses)) {
                Collections.addAll(resultList, addresses);
            }
        }
        return resultList.toArray(new InternetAddress[0]);
    }

    /**
     * 解析第一个地址
     *
     * @param address 地址字符串
     * @param charset 编码
     * @return 地址列表
     */
    public static InternetAddress parseFirstAddress(String address, Charset charset) {
        final InternetAddress[] internetAddresses = parseAddress(address, charset);
        if (ArrayUtils.isEmpty(internetAddresses)) {
            try {
                return new InternetAddress(address);
            } catch (AddressException e) {
                throw new MailException(e);
            }
        }
        return internetAddresses[0];
    }

    /**
     * 将一个地址字符串解析为多个地址<br>
     * 地址间使用" "、","、";"分隔
     *
     * @param address 地址字符串
     * @param charset 编码
     * @return 地址列表
     */
    public static InternetAddress[] parseAddress(String address, Charset charset) {
        InternetAddress[] addresses;
        try {
            addresses = InternetAddress.parse(address);
        } catch (AddressException e) {
            throw new MailException(e);
        }
        //编码用户名
        if (ArrayUtils.isNotEmpty(addresses)) {
            for (InternetAddress internetAddress : addresses) {
                try {
                    internetAddress.setPersonal(internetAddress.getPersonal(), charset.name());
                } catch (UnsupportedEncodingException e) {
                    throw new MailException(e);
                }
            }
        }

        return addresses;
    }

    /**
     * 编码中文字符<br>
     * 编码失败返回原字符串
     *
     * @param text    被编码的文本
     * @param charset 编码
     * @return 编码后的结果
     */
    public static String encodeText(String text, Charset charset) {
        try {
            return MimeUtility.encodeText(text, charset.name(), null);
        } catch (UnsupportedEncodingException e) {
            // ignore
        }
        return text;
    }
}