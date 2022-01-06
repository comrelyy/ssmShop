package com.relyy.shop.backend.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.relyy.shop.backend.common.BulidTree;
import com.relyy.shop.backend.common.Query;
import com.relyy.shop.backend.common.Tree;
import com.relyy.shop.backend.entity.DictDO;
import com.relyy.shop.backend.entity.MenuDO;
import com.relyy.shop.backend.mapper.MenuMapper;
import com.relyy.shop.backend.mapper.RoleMenuMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单管理
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:52:17
 */
@Service
public class MenuService {
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private RoleMenuMapper roleMenuMapper;

	public MenuDO get(Long menuId){
		return menuMapper.get(menuId);
	}

	public List<MenuDO> list(Map<String, Object> map){
		return menuMapper.list(map);
	}

	public int count(Map<String, Object> map){
		return menuMapper.count(map);
	}

	public int save(MenuDO menu){
		return menuMapper.save(menu);
	}

	public int update(MenuDO menu){
		return menuMapper.update(menu);
	}

	public int remove(Long menuId){
		return menuMapper.remove(menuId);
	}

	public int batchRemove(Long[] menuIds){
		return menuMapper.batchRemove(menuIds);
	}

	/**
	 * 获取所有菜单
	 * @return
	 */
	public  Tree<MenuDO> listAllMenu(){
		List<Tree<MenuDO>> trees = Lists.newArrayList();
		List<MenuDO> menuDOS = menuMapper.selectList(new QueryWrapper<MenuDO>());
		for (MenuDO sysMenuDO : menuDOS) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuDO> t = BulidTree.build(trees);
		return t;
	}
	/**
	 * 根据用户获取可访问菜单
	 * @param userId
	 * @return
	 */
	public  List<Tree<MenuDO>> listMenuByUserId(Long userId){
		List<MenuDO> menuDOs = menuMapper.listMenuByUserId(userId);
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<MenuDO>> list = BulidTree.buildList(trees, "0");
		return list;
	}

	public  List<MenuDO> getAllMenuByUserId(Long userId) {
		return menuMapper.listAllMenuByUserId(userId);
	}
	/**
	 * 根据角色获取相应的菜单信息
	 * @param roleId
	 * @return
	 */
	public  Tree<MenuDO> listMenuByRoleId(Long roleId){
		List<Long> menuIds = roleMenuMapper.getMenuIdByRoleId(roleId);
		List<Tree<MenuDO>> trees = Lists.newArrayList();
		//if (!CollectionUtils.isEmpty(menuIds)) {
			List<MenuDO> menuDOS = menuMapper.selectList(new QueryWrapper<MenuDO>());
			List<Long> allMenuParentIds = menuDOS.stream().map(MenuDO::getParentId).collect(Collectors.toList());
			List<Long> tempMenuIds = menuIds.stream()
					.filter(menuId -> !allMenuParentIds.contains(menuId))
					.collect(Collectors.toList());
			for (MenuDO sysMenuDO : menuDOS) {
				Tree<MenuDO> tree = new Tree<MenuDO>();
				tree.setId(sysMenuDO.getMenuId().toString());
				tree.setParentId(sysMenuDO.getParentId().toString());
				tree.setText(sysMenuDO.getName());
				Map<String, Object> state = new HashMap<>(16);
				Long menuId = sysMenuDO.getMenuId();
				if (tempMenuIds.contains(menuId)) {
					state.put("selected", true);
				} else {
					state.put("selected", false);
				}
				tree.setState(state);
				trees.add(tree);
			}
		//}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuDO> t = BulidTree.build(trees);
		return t;
	}

	public IPage<MenuDO> listByPage(Query<MenuDO> query) {
		IPage page = new Page(query.getPage(),query.getLimit());
		QueryWrapper<MenuDO> wrapper = new QueryWrapper<>(query.getCondition());
		return menuMapper.selectPage(page,wrapper);
	}
}
