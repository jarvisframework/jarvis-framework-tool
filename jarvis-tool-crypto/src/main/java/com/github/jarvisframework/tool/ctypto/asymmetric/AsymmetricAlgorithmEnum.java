package com.github.jarvisframework.tool.ctypto.asymmetric;

/**
 * <p>非对称算法类型</p>
 *
 * @author 王涛
 * @see <a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyPairGenerator"></a>
 * @since 1.0, 2020-07-24 18:14:26
 */
public enum AsymmetricAlgorithmEnum {

    /**
     * RSA算法
     */
    RSA("RSA"),
    /**
     * RSA算法，此算法用了默认补位方式为RSA/ECB/PKCS1Padding
     */
    RSA_ECB_PKCS1("RSA/ECB/PKCS1Padding"),
    /**
     * RSA算法，此算法用了RSA/None/NoPadding
     */
    RSA_None("RSA/None/NoPadding");

    private final String value;

    /**
     * 构造
     *
     * @param value 算法字符表示，区分大小写
     */
    AsymmetricAlgorithmEnum(String value) {
        this.value = value;
    }

    /**
     * 获取算法字符串表示，区分大小写
     *
     * @return 算法字符串表示
     */
    public String getValue() {
        return this.value;
    }
}