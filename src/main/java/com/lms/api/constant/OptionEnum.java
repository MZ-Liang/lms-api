package com.lms.api.constant;

/**
 * 操作枚举
 * @author Ming
 * @date 2018年11月26日
 */
public enum OptionEnum {
	/** 新建 */
	NEW("new",0),
	/** 更新 */
	UPDATE("update",1),
	/** 删除 */
	DELETE("delete",2);
	
	private String name;
	private Integer index;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	private OptionEnum(String name,Integer index) {
		this.name=name;
		this.index=index;
	}
	
	/**
	   *   根据index获取枚举name
	 * @param index
	 * @return
	 */
	public static String getName(Integer index) {
		for (OptionEnum optionEnum : OptionEnum.values()) {
			if (optionEnum.getIndex()==index) {
				return optionEnum.getName();
			}
		}
		return null;
	}
}
