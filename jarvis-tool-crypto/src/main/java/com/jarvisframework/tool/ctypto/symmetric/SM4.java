package com.jarvisframework.tool.ctypto.symmetric;


import com.jarvisframework.tool.core.util.ArrayUtils;
import com.jarvisframework.tool.core.util.StringUtils;
import com.jarvisframework.tool.ctypto.ModeEnum;
import com.jarvisframework.tool.ctypto.PaddingEnum;
import com.jarvisframework.tool.ctypto.SecureUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * <p>SM4实现</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-23 11:56:48
 */
public class SM4 extends SymmetricCrypto {
    private static final long serialVersionUID = 1L;

    public static final String ALGORITHM_NAME = "SM4";

    //------------------------------------------------------------------------- Constrctor start

    /**
     * 构造，使用随机密钥
     */
    public SM4() {
        super(ALGORITHM_NAME);
    }

    /**
     * 构造
     *
     * @param key 密钥
     */
    public SM4(byte[] key) {
        super(ALGORITHM_NAME, key);
    }

    /**
     * 构造，使用随机密钥
     *
     * @param mode    模式{@link ModeEnum}
     * @param padding {@link PaddingEnum}补码方式
     */
    public SM4(ModeEnum mode, PaddingEnum padding) {
        this(mode.name(), padding.name());
    }

    /**
     * 构造
     *
     * @param mode    模式{@link ModeEnum}
     * @param padding {@link PaddingEnum}补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     */
    public SM4(ModeEnum mode, PaddingEnum padding, byte[] key) {
        this(mode, padding, key, null);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link ModeEnum}
     * @param padding {@link PaddingEnum}补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     * @param iv      偏移向量，加盐
     */
    public SM4(ModeEnum mode, PaddingEnum padding, byte[] key, byte[] iv) {
        this(mode.name(), padding.name(), key, iv);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link ModeEnum}
     * @param padding {@link PaddingEnum}补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     */
    public SM4(ModeEnum mode, PaddingEnum padding, SecretKey key) {
        this(mode, padding, key, (IvParameterSpec) null);
    }

    /**
     * 构造
     *
     * @param mode    模式{@link ModeEnum}
     * @param padding {@link PaddingEnum}补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     * @param iv      偏移向量，加盐
     */
    public SM4(ModeEnum mode, PaddingEnum padding, SecretKey key, byte[] iv) {
        this(mode, padding, key, ArrayUtils.isEmpty(iv) ? ((IvParameterSpec) null) : new IvParameterSpec(iv));
    }

    /**
     * 构造
     *
     * @param mode    模式{@link ModeEnum}
     * @param padding {@link PaddingEnum}补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     * @param iv      偏移向量，加盐
     */
    public SM4(ModeEnum mode, PaddingEnum padding, SecretKey key, IvParameterSpec iv) {
        this(mode.name(), padding.name(), key, iv);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     */
    public SM4(String mode, String padding) {
        this(mode, padding, (byte[]) null);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     */
    public SM4(String mode, String padding, byte[] key) {
        this(mode, padding, key, null);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     * @param iv      加盐
     */
    public SM4(String mode, String padding, byte[] key, byte[] iv) {
        this(mode, padding,
                SecureUtils.generateKey(ALGORITHM_NAME, key),
                ArrayUtils.isEmpty(iv) ? null : new IvParameterSpec(iv));
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     */
    public SM4(String mode, String padding, SecretKey key) {
        this(mode, padding, key, null);
    }

    /**
     * 构造
     *
     * @param mode    模式
     * @param padding 补码方式
     * @param key     密钥，支持三种密钥长度：128、192、256位
     * @param iv      加盐
     */
    public SM4(String mode, String padding, SecretKey key, IvParameterSpec iv) {
        super(StringUtils.format("SM4/{}/{}", mode, padding), key, iv);
    }
    //------------------------------------------------------------------------- Constrctor end
}
