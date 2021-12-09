package com.relyy.shop.backend.utils;

import com.relyy.shop.backend.entity.UserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

/**
 * @Description
 * @Created by Reeve Cai
 * @Date 2021/12/9
 */
public class ShiroUtils {
	@Autowired
	private static SessionDAO sessionDAO;

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

	public static List<Principal> getPrinciples() {
		List<Principal> principals = null;
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		return principals;
	}
}
