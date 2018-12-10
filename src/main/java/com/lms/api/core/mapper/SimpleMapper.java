package com.lms.api.core.mapper;

import java.util.List;


/**
 * 简单数据处理
 * 
 */
public interface SimpleMapper<K, T> {

	/**
	 * 新增信息处理
	 * 
	 * @param record 信息
	 * @return 处理结果
	 */
	int insert(T record);

	/**
	 * 删除信息处理
	 * 
	 * @param id ID
	 * @return 处理结果
	 */
	int deleteByPrimaryKey(K id);

	/**
	 * 根据主键更新信息处理
	 * 
	 * @param record 信息
	 * @return 处理结果
	 */
	int updateByPrimaryKey(T record);

	/**
	 * 根据主键更新指定的信息处理
	 * 
	 * @param record 信息
	 * @return 处理结果
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * 根据主键查询信息处理
	 * 
	 * @param id 主键信息
	 * @return 信息
	 */
	T selectByPrimaryKey(K id);

	/**
	 * 根据条件查询信息处理
	 * 
	 * @param condition 查询条件
	 * @return 查询结果
	 */
	List<T> selectByCondition(T condition);

	/**
	 * 根据条件查询信息数量处理
	 * 
	 * @param condition 查询条件
	 * @return 数量
	 */
	Long countByCondition(T condition);
}
