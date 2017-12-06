package com.jrcx.ads.emuns;

public enum AdsError {
	INVALID_SIGN(1000, "无效签名！"),
	NO_LOGIN(1001, "未登录！"),
	EMPTY_TOKEN(1002, "Token为空！"),
	ACCOUNT_PASSWORD_ERROR(1003,"账号或密码错误！"),
	MUST_CHANGE_PASSWORD(1004,"请修改默认密码！"),
	LOGIN_FAILED_WITH_CAPTCHA(1005,"账号或密码错误！"),
	EMPTY_CAPTCHA(1006,"验证码为空！"),
	WRONG_CAPTCHA(1007,"验证码错误！"),
	HAS_SHARED(1008,"已经做过分享预约，不能再次预约！"),
	UNAUDIT_ACCOUNT(1009, "未审核，只能做实名认证！"),
	NO_PERMISSION(1010, "没有权限！"),
	COMPLETE_NEED_LOGIN(1011, "没有权限！"),
	;




	AdsError(Integer key, String value) {
		this.key = key;
		this.value = value;
	}


	public Integer key;
	public String value;
}
