package com.relyy.shop.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.relyy.shop.backend.mapper.RoleMapper;
import com.relyy.shop.backend.entity.RoleDO;

/**
 * 角色
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:48:40
 */
@Service
public class RoleService {
	@Autowired
	private RoleMapper roleMapper;

	public RoleDO get(Long roleId){
		return roleMapper.selectById(roleId);
	}

	public List<RoleDO> list(Map<String, Object> map){
		return roleMapper.list(map);
	}

	public int count(Map<String, Object> map){
		return roleMapper.count(map);
	}

	public int save(RoleDO role){
		return roleMapper.save(role);
	}

	public int update(RoleDO role){
		return roleMapper.update(role);
	}

	public int remove(Long roleId){
		return roleMapper.remove(roleId);
	}

	public int batchRemove(Long[] roleIds){
		return roleMapper.batchRemove(roleIds);
	}
}
