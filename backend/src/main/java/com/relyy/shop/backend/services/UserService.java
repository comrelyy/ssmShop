package com.relyy.shop.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.relyy.shop.backend.mapper.UserMapper;
import com.relyy.shop.backend.entity.UserDO;

/**
 * 
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-09 17:04:48
 */
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public UserDO get(Long userId){
		return userMapper.get(userId);
	}

	public List<UserDO> list(Map<String, Object> map){
		return userMapper.list(map);
	}

	public int count(Map<String, Object> map){
		return userMapper.count(map);
	}

	public int save(UserDO user){
		return userMapper.save(user);
	}

	public int update(UserDO user){
		return userMapper.update(user);
	}

	public int remove(Long userId){
		return userMapper.remove(userId);
	}

	public int batchRemove(Long[] userIds){
		return userMapper.batchRemove(userIds);
	}
}
