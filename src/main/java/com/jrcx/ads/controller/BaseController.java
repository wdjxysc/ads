package com.jrcx.ads.controller;



import com.jrcx.ads.res.BaseRes;
import com.jrcx.ads.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

	/**
	 * 统一用org.slf4j日志接口
	 */
	protected Logger logger = LoggerFactory.getLogger(super.getClass());


	protected Object getParam(HttpServletRequest request, BaseRes res,
                              Class<?> clazz) {
		Object param = null;
		try {
			param = WebUtil.parseParam(request, clazz);
		} catch (Exception e) {
			res.setErrorMsg(e.getMessage());
		}

		if (param == null) {
			res.setErrorMsg("参数格式错误!");
		}

		return param;
	}
}
