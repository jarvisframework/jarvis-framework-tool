package com.jarvisframework.tool.ctypto.symmetric;

/**
 * 对称算法类型<br>
 * see: https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyGenerator
 *
 * @author 王涛
 * @since 1.0, 2020-07-23 12:00:22
 */
public enum SymmetricAlgorithmEnum {
    /**
     * 默认的AES加密方式：AES/CBC/PKCS5Padding
     */
    AES("AES"),

    ARCFOUR("ARCFOUR"),

    Blowfish("Blowfish"),

    /**
     * 默认的DES加密方式：DES/ECB/PKCS5Padding
     */
    DES("DES"),

    /**
     * 3DES算法，默认实现为：DESede/CBC/PKCS5Padding
     */
    DESede("DESede"),

    RC2("RC2"),

    PBEWithMD5AndDES("PBEWithMD5AndDES"),

    PBEWithSHA1AndDESede("PBEWithSHA1AndDESede"),

    PBEWithSHA1AndRC2_40("PBEWithSHA1AndRC2_40");

    private final String value;

    /**
     * 构造
     *
     * @param value 算法的字符串表示，区分大小写
     */
    SymmetricAlgorithmEnum(String value) {
        this.value = value;
    }

    /**
     * 获得算法的字符串表示形式
     *
     * @return 算法字符串
     */
    public String getValue() {
        return this.value;
    }
}
