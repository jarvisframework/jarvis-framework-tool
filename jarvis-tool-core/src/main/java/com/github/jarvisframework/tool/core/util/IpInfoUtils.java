package com.github.jarvisframework.tool.core.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>IP工具类</p>
 *
 * @author 王涛
 * @since 2018/1/16 13:46
 */
public class IpInfoUtils {

    /**
     * 获取本机IP地址
     *
     * @return
     * @throws UnknownHostException
     */
    public static String getLocalHost() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * 获取IP地址最后一个字节
     *
     * @return
     * @throws UnknownHostException
     */
    public static int getLastIp() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        byte[] ipByte = ip.getAddress();
        return ipByte[ipByte.length - 1];
    }

    /**
     * 获取请求客户端的IP地址
     *
     * @param request
     * @return
     */
    public static String getClientReqAddr(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
