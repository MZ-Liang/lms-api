package com.lms.api.core.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.lms.api.constant.StatusConstant;
import com.lms.api.core.exception.NoUserException;
import com.lms.api.core.exception.PasswordErrorException;
import com.lms.api.entity.user.UserEntity;
import com.lms.api.service.user.UserService;

/**
 * 实现AuthorizingRealm接口用户用户认证
 * 
 * @author Ming
 * @date 2018年10月31日
 */
public class AuthenticationRealm extends AuthorizingRealm {
	/** 用户服务 */
	@Autowired
	private UserService userService;

	/**
	 * 自定义获取授权信息
	 * 
	 * @param principalCollection 责任人
	 * @return AuthorizationInfo
	 */
	public AuthorizationInfo getMyAuthorizationInfo(PrincipalCollection principalCollection) {
		AuthorizationInfo authorizationInfo = getAuthorizationInfo(principalCollection);
		return authorizationInfo;
	}

	/**
	 * 角色权限和对应权限添加
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 获取登录用户名
		UserEntity userEntity = (UserEntity) principalCollection.getPrimaryPrincipal();
		// 添加角色和权限
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 添加角色
		simpleAuthorizationInfo.addRoles(userService.findRoleName(userEntity.getId()));
		// 添加权限
		simpleAuthorizationInfo.addStringPermissions(userService.findPermissionPath(userEntity.getId()));
		return simpleAuthorizationInfo;
	}

	/**
	 * 用户认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// 获取用户信息
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

		if (token == null) {
			throw new UnknownAccountException();
		}

		// 用户登录信息
		String userName = token.getUsername();
		String password = String.valueOf(token.getPassword());

		// 数据库用户信息
		UserEntity userEntity = userService.findByUserName(userName,StatusConstant.NORMAL);

		if (userEntity == null || userEntity.getStatus()==StatusConstant.DELETED) {
			throw new NoUserException();
		} else if (!userEntity.getPassword().equals(password)) {
			throw new PasswordErrorException();
		} else {
			String userPassword = userEntity.getPassword();
			// 这里验证authenticationToken和simpleAuthenticationInfo的信息
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userEntity, userPassword,
					getName());
			return simpleAuthenticationInfo;
		}
	}

	/**
	 * 重写方法,清除当前用户的的 授权缓存
	 * 
	 * @param principals
	 */
	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 重写方法，清除当前用户的 认证缓存
	 * 
	 * @param principals
	 */
	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	/**
	 * 自定义方法：清除所有 授权缓存
	 */
	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	/**
	 * 自定义方法：清除所有 认证缓存
	 */
	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	/**
	 * 自定义方法：清除所有的 认证缓存 和 授权缓存
	 */
	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
