package com.relyy.shop.backend.controller;

import com.relyy.shop.backend.entity.UserDO;
import com.relyy.shop.backend.utils.ShiroUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Description
 * @Created by Reeve Cai
 * @Date 2021/12/9
 */
public class BaseController {

	public Long generatorUserId(){
		return System.currentTimeMillis();
	}
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public Long getUserId(HttpServletRequest request) {
		return this.getUserId();
//		Object userId = request.getSession().getAttribute("userId");
//		return Objects.isNull(userId) ? null : (Long) userId;
	}


	public String getUsername() {
		return getUser().getUsername();
	}
}
