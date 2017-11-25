package com.jrcx.ads.res;

public class DataRes<T> extends BaseRes {
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
