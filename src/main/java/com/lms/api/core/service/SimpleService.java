package com.lms.api.core.service;

import java.util.List;

/**
 * 单业务服务接口
 * 
 */
public interface SimpleService<K, T> {

	/**
	 * 新增增加信息处理
	 * 
	 * @param entity 信息
	 * @return 处理结果
	 */
	boolean create(T entity);

	/**
	 * 删除信息处理
	 * 
	 * @param pk 主键信息
	 * @return 处理结果
	 */
	boolean delete(K pk);

	/**
	 * 根据主键更新信息处理
	 * 
	 * @param entity 信息
	 * @return 处理结果
	 */
    boolean update(T entity);

	/**
	 * 根据主键更新指定的信息处理
	 * 
	 * @param entity 信息
	 * @return 处理结果
	 */
    boolean updateBySelective(T entity);
    
	/**
	 * 根据主键查询信息处理
	 * 
	 * @param pk 主键信息
	 * @return 信息
	 */
    T get(K pk) throws Exception;

	/**
	 * 根据条件查询信息处理
	 * 
	 * @param condition 查询条件
	 * @return 查询结果
	 */
    List<T> selectByCondition(T condition) throws Exception;

	/**
	 * 根据条件查询信息数量处理
	 * 
	 * @param condition 查询条件
	 * @return 数量
	 */
    Long count(T condition);
}
