package com.jarvis.framework.core.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * <p>Mac工具类</p>
 *
 * @author 王涛
 * @since 2018/1/16 15:40:00
 */
public class MacInfoUtils {

    /**
     * 获取MAC地址最后一个字节
     *
     * @return
     * @throws UnknownHostException
     * @throws SocketException
     */
    public static int getLastMac() throws UnknownHostException, SocketException {
        int id;
        InetAddress ip = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
        if (null != network) {
            byte[] mac = network.getHardwareAddress();
            id = mac[mac.length - 1];
        } else {
            id = 1;
        }
        return id;
    }
}
