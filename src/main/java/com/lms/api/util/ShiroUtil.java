package com.lms.api.util;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;

import com.lms.api.core.exception.NoLoginExceprion;
import com.lms.api.core.shiro.AuthenticationRealm;
import com.lms.api.entity.user.UserEntity;

/**
 * shiro工具类，已注入实体
 * @author Ming
 * @date 2018年10月31日
 */
public class ShiroUtil {
	/**
	 * 自定义realm
	 */
	private AuthenticationRealm authenticationRealm;
	
	/**
	 * 构造器
	 * @param authenticationRealm 自定义realm
	 */
	public ShiroUtil(AuthenticationRealm authenticationRealm) {
		this.authenticationRealm=authenticationRealm;
	}
	
	/**
	 * 当前用户是否包含此权限
	 * @param permission 权限
	 * @return boolean
	 */
	public static boolean hasPermission(String permission) {
		return SecurityUtils.getSubject().isPermitted(permission);
	}
	
	/**
	 * 当前用户是否拥有此角色
	 * @param roleName 角色名
	 * @return boolean
	 */
	public static boolean hasRole(String roleName) {
		return SecurityUtils.getSubject().hasRole(roleName);
	}
	
	/**
	 * 获取登录请求者
	 * @return principal
	 */
	public static Object getPrincipal() {
		Object object = SecurityUtils.getSubject().getPrincipal();
		//为空，抛出未登录异常
		if (object==null) {
			throw new NoLoginExceprion();
		}
		return object;
	}
	
	/**
	 * 获取当前用户的id
	 * @return userId
	 */
	public static Long getUserId() {
		return ((UserEntity) getPrincipal()).getId();
	}
	
	/**
	 * 获取当前用户
	 * @return
	 */
	public static UserEntity getUserEntity() {
		return (UserEntity) getPrincipal();
	}
	
	/**
	 * 获取当前用户的session keys
	 * 
	 * @return {[org.apache.shiro.subject.support.DefaultSubjectContext_AUTHENTICATED_SESSION_KEY:true],
	 * 			[org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY:UserEntity]}
	 * 
	 */
	public static Collection<Object> getAttributeKeys() {
		return SecurityUtils.getSubject().getSession().getAttributeKeys();
	}
	
	/**
	 * 获取授权信息
	 * @return
	 */
	public AuthorizationInfo getAuthorizationInfo() {
		return authenticationRealm.getMyAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}
	
	/**
	 * 获取当前用户的所有权限
	 * @return
	 */
	public Set<String> getPermissions() {
		return (Set<String>) getAuthorizationInfo().getStringPermissions();
	}
	
	/**
	 * 获取当前用户的角色
	 * @return
	 */
	public Set<String> getRoles() {
		return (Set<String>) getAuthorizationInfo().getRoles();
	}
}
