package com.wxw.support;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Result result;

	public ServiceException() {
		super();
	}

	public ServiceException(Result result) {
		super(result.getMessage());
		this.result = result;
	}

	public ServiceException(Result result, String message) {
		super(message == null ? result.getMessage() : message);
		this.result = result;
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}
