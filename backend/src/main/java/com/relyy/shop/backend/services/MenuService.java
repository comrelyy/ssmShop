package com.relyy.shop.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.relyy.shop.backend.mapper.MenuMapper;
import com.relyy.shop.backend.entity.MenuDO;

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
}
