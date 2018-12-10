package com.lms.api.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * HttpClient返回结果
 * @author Ming
 * @date 2018年11月14日
 */
@ApiModel(value="HttpResult",description="HttpClient返回结果")
public class HttpResult implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  响应的状态码
	 */
	@ApiModelProperty("响应的状态码")
	private Integer code;
	 
	/**
	 *  响应体
	 */
	@ApiModelProperty("响应体")
	private String body;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public HttpResult() {
		super();
	}

	public HttpResult(Integer code, String body) {
		super();
		this.code = code;
		this.body = body;
	}

	@Override
	public String toString() {
		return "HttpResult [code=" + code + ", body=" + body + "]";
	} 
		
}
