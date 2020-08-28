package com.github.jarvisframework.tool.ctypto.asymmetric;

import com.github.jarvisframework.tool.core.codec.BCD;
import com.github.jarvisframework.tool.core.codec.Base64;
import com.github.jarvisframework.tool.core.io.IORuntimeException;
import com.github.jarvisframework.tool.core.io.IOUtils;
import com.github.jarvisframework.tool.core.util.CharsetUtils;
import com.github.jarvisframework.tool.core.util.HexUtils;
import com.github.jarvisframework.tool.core.util.StringUtils;
import com.github.jarvisframework.tool.ctypto.SecureUtils;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * <p>抽象的非对称加密对象，包装了加密和解密为Hex和Base64的封装</p>
 *
 * @param <T> 返回自身类型
 * @author Doug Wang
 * @since 1.0, 2020-07-23 11:28:44
 */
public abstract class AbstractAsymmetricCrypto <T extends AbstractAsymmetricCrypto<T>> extends BaseAsymmetric<T> {
    // ------------------------------------------------------------------ Constructor start

    /**
     * 构造
     * <p>
     * 私钥和公钥同时为空时生成一对新的私钥和公钥<br>
     * 私钥和公钥可以单独传入一个，如此则只能使用此钥匙来做加密或者解密
     *
     * @param algorithm  算法
     * @param privateKey 私钥
     * @param publicKey  公钥
     * @since 3.1.1
     */
    public AbstractAsymmetricCrypto(String algorithm, PrivateKey privateKey, PublicKey publicKey) {
        super(algorithm, privateKey, publicKey);
    }
    // ------------------------------------------------------------------ Constructor end

    // --------------------------------------------------------------------------------- Encrypt

    /**
     * 加密
     *
     * @param data    被加密的bytes
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return 加密后的bytes
     */
    public abstract byte[] encrypt(byte[] data, KeyTypeEnum keyType);

    /**
     * 编码为Hex字符串
     *
     * @param data    被加密的bytes
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return Hex字符串
     */
    public String encryptHex(byte[] data, KeyTypeEnum keyType) {
        return HexUtils.encodeHexStr(encrypt(data, keyType));
    }

    /**
     * 编码为Base64字符串
     *
     * @param data    被加密的bytes
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return Base64字符串
     * @since 4.0.1
     */
    public String encryptBase64(byte[] data, KeyTypeEnum keyType) {
        return Base64.encode(encrypt(data, keyType));
    }

    /**
     * 加密
     *
     * @param data    被加密的字符串
     * @param charset 编码
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return 加密后的bytes
     */
    public byte[] encrypt(String data, String charset, KeyTypeEnum keyType) {
        return encrypt(StringUtils.bytes(data, charset), keyType);
    }

    /**
     * 加密
     *
     * @param data    被加密的字符串
     * @param charset 编码
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return 加密后的bytes
     */
    public byte[] encrypt(String data, Charset charset, KeyTypeEnum keyType) {
        return encrypt(StringUtils.bytes(data, charset), keyType);
    }

    /**
     * 加密，使用UTF-8编码
     *
     * @param data    被加密的字符串
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return 加密后的bytes
     */
    public byte[] encrypt(String data, KeyTypeEnum keyType) {
        return encrypt(StringUtils.bytes(data, CharsetUtils.CHARSET_UTF_8), keyType);
    }

    /**
     * 编码为Hex字符串
     *
     * @param data    被加密的字符串
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return Hex字符串
     * @since 4.0.1
     */
    public String encryptHex(String data, KeyTypeEnum keyType) {
        return HexUtils.encodeHexStr(encrypt(data, keyType));
    }

    /**
     * 编码为Hex字符串
     *
     * @param data    被加密的bytes
     * @param charset 编码
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return Hex字符串
     * @since 4.0.1
     */
    public String encryptHex(String data, Charset charset, KeyTypeEnum keyType) {
        return HexUtils.encodeHexStr(encrypt(data, charset, keyType));
    }

    /**
     * 编码为Base64字符串，使用UTF-8编码
     *
     * @param data    被加密的字符串
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return Base64字符串
     * @since 4.0.1
     */
    public String encryptBase64(String data, KeyTypeEnum keyType) {
        return Base64.encode(encrypt(data, keyType));
    }

    /**
     * 编码为Base64字符串
     *
     * @param data    被加密的字符串
     * @param charset 编码
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return Base64字符串
     * @since 4.0.1
     */
    public String encryptBase64(String data, Charset charset, KeyTypeEnum keyType) {
        return Base64.encode(encrypt(data, charset, keyType));
    }

    /**
     * 加密
     *
     * @param data    被加密的数据流
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return 加密后的bytes
     * @throws IORuntimeException IO异常
     */
    public byte[] encrypt(InputStream data, KeyTypeEnum keyType) throws IORuntimeException {
        return encrypt(IOUtils.readBytes(data), keyType);
    }

