package com.relyy.shop.backend.services;

import com.relyy.shop.backend.common.BulidTree;
import com.relyy.shop.backend.common.Tree;
import com.relyy.shop.backend.entity.MenuDO;
import com.relyy.shop.backend.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
