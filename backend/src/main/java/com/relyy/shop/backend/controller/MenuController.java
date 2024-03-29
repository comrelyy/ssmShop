package com.relyy.shop.backend.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relyy.shop.backend.common.*;
import com.relyy.shop.backend.entity.MenuDO;
import com.relyy.shop.backend.services.MenuService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

/**
 * 菜单管理
 *
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:52:17
 */

@Controller
@RequestMapping("/backend/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping()
    @RequiresPermissions("sys:menu:menu")
    String menu() {
        return "backend/menu/menu";
    }

    @ApiOperation(value = "获取菜单管理列表", notes = "获取菜单管理列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:menu")
    public ResponseResult<PageBean> list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        IPage<MenuDO> page = menuService.listByPage(query);
        //int total = dictService.count(query);
        PageBean pageBean = new PageBean(page);
        return ResponseResult.ok().put(pageBean);
    }

    @ApiOperation(value = "新增菜单管理页面", notes = "新增菜单管理页面")
    @GetMapping("/add/{menuId}")
    @RequiresPermissions("sys:menu:add")
    public String add(@PathVariable("menuId") Long menuId, Model model) {
        if (Objects.isNull(menuId)) return "/error";
        model.addAttribute("parentId",menuId);
        if (Objects.equals(menuId, Constant.ROOT_MENU_ID)){
            model.addAttribute("pName", Constant.ROOT_MENU_NAME);
        }else {
            MenuDO menu = menuService.get(menuId);
            if (Objects.isNull(menu)) return "/error";
            model.addAttribute("pName",menu.getName());
        }
        return "backend/menu/add";
    }

    @ApiOperation(value = "修改菜单管理页面", notes = "修改菜单管理页面")
    @GetMapping("/edit/{menuId}")
    @RequiresPermissions("sys:menu:edit")
    String edit(@PathVariable("menuId") Long menuId, Model model) {
        MenuDO menu = menuService.get(menuId);
        Long parentId = menu.getParentId();
        if (Objects.equals(parentId, Constant.ROOT_MENU_ID)){
            model.addAttribute("pName", Constant.ROOT_MENU_NAME);
        }else {
            MenuDO pMenu = menuService.get(parentId);
            model.addAttribute("pName", pMenu.getName());
        }
        model.addAttribute("menu", menu);
        return "backend/menu/edit";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增菜单管理", notes = "新增菜单管理")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("sys:menu:add")
    public ResponseResult save( MenuDO menu) {
        if (menuService.save(menu) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改菜单管理", notes = "修改菜单管理")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("sys:menu:edit")
    public ResponseResult update( MenuDO menu) {
            menuService.update(menu);
        return ResponseResult.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除菜单管理", notes = "删除菜单管理")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("sys:menu:remove")
    public ResponseResult remove( Long menuId) {
        if (menuService.remove(menuId) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "批量删除菜单管理", notes = "批量删除菜单管理")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("sys:menu:batchRemove")
    public ResponseResult remove(@RequestParam("ids[]") Long[] menuIds) {
            menuService.batchRemove(menuIds);
        return ResponseResult.ok();
    }

    /**
     * 获取所有菜单
     */
    @ApiOperation(value = "获取所有菜单", notes = "获取所有菜单")
    @GetMapping("/getMenuTree")
    @ResponseBody
    public ResponseResult<Tree<MenuDO>> getMenuTree() {
        Tree<MenuDO> menuDOTree = menuService.listAllMenu();
        return ResponseResult.ok().put(menuDOTree);
    }

    /**
     * 根据角色获取菜单
     */
    @ApiOperation(value = "根据角色获取菜单", notes = "根据角色获取菜单")
    @GetMapping("/getMenuTree/{roleId}")
    @ResponseBody
    public ResponseResult<Tree<MenuDO>> getMenuTreeByRole(@PathVariable("roleId") Long roleId) {
        Tree<MenuDO> menuDOTree = menuService.listMenuByRoleId(roleId);
        return ResponseResult.ok().put(menuDOTree);
    }
}
