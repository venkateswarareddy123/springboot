package com.springboot.kafka.util;

public class ResponseData<T> {

	private T body;

	public ResponseData() {
	}

	public ResponseData(T body) {
		this.body = body;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
}
