package io.kevin.common.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类，用户获取IP地址
 * @author ZGJ
 * @date 2017/7/3 22:06
 **/
public class IPUtils {
    private static Logger logger = LoggerFactory.getLogger(IPUtils.class);

    /**
     * 获取IP地址
     *
     * 如果使用了Nginx等反向代理软件，则不能通过request.getRemoteAddr()获取IP地址，要通过获取X-Forwarded-For的值
     * 如果使用了多级反向代理的话，则X-Forwarded-For的值会有多个，以逗号分隔的一串IP地址
     *
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip  = null;
        try {
            ip = request.getHeader("X-Forwarded-For");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            logger.error("IPUtils ERROR ", e);
        }
        return ip;
    }
}
