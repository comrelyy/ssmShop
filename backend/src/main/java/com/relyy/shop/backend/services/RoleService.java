package com.relyy.shop.backend.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.relyy.shop.backend.common.Query;
import com.relyy.shop.backend.entity.RoleDO;
import com.relyy.shop.backend.mapper.RoleMapper;
import com.relyy.shop.backend.mapper.RoleMenuMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

	@Transactional
	public boolean save(RoleDO role){
		if (roleMapper.insert(role) > 0) {
			List<Long> menuIds = role.getMenuIds();
			if (CollectionUtils.isNotEmpty(menuIds)) {
				roleMenuMapper.batchInsert(menuIds, role.getRoleId());
			}
		}
		return true;
	}

	@Transactional
	public int update(RoleDO role){
		roleMenuMapper.delByRoleId(role.getRoleId());
		if (CollectionUtils.isNotEmpty(role.getMenuIds())){
			roleMenuMapper.batchInsert(role.getMenuIds(),role.getRoleId());
		}
		return roleMapper.updateById(role);
	}

	@Transactional
	public int remove(Long roleId){
		roleMenuMapper.delByRoleId(roleId);
		return roleMapper.remove(roleId);
	}
	@Transactional
	public int batchRemove(Long[] roleIds){
		roleMenuMapper.batchDelByRole(Arrays.asList(roleIds));
		return roleMapper.deleteBatchIds(Arrays.asList(roleIds));
	}

	public IPage<RoleDO> listByPage(Query<RoleDO> query) {
		IPage page = new Page(query.getPage(),query.getLimit());
		QueryWrapper<RoleDO> wrapper = new QueryWrapper<>(query.getCondition());
		return roleMapper.selectPage(page,wrapper);
	}
}
