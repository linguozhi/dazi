package com.dazi.core.common.utils;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * web相关辅助类
 *
 * @author fengweixiong
 */
public class WebUtils {

    public static ServletRequestAttributes getRequestAttributes() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }

    /**
     * 获取请求对象
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取回应对象
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取上下文
     */
    public static ServletContext getContext() {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }

    /**
     * 设置cookie的值
     */
    public static void addCookie(String name, String value) {
        HttpServletResponse response = getResponse();
        Cookie cookie = new Cookie(name, value);
        response.addCookie(cookie);
    }

    /**
     * 设置cookie的值
     */
    public static void addCookie(String name, String value, int maxAge) {
        HttpServletResponse response = getResponse();
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获取cookie的值
     */
    public static String getCookie(String name) {
        HttpServletRequest request = getRequest();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * url编码
     *
     * @param value
     * @return url编码
     */
    public static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, Charset.defaultCharset().name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


}
