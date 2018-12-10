package com.lms.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.fastjson.JSON;

/**
 * Bean工具类
 * @author Ming
 * @date 2018年11月19日
 */
public class BeanUtils {

	/**
	 * List集合复制
	 * @param <T> 目标泛型
	 * @param <S> 源泛型
	 * @param list 源数据
	 * @param clazz 目标泛型class
	 * @return	List<T>
	 */
	public static <T, S> List<T> copyList(List<S> list,Class<T> clazz) {
	    if (CollectionUtils.isEmpty(list)) {
	        return new ArrayList<T>();
	    }
	    return JSON.parseArray(JSON.toJSONString(list), clazz);
	}

	/**
	 * Map集合复制
	 * @param map 源数据
	 * @return Map<String, Object>
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> copyMap(Map map) {
	    return JSON.parseObject(JSON.toJSONString(map));
	}
}
