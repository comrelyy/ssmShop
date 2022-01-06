package com.relyy.shop.backend.utils;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.relyy.shop.backend.entity.UserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Created by Reeve Cai
 * @Date 2021/12/9
 */
public class ShiroUtils {
	@Autowired
	private static SessionDAO sessionDAO;

	//使用Caffeine构建本地缓存
	private static Cache<Long, Set<String>> userPermsLocalCache = Caffeine.newBuilder()
			.expireAfterWrite(1L, TimeUnit.HOURS)
			.maximumSize(100)
			.build();

	public static Subject getSubjct() {
		return SecurityUtils.getSubject();
	}
	public static UserDO getUser() {
		Object object = getSubjct().getPrincipal();
		return (UserDO)object;
	}
	public static Long getUserId() {
		return getUser().getUserId();
	}


	public static void logout() {
		getSubjct().logout();
	}

	public static void addUserPerms(Long userId,Set<String> perms){
		userPermsLocalCache.put(userId,perms);
	}

	public static void removePermsByUserId(Long userId) {
		userPermsLocalCache.invalidate(userId);
	}

	public static Set<String> getPermsByUserId(Long userId){
		return userPermsLocalCache.getIfPresent(userId);
	}
	public static List<Principal> getPrinciples() {
		List<Principal> principals = null;
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		return principals;
	}
}