    /**
     * 编码为Hex字符串
     *
     * @param data    被加密的数据流
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return Hex字符串
     * @since 4.0.1
     */
    public String encryptHex(InputStream data, KeyTypeEnum keyType) {
        return HexUtils.encodeHexStr(encrypt(data, keyType));
    }

    /**
     * 编码为Base64字符串
     *
     * @param data    被加密的数据流
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return Base64字符串
     * @since 4.0.1
     */
    public String encryptBase64(InputStream data, KeyTypeEnum keyType) {
        return Base64.encode(encrypt(data, keyType));
    }

    /**
     * 分组加密
     *
     * @param data    数据
     * @param keyType 密钥类型
     * @return 加密后的密文
     * @since 4.1.0
     */
    public String encryptBcd(String data, KeyTypeEnum keyType) {
        return encryptBcd(data, keyType, CharsetUtils.CHARSET_UTF_8);
    }

    /**
     * 分组加密
     *
     * @param data    数据
     * @param keyType 密钥类型
     * @param charset 加密前编码
     * @return 加密后的密文
     * @since 4.1.0
     */
    public String encryptBcd(String data, KeyTypeEnum keyType, Charset charset) {
        return BCD.bcdToStr(encrypt(data, charset, keyType));
    }

    // --------------------------------------------------------------------------------- Decrypt

    /**
     * 解密
     *
     * @param bytes   被解密的bytes
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return 解密后的bytes
     */
    public abstract byte[] decrypt(byte[] bytes, KeyTypeEnum keyType);

    /**
     * 解密
     *
     * @param data    被解密的bytes
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return 解密后的bytes
     * @throws IORuntimeException IO异常
     */
    public byte[] decrypt(InputStream data, KeyTypeEnum keyType) throws IORuntimeException {
        return decrypt(IOUtils.readBytes(data), keyType);
    }

    /**
     * 从Hex或Base64字符串解密，编码为UTF-8格式
     *
     * @param data    Hex（16进制）或Base64字符串
     * @param keyType 私钥或公钥 {@link KeyTypeEnum}
     * @return 解密后的bytes
     * @since 4.5.2
     */
    public byte[] decrypt(String data, KeyTypeEnum keyType) {
        return decrypt(SecureUtils.decode(data), keyType);
    }

    /**
     * 解密为字符串，密文需为Hex（16进制）或Base64字符串
     *
     * @param data    数据，Hex（16进制）或Base64字符串
     * @param keyType 密钥类型
     * @param charset 加密前编码
     * @return 解密后的密文
     * @since 4.5.2
     */
    public String decryptStr(String data, KeyTypeEnum keyType, Charset charset) {
        return StringUtils.str(decrypt(data, keyType), charset);
    }

    /**
     * 解密为字符串，密文需为Hex（16进制）或Base64字符串
     *
     * @param data    数据，Hex（16进制）或Base64字符串
     * @param keyType 密钥类型
     * @return 解密后的密文
     * @since 4.5.2
     */
    public String decryptStr(String data, KeyTypeEnum keyType) {
        return decryptStr(data, keyType, CharsetUtils.CHARSET_UTF_8);
    }

    /**
     * 解密BCD
     *
     * @param data    数据
     * @param keyType 密钥类型
     * @return 解密后的密文
     * @since 4.1.0
     */
    public byte[] decryptFromBcd(String data, KeyTypeEnum keyType) {
        return decryptFromBcd(data, keyType, CharsetUtils.CHARSET_UTF_8);
    }

    /**
     * 分组解密
     *
     * @param data    数据
     * @param keyType 密钥类型
     * @param charset 加密前编码
     * @return 解密后的密文
     * @since 4.1.0
     */
    public byte[] decryptFromBcd(String data, KeyTypeEnum keyType, Charset charset) {
        final byte[] dataBytes = BCD.ascToBcd(StringUtils.bytes(data, charset));
        return decrypt(dataBytes, keyType);
    }

    /**
     * 解密为字符串，密文需为BCD格式
     *
     * @param data    数据，BCD格式
     * @param keyType 密钥类型
     * @param charset 加密前编码
     * @return 解密后的密文
     * @since 4.5.2
     */
    public String decryptStrFromBcd(String data, KeyTypeEnum keyType, Charset charset) {
        return StringUtils.str(decryptFromBcd(data, keyType, charset), charset);
    }

    /**
     * 解密为字符串，密文需为BCD格式，编码为UTF-8格式
     *
     * @param data    数据，BCD格式
     * @param keyType 密钥类型
     * @return 解密后的密文
     * @since 4.5.2
     */
    public String decryptStrFromBcd(String data, KeyTypeEnum keyType) {
        return decryptStrFromBcd(data, keyType, CharsetUtils.CHARSET_UTF_8);
    }
}
