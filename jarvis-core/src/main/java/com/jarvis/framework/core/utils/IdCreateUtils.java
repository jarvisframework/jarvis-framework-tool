package com.jarvis.framework.core.utils;

import com.jarvis.framework.core.SnowflakeIdWorker;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>ID生成工具类</p>
 *
 * @author 王涛
 * @since 2018/1/16 20:02:00
 */
public class IdCreateUtils {

    /**
     * 推特ID生产器
     */
    private static final SnowflakeIdWorker ID_WORKER = new SnowflakeIdWorker();

    private static boolean IS_THREAD_LOCAL_RANDOM_AVAILABLE = false;

    private static Random random;

    private static final long LEAST_SIG_BITS;

    /**
     * 重入锁
     */
    private static final ReentrantLock LOCKER = new ReentrantLock();

    private static long lastTime;

    static {
        try {
            IS_THREAD_LOCAL_RANDOM_AVAILABLE = null != IdCreateUtils.class
                    .getClassLoader().loadClass("java.util.concurrent.ThreadLocalRandom");
        } catch (ClassNotFoundException e) {
        }
        byte[] seed = new SecureRandom().generateSeed(8);
        LEAST_SIG_BITS = new BigInteger(seed).longValue();
        if (!IS_THREAD_LOCAL_RANDOM_AVAILABLE) {
            random = new Random(LEAST_SIG_BITS);
        }
    }

    /**
     * 私有化构造函数
     */
    private IdCreateUtils() {
    }

    /**
     * 生成一个新的工作ID
     *
     * @return
     */
    public static long createWorkId() {
        return ID_WORKER.nextId();
    }


    /**
     * 生成一个长整形ID
     *
     * @return
     */
    public static BigInteger createBigIntId() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //把16进制，转换成10进制的大数字
        return new BigInteger(uuid, 16);
    }

    /**
     * 生成一个随机长整形ID
     *
     * @return
     */
    public static BigInteger createRandomBigIntId() {
        return new BigInteger(createTimeBasedUUIDStr().getBytes());
    }

    /**
     * 生成一个原始的UUID字符串
     *
     * @return
     */
    public static String createUUIDStr() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成一个基于随机数的UUID字符
     *
     * @return
     */
    public static String createRandomBasedUUIDStr() {
        byte[] randomBytes = new byte[16];
        if (IS_THREAD_LOCAL_RANDOM_AVAILABLE) {
            ThreadLocalRandom.current().nextBytes(randomBytes);
        } else {
            random.nextBytes(randomBytes);
        }
        long mostSigBits = 0;
        for (int i = 0; i < 8; i++) {
            mostSigBits = (mostSigBits << 8) | (randomBytes[i] & 0xff);
        }
        long leastSigBits = 0;
        for (int i = 8; i < 16; i++) {
            leastSigBits = (leastSigBits << 8) | (randomBytes[i] & 0xff);
        }
        return new UUID(mostSigBits, leastSigBits).toString().replaceAll("-", "");
    }

    /**
     * 生成一个基于时间戳的UUID字符
     *
     * @return
     */
    public static String createTimeBasedUUIDStr() {
        long timeMillis = (System.currentTimeMillis() * 10000) + 0x01B21DD213814000L;
        LOCKER.lock();
        try {
            if (timeMillis > lastTime) {
                lastTime = timeMillis;
            } else {
                timeMillis = ++lastTime;
            }
        } finally {
            LOCKER.unlock();
        }
        // time low
        long mostSigBits = timeMillis << 32;
        // time mid
        mostSigBits |= (timeMillis & 0xFFFF00000000L) >> 16;
        // time hi and version
        mostSigBits |= 0x1000 | ((timeMillis >> 48) & 0x0FFF);
        return new UUID(mostSigBits, LEAST_SIG_BITS).toString().replaceAll("-", "");
    }

}
