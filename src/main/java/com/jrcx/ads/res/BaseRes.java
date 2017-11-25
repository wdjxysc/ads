package com.jrcx.ads.res;

public class BaseRes {
	private boolean success = true;
	/**
	 * 返回的信息
	 */
	protected String msg = "操作成功";

	protected Integer errorCode;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMsg(String msg) {
		this.success = false;
		this.msg = msg;
	}
}
