package com.relyy.shop.backend.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.relyy.shop.backend.common.Constant;
import com.relyy.shop.backend.common.Query;
import com.relyy.shop.backend.entity.UserDO;
import com.relyy.shop.backend.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
		QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id",userId);
		return userMapper.selectOne(queryWrapper);

	}

	public UserDO getUserByName(String username,String password){
		QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username",username);
		if (StringUtils.isNotBlank(password)){
			queryWrapper.eq("password",password);
		}
		return userMapper.selectOne(queryWrapper);
	}

	public List<UserDO> list(Map<String, Object> map){
		return userMapper.list(map);
	}

	public IPage<UserDO> listByPage(Query<UserDO> query){
		IPage page = new Page(query.getPage(),query.getLimit());
		QueryWrapper<UserDO> wrapper = new QueryWrapper<>(query.getCondition());
		return userMapper.selectPage(page,wrapper);
	}

	public int count(Map<String, Object> map){
		return userMapper.count(map);
	}

	public int save(UserDO user){
		return userMapper.save(user);
	}

	public int update(UserDO user){
		return userMapper.updateById(user);
	}

	public int updateByUserId(UserDO user){
		return userMapper.update(user);
	}

	public int remove(Long userId){
		return userMapper.updateStatus(userId, Constant.USER_STATU_DEL);
	}

	public int batchRemove(Long[] userIds){
		return userMapper.batchRemove(userIds);
	}

	public int forbidden(Long userId) {
		return userMapper.updateStatus(userId, Constant.USER_STATU_FORBIDDEN);
	}

	public int active(Long userId) {
		return userMapper.updateStatus(userId, Constant.USER_STATU_NOR);
	}
}
