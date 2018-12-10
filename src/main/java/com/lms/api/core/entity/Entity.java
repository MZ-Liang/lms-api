package com.lms.api.core.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 实体
 * 
 */
@ApiModel(value="Entity",description="只有id属性")
public class Entity implements Serializable {

	/** SerialVersionUID */
	private static final long serialVersionUID = 1L;

	/** ID */
	@ApiModelProperty("id")
	protected Long id;

	/**
	 * 取得ID
	 * 
	 * @return ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设定ID
	 * 
	 * @param id ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
}