package com.github.jarvisframework.tool.ctypto.digest.mac;


import com.github.jarvisframework.tool.ctypto.SmUtils;
import com.github.jarvisframework.tool.ctypto.digest.HmacAlgorithmEnum;

import java.security.Key;

/**
 * <p>{@link MacEngine} 实现工厂类</p>
 *
 * @author Doug Wang
 * @since 1.0, 2020-07-24 20:10:27
 */
public class MacEngineFactory {

    /**
     * 根据给定算法和密钥生成对应的{@link MacEngine}
     * @param algorithm 算法，见{@link HmacAlgorithmEnum}
     * @param key 密钥
     * @return {@link MacEngine}
     */
    public static MacEngine createEngine(String algorithm, Key key) {
        if(algorithm.equalsIgnoreCase(HmacAlgorithmEnum.HmacSM3.getValue())) {
            // HmacSM3算法是BC库实现的
            return SmUtils.createHmacSm3Engine(key.getEncoded());
        }
        return new DefaultHMacEngine(algorithm, key);
    }

}
