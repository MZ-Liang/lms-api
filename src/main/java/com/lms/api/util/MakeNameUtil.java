package com.lms.api.util;

import java.util.UUID;

/**
 * 名称制作工具类
 * @author Ming
 * @date 2018年11月21日
 */
public class MakeNameUtil {
	
	/**
	 * 获取UUID字符串
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 获取(前缀+UUID)字符串
	 * @param prefix 前缀
	 * @return
	 */
	public static String getName(String prefix) {
		return prefix+"-"+getUUID();
	}
	
	/**
	 * 获取(前缀+UUID+后缀)字符串
	 * @param prefix 前缀
	 * @param suffix 后缀
	 * @return
	 */
	public static String getName(String prefix,String suffix) {
		return getName(prefix)+suffix;
	}
	
	/**
	 * 获取(id+"-"+前缀+UUID+后缀)字符串
	 * @param id id
	 * @param prefix 前缀
	 * @param suffix 后缀
	 * @return
	 */
	public static String getName(Long id, String prefix,String suffix) {
		return getName(id+"-"+prefix,suffix);
	}
	
}
