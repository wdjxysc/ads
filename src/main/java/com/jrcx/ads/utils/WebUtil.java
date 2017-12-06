package com.jrcx.ads.utils;

import com.jrcx.ads.emuns.AdsError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
	private static final Logger logger = LoggerFactory.getLogger(WebUtil.class);

	public static Object parseParam(HttpServletRequest request, Class<?> clazz)
			throws Exception {

		try {
			String data = request.getParameter("data");
			logger.info("reqData:{}", data);
			return JacksonJsonUtil.readValue(data, clazz);
		} catch (Exception e) {
			logger.error("parseParam", e);
			throw new Exception("参数错误!");
		}
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static boolean isIE(HttpServletRequest request) {
		return (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 || request
				.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0) ? true
				: false;
	}



	
	public static String generateErrorResult(AdsError error) {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append('"').append("success").append('"').append(":")
				.append("false,");
		builder.append('"').append("errorCode").append('"').append(":")
				.append(error.key).append(",");
		builder.append('"').append("msg").append('"').append(":").append('"')
				.append(error.value).append('"');
		builder.append("}");
		return builder.toString();
	}
}
