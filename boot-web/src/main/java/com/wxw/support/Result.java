package com.wxw.support;

public enum Result {
	SUCCESS(200, "成功"),
	FAILURE(-1, "失败"),
	Unauthorized(401, "未登录或会话过期"),
	FORBIDDEN(403, "没有权限访问"),
	NOT_FOUND(404, "资源不存在"),
	USER_EXIST(-1000, "用户已经存在"),
	USER_NOT_FOUND(-1001, "用户不存在"),
	USER_PASSWORD_INVALID(-1002, "用户密码不正确"),
	USER_PASSWORD_VALID_OVER(-1003, "密码输入错误次数超过当日最大限制，请联系客户人员"),
	USER_FREEZE(-1004, "用户帐户已被冻结"),
	USER_CANCEL(-1005, "用户帐户已被注销"),
	USER_TYPE_NOT_ADMIN(-1006, "用户不是管理员"),
	PASSWORD_NOT_CHANGE(-1074, "密码没有修改"),
	VERIFICATION_CODE_NOT_NULL(-914,"图片验证码不能为空"),
	VERIFICATION_CODE_NOT_FOUND(-915,"图片验证码还未生成"),
	VERIFICATION_CODE_FORMAT_ERROR(-916,"图片验证码格式不正确"),
	VERIFICATION_CODE_INVALID(-917,"图片验证码不正确"),
	//限制类错误
	REQUEST_EXPIRED(300101, "用户请求过期"),
	USER_CALL_OVERRUN_PER_DAY(300102, "用户日调用量超限"),
	SERVICE_CALL_OVERRUN_PER_SECOND(300103, "服务每秒调用量超限"),
	SERVICE_CALL_OVERRUN_PER_DAY(300104, "服务日调用量超限"),
	USER_NO_CALL_PERMISSIONS(300105, "用户没有调用权限"),
	//调用方错误
	URL_CANNOT_BE_RESOLVED(300201, "url无法解析"),
	MISSING_APIKEY(300202, "请求缺少apikey"),
	MISSING_SIGNATURE(300203, "请求缺少signature"),
	APIKEY_OR_SECRETKEY_IS_NULL(300204, "服务没有取到apikey或secretkey"),
	APIKEY_DOES_NOT_EXIST(300205, "apikey不存在"),
	API_DOES_NOT_EXIST(300206, "api不存在"),
	API_OUT_OF_SERVICE(300207, "api已关闭服务"),
	SERVICE_OVERDUE(300208, "余额不足，请充值"),
	SIGNATURE_NOT_VERIFIED(300209, "未通过签名验证"),
	SERVICE_PROVIDER_RESPONSE_STATUS_ERROR(300210, "服务商响应status非200"),
	REQUEST_PARAMETER_ERROR(300211, "请求参数错误"),
	API_PROCESS_ERROR(300212, "api处理失败"),

	;

	Result(int code, String message) {
		this.code = code;
		this.message = message;
	}

	private int code;
	private String message;

	public int getCode() {
		return code;
	}

//	enum 禁止修改
//	public void setCode(int code) {
//		this.code = code;
//	}

	public String getMessage() {
		return message;
	}

//	public void setMessage(String message) {
//		this.message = message;
//	}

}
