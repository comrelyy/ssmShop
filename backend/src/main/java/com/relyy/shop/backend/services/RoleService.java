package com.relyy.shop.backend.services;

import com.relyy.shop.backend.mapper.RoleMenuMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
	@Autowired
	private RoleMenuMapper roleMenuMapper;

	public RoleDO get(Long roleId){
		return roleMapper.selectById(roleId);
	}

	public List<RoleDO> list(Map<String, Object> map){
		return roleMapper.list(map);
	}

	public int count(Map<String, Object> map){
		return roleMapper.count(map);
	}

	public boolean save(RoleDO role){
		if (roleMapper.insert(role) > 0) {
			List<Long> menuIds = role.getMenuIds();
			if (CollectionUtils.isNotEmpty(menuIds)) {
				roleMenuMapper.batchInsert(menuIds, role.getRoleId());
			}
		}
		return true;
	}

	public int update(RoleDO role){
		roleMenuMapper.delByRoleId(role.getRoleId());
		if (CollectionUtils.isNotEmpty(role.getMenuIds())){
			roleMenuMapper.batchInsert(role.getMenuIds(),role.getRoleId());
		}
		return roleMapper.updateById(role);
	}

	public int remove(Long roleId){
		roleMenuMapper.delByRoleId(roleId);
		return roleMapper.remove(roleId);
	}

	public int batchRemove(Long[] roleIds){
		roleMenuMapper.batchDelByRole(Arrays.asList(roleIds));
		return roleMapper.deleteBatchIds(Arrays.asList(roleIds));
	}
}
