package com.github.jarvisframework.tool.ctypto.digest;

/**
 * <p>摘要算法类型枚举类</p>
 * see: https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#MessageDigest
 *
 * @author Doug Wang
 * @since 1.0, 2020-07-22 10:57:18
 */
public enum DigestAlgorithmEnum {

    /**
     * MD2
     */
    MD2("MD2"),
    /**
     * MD5
     */
    MD5("MD5"),
    /**
     * SHA-1
     */
    SHA1("SHA-1"),
    /**
     * SHA-256
     */
    SHA256("SHA-256"),
    /**
     * SHA-384
     */
    SHA384("SHA-384"),
    /**
     * SHA-512
     */
    SHA512("SHA-512");

    private final String value;

    /**
     * 构造
     *
     * @param value 算法字符串表示
     */
    DigestAlgorithmEnum(String value) {
        this.value = value;
    }

    /**
     * 获取算法字符串表示
     *
     * @return 算法字符串表示
     */
    public String getValue() {
        return this.value;
    }
}
