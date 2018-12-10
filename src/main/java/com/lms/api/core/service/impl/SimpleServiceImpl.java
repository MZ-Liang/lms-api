package com.lms.api.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;

import com.lms.api.core.entity.Entity;
import com.lms.api.core.mapper.SimpleMapper;
import com.lms.api.util.ObjectUtility;

/**
 * 单业务服务处理
 * 
 */
public abstract class SimpleServiceImpl<K, T>{
	
	/**
	 * 新增增加信息处理
	 * 
	 * @param entity 信息
	 * @return 处理结果
	 */
	@SuppressWarnings("unchecked")
	public boolean create(T entity) {
		Entity e = (Entity) entity;
		return getMapper().insert((T) e) > 0;
	}

	/**
	 * 删除信息处理
	 * 
	 * @param pk 主键信息
	 * @return 处理结果
	 */
	public boolean delete(K pk) {
		return getMapper().deleteByPrimaryKey(pk) > 0;
	}

	/**
	 * 根据主键更新信息处理
	 * 
	 * @param entity 信息
	 * @return 处理结果
	 */
	public boolean update(T entity) {
		return getMapper().updateByPrimaryKey(entity) > 0;
	}
	
	/**
	 * 根据主键更新指定的信息处理
	 * 
	 * @param entity 信息
	 * @return 处理结果
	 */
	public boolean updateBySelective(T entity) {
		return getMapper().updateByPrimaryKeySelective(entity) > 0;
	}
    
	/**
	 * 根据主键查询信息处理
	 * 
	 * @param pk 主键信息
	 * @return 信息
	 */
	@SuppressWarnings("unchecked")
	public T get(K pk) throws Exception {
		T t = getMapper().selectByPrimaryKey(pk);
		if (t == null) {
			return null;
		}
		T ret = (T) ObjectUtility.createInstance(t.getClass());
		BeanUtils.copyProperties(ret, t);
		return ret;
	}

	/**
	 * 根据条件查询信息处理
	 * 
	 * @param condition 查询条件
	 * @return 查询结果
	 */
	@SuppressWarnings("unchecked")
	public List<T> selectByCondition(T condition) throws Exception {
		List<T> list = getMapper().selectByCondition(condition);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<T> retList = new ArrayList<T>();
		T ret = null;
		for (T item : list) {
			ret = (T) ObjectUtility.createInstance(item.getClass());
			BeanUtils.copyProperties(ret, item);
			retList.add(ret);
		}
		return retList;
	}

	/**
	 * 根据条件查询信息数量处理
	 * 
	 * @param condition 查询条件
	 * @return 数量
	 */
	public Long count(T condition) {
		return getMapper().countByCondition(condition);
	}

	/**
	 * 取得数据处理对象
	 * 
	 * @return 数据处理对象
	 */
	protected abstract SimpleMapper<K, T> getMapper();
}
