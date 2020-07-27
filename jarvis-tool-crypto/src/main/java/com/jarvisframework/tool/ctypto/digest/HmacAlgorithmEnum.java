package com.jarvisframework.tool.ctypto.digest;

/**
 * <p>HMAC算法类型</p>
 *
 * @author 王涛
 * @ss <a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Mac"></a>
 * @since 1.0, 2020-07-24 18:26:07
 */
public enum HmacAlgorithmEnum {

    HmacMD5("HmacMD5"),
    HmacSHA1("HmacSHA1"),
    HmacSHA256("HmacSHA256"),
    HmacSHA384("HmacSHA384"),
    HmacSHA512("HmacSHA512"),
    /**
     * HmacSM3算法实现，需要BouncyCastle库支持
     */
    HmacSM3("HmacSM3");

    private final String value;

    HmacAlgorithmEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
