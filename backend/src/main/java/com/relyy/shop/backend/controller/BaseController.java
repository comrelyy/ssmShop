package com.relyy.shop.backend.controller;

import com.relyy.shop.backend.entity.UserDO;
import com.relyy.shop.backend.utils.ShiroUtils;

/**
 * @Description
 * @Created by Reeve Cai
 * @Date 2021/12/9
 */
public class BaseController {

	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}
