package com.relyy.shop.backend.services.shiro;

import com.relyy.shop.backend.entity.MenuDO;
import com.relyy.shop.backend.entity.UserDO;
import com.relyy.shop.backend.services.MenuService;
import com.relyy.shop.backend.services.UserService;
import com.relyy.shop.backend.utils.ShiroUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description
 * @Created by Reeve Cai
 * @Date 2022/1/4
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Autowired
	private MenuService menuService;


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		Long userId = ShiroUtils.getUserId();
		Set<String> perms = ShiroUtils.getPermsByUserId(userId);
		if (CollectionUtils.isEmpty(perms)) {
			perms = menuService.getAllMenuByUserId(userId)
					.stream().map(MenuDO::getPerms)
					.collect(Collectors.toSet());
			ShiroUtils.addUserPerms(userId,perms);
		}
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setStringPermissions(perms);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		UserDO userByName = userService.getUserByName(userName, password);
		// 账号不存在
		if (userByName == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		// 密码错误
		if (!password.equals(userByName.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}

		// 账号锁定
		if (userByName.getStatus() == 0) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userByName, password, getName());
		return info;
	}
}
