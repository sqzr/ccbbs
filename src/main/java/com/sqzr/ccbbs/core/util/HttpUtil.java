package com.sqzr.ccbbs.core.util;

import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http相关的工具类
 * User: WeiYang
 * Date: 2015/6/4
 * Time: 15:46
 */
@NoArgsConstructor
public class HttpUtil {
    /**
     * 清空用户cookie
     */
    public static void clearCookies() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    /**
     * 清除cookie
     *
     * @param cookieName cookie名
     */
    public static void clearCookie(String cookieName) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Cookie newCookie = new Cookie(cookieName, null);
        newCookie.setMaxAge(0);
        newCookie.setPath("/");
        response.addCookie(newCookie);
    }

    /**
     * 获取当前请求用户的Http头信息
     * 获取useragent  exp: getHeader("user-agent");
     *
     * @param header 需要获取的Http头Key
     * @return
     */
    public static String getHeader(String header) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request.getHeader(header);
    }


    /**
     * 获取当前请求用户的ip
     *
     * @param isHeader 是否需要从header头中读取,此头部数据头部数据可伪造。
     *                 当在使用CDN情况时,设为true(或者某些大型网络中),
     *                 CDN 会将真实用户ip 放入请求头部中,如果未使用CDN,则设为false,获取连接到此服务器的ip,
     *                 而不从http头中读取
     * @return ip
     */
    public static String getIp(boolean isHeader) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        if (isHeader) {
            String ip = getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = getHeader("HTTP_X_FORWARDED_FOR");
            }
            return ip;
        } else {
            return request.getRemoteAddr();
        }
    }


    /**
     * 获取当前请求用户的ip(读取用户真实ip,不从http头中取)
     *
     * @return
     */
    public static String getIp() {
        return getIp(false);
    }

    /**
     * 读取当前请求用户的useragent
     *
     * @return
     */
    public static String getUseragent() {
        return getHeader("user-agent");
    }
}
