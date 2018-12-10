package com.lms.api.core.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.api.core.service.ShiroService;

/**
 * 重新加载shiro权限
 * @author Ming
 * @date 2018年11月5日
 */
@Service
public class ShiroServiceImpl implements ShiroService{
	/** shiroFilter工厂 */
	@Autowired
	ShiroFilterFactoryBean shiroFilterFactoryBean;
	
	@Autowired
	//SysPermissionInitService sysPermissionInitService;
	
	/**
	 * 初始化权限
	 */
	public Map<String, String> loadFilterChainDefinitions() {
		// 权限控制map.从数据库获取
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 从数据库获取权限
		/*List<SysPermissionInit> list = sysPermissionInitService.selectAll();
		for (SysPermissionInit sysPermissionInit : list) {
			filterChainDefinitionMap.put(sysPermissionInit.getUrl(),
					sysPermissionInit.getPermissionInit());
		}*/
		
		return filterChainDefinitionMap;
	}
	/**
	 * 重新加载权限
	 */
	public void updatePermission() {
		synchronized (shiroFilterFactoryBean) {
			AbstractShiroFilter shiroFilter = null;
			try {
				shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
						.getObject();
			} catch (Exception e) {
				throw new RuntimeException(
						"get ShiroFilter from shiroFilterFactoryBean error!");
			}
			PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
					.getFilterChainResolver();
			DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
					.getFilterChainManager();
			// 清空老的权限控制
			manager.getFilterChains().clear();
			shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
			shiroFilterFactoryBean
					.setFilterChainDefinitionMap(loadFilterChainDefinitions());
			// 重新构建生成
			Map<String, String> chains = shiroFilterFactoryBean
					.getFilterChainDefinitionMap();
			for (Map.Entry<String, String> entry : chains.entrySet()) {
				String url = entry.getKey();
				String chainDefinition = entry.getValue().trim()
						.replace(" ", "");

				manager.createChain(url, chainDefinition);
			}
			System.out.println("更新权限成功！！");
		}
	}

}
