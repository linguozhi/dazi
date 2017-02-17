package com.dazi.core.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class RequestUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(RequestUtils.class);
	
	/**
	 * Get the original IP address of the client
	 * 
	 * It depends from proxy server/load balancer and their configurations
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
			int i = ip.indexOf(',');
			if (i >= 0) {
				return ip.substring(0, i);
			} else {
				return ip;
			}
		}
		
		ip = request.getHeader("X-Real-IP");
		if (ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		
		ip = request.getHeader("Proxy-Client-IP");
		if (ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		
		ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		
		ip = request.getHeader("HTTP_CLIENT_IP");
		if (ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}

		ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		if (ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}

		return request.getRemoteAddr();
	}
	
	/**
	 * 构建回调参数
	 * @param request
	 * @return
	 */
	public static Map<String, String> buildNotifyParams(HttpServletRequest request) {
		Map<String, String[]> requestParams = request.getParameterMap();
		if(requestParams == null || requestParams.isEmpty()) {
			return null;
		}
		
		Map<String, String> params = new LinkedHashMap<String, String>();
		for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		
		return params;
	}
	
	/**
	 * 构建回调参数，指定参数做decode处理
	 * @param request
	 * @param decodeTarget 需要做decode的参数集合
	 * @param decode 编码
	 * @return
	 */
	public static Map<String, String> buildNotifyParamsUrlDecode(HttpServletRequest request, Set<String> decodeTarget, String decode) {
		Map<String, String[]> requestParams = request.getParameterMap();
		if(requestParams == null || requestParams.isEmpty()) {
			return null;
		}
		
		Map<String, String> params = new LinkedHashMap<String, String>();
		try {
			for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				
				if (decodeTarget.contains(name)) {
					params.put(name, URLDecoder.decode(valueStr, decode));
				} else {
					params.put(name, valueStr);
				}
			}
		} catch (Exception e) {
			logger.error("buildNotifyParamsUrlDecode() - error.", e);
		}
		
		return params;
	}
}
