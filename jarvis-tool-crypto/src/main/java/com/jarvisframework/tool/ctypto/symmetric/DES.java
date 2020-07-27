package com.jarvisframework.tool.ctypto.symmetric;

import com.jarvisframework.tool.core.util.StringUtils;
import com.jarvisframework.tool.ctypto.ModeEnum;
import com.jarvisframework.tool.ctypto.PaddingEnum;
import com.jarvisframework.tool.ctypto.SecureUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * DES加密算法实现<br>
 * DES全称为Data Encryption Standard，即数据加密标准，是一种使用密钥加密的块算法<br>
 * Java中默认实现为：DES/CBC/PKCS5Padding
 *
 * @author 王涛
 * @since 1.0, 2020-07-24 18:47:23
 */
public class DES extends SymmetricCrypto {
    private static final long serialVersionUID = 1L;

    // ------------------------------------------------------------------------- Constrctor start
    /**
     * 构造，默认DES/CBC/PKCS5Padding，使用随机密钥
     */
    public DES() {
        super(SymmetricAlgorithmEnum.DES);
    }

    /**
     * 构造，使用默认的DES/CBC/PKCS5Padding
     *
     * @param key 密钥
     */
    public DES(byte[] key) {
        super(SymmetricAlgorithmEnum.DES, key);
    }

    /**
     * 构造，使用随机密钥
     *
     * @param mode 模式{@link ModeEnum}
     * @param padding {@link PaddingEnum}补码方式
     */
    public DES(ModeEnum mode, PaddingEnum padding) {
        this(mode.name(), padding.name());
    }

    /**
     * 构造
     *
     * @param mode 模式{@link ModeEnum}
     * @param padding {@link PaddingEnum}补码方式
     * @param key 密钥，长度：8的倍数
     */
    public DES(ModeEnum mode, PaddingEnum padding, byte[] key) {
        this(mode, padding, key, null);
    }

    /**
     * 构造
     *
     * @param mode 模式{@link ModeEnum}
     * @param padding {@link PaddingEnum}补码方式
     * @param key 密钥，长度：8的倍数
     * @param iv 偏移向量，加盐
     * @since 3.3.0
     */
    public DES(ModeEnum mode, PaddingEnum padding, byte[] key, byte[] iv) {
        this(mode.name(), padding.name(), key, iv);
    }

    /**
     * 构造
     *
     * @param mode 模式{@link ModeEnum}
     * @param padding {@link PaddingEnum}补码方式
     * @param key 密钥，长度：8的倍数
     * @since 3.3.0
     */
    public DES(ModeEnum mode, PaddingEnum padding, SecretKey key) {
        this(mode, padding, key, null);
    }

    /**
     * 构造
     *
     * @param mode 模式{@link ModeEnum}
     * @param padding {@link PaddingEnum}补码方式
     * @param key 密钥，长度：8的倍数
     * @param iv 偏移向量，加盐
     * @since 3.3.0
     */
    public DES(ModeEnum mode, PaddingEnum padding, SecretKey key, IvParameterSpec iv) {
        this(mode.name(), padding.name(), key, iv);
    }

    /**
     * 构造
     *
     * @param mode 模式
     * @param padding 补码方式
     */
    public DES(String mode, String padding) {
        this(mode, padding, (byte[]) null);
    }

    /**
     * 构造
     *
     * @param mode 模式
     * @param padding 补码方式
     * @param key 密钥，长度：8的倍数
     */
    public DES(String mode, String padding, byte[] key) {
        this(mode, padding, SecureUtils.generateKey("DES", key), null);
    }

    /**
     * 构造
     *
     * @param mode 模式
     * @param padding 补码方式
     * @param key 密钥，长度：8的倍数
     * @param iv 加盐
     */
    public DES(String mode, String padding, byte[] key, byte[] iv) {
        this(mode, padding, SecureUtils.generateKey("DES", key), null == iv ? null : new IvParameterSpec(iv));
    }

    /**
     * 构造
     *
     * @param mode 模式
     * @param padding 补码方式
     * @param key 密钥，长度：8的倍数
     */
    public DES(String mode, String padding, SecretKey key) {
        this(mode, padding, key, null);
    }

    /**
     * 构造
     *
     * @param mode 模式
     * @param padding 补码方式
     * @param key 密钥，长度：8的倍数
     * @param iv 加盐
     */
    public DES(String mode, String padding, SecretKey key, IvParameterSpec iv) {
        super(StringUtils.format("DES/{}/{}", mode, padding), key, iv);
    }
    // ------------------------------------------------------------------------- Constrctor end
}
