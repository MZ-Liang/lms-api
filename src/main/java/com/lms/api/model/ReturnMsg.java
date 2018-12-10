package com.lms.api.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 消息模型
 * @param <T>
 *
 */
@ApiModel(value="ReturnMsg",description="消息响应model")
public class ReturnMsg<T> implements Serializable{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** 返回码(0:异常 1:成功) */
	@ApiModelProperty(value="返回码(0:异常 1:成功)",required=true)
	private int code;
	/** 消息 */
	@ApiModelProperty(value="返回消息",required=true)
	private String msg;
	/** 数据对象 */
	@ApiModelProperty(value="返回数据对象")
	private T data;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public ReturnMsg() {
	}
	
	public ReturnMsg(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public ReturnMsg(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "ReturnMsg [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
}
