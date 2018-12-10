package com.lms.api.core.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;

import com.lms.api.core.shiro.AuthenticationRealm;
import com.lms.api.util.ShiroUtil;

/**
 * shiro配置类
 * @author Ming
 * @date 2018年10月31日
 */
@Configuration
@PropertySource("classpath:redis.properties")
public class ShiroConfig {
	
	@Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.timeout}")
    private int timeout;
    @Value("${redis.shiro.expire}")
    private int expire;

	/**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在	  
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        // 权限控制map.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        
        shiroFilterFactoryBean.setFilters(filtersMap);
        // 配置不会被拦截的链接 顺序判断
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        // 从数据库获取动态的权限
        // filterChainDefinitionMap.put("/add", "perms[权限添加]");
        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        // 从数据库获取
        /*List<SysPermissionInit> list = sysPermissionInitService.selectAll();

        for (SysPermissionInit sysPermissionInit : list) {
            filterChainDefinitionMap.put(sysPermissionInit.getUrl(),
                    sysPermissionInit.getPermissionInit());
        }*/
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        
        //logout这个拦截器是shiro已经实现好了的。
        filterChainDefinitionMap.put("/logout", "logout");
        //登录请求放行
        filterChainDefinitionMap.put("/login", "anon");
        
        // swagger2 API文档放行
        filterChainDefinitionMap.put("/api", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/null/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs/**", "anon");
        
        //测试放行
        filterChainDefinitionMap.put("/test", "anon");

        //spring icon资源放行
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        
        filterChainDefinitionMap.put("/**", "anon");
        
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/goLogin");
        // 设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    
    
    /**
             * 权限管理，配置主要是Realm的管理认证
     * @return SecurityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(AuthenticationRealm());
        // 自定义缓存实现 使用redis
     	securityManager.setCacheManager(cacheManager());
     	// 自定义session管理 使用redis
     	securityManager.setSessionManager(SessionManager());
        
        return securityManager;
    }
    
    /**
     * 自定义sessionManager
     * @return
     */
    @Bean
    public SessionManager SessionManager() {
    	DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    	sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }
    
    /**
     * 配置shiro redisManager
     * 
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        // 配置缓存过期时间
        redisManager.setExpire(expire);
        redisManager.setTimeout(timeout);
        redisManager.setPassword(password);
        return redisManager;
    }
    
    /**
     * cacheManager 缓存 redis实现
     *
             * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }
    
    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 
             * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }



	/**
	 *  将自己的验证方式加入容器
	 * @return
	 */
	@Bean
	public AuthenticationRealm AuthenticationRealm() {
		AuthenticationRealm authenticationRealm = new AuthenticationRealm();
		return authenticationRealm;
	}


	/**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
	
	/**
	 * 注入封装自定义realm
	 * @param authenticationRealm
	 * @return ShiroUtil对象
	 */
	@Bean
	public ShiroUtil getShiroUtil(AuthenticationRealm authenticationRealm) {
		return new ShiroUtil(authenticationRealm);
	}
	
}