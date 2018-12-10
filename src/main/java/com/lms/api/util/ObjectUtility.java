package com.lms.api.util;

/**
 * 对象处理工具类
 * 
 */
public class ObjectUtility {

	/**
	 * 创建对象实例
	 * 
	 * @param ojbClass Class对象
	 * @return 对象实例
	 */
	public static <T> T createInstance(Class<T> ojbClass) {
		T obj = null;
		try {
			obj = ojbClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
