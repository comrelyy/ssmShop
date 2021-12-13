package com.relyy.shop.backend.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.ApiOperation;


import com.relyy.shop.backend.entity.MenuDO;
import com.relyy.shop.backend.services.MenuService;
import com.relyy.shop.backend.common.PageBean;
import com.relyy.shop.backend.common.Query;
import com.relyy.shop.backend.common.ResponseResult;

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
    @RequiresPermissions("backend:menu:menu")
    String menu() {
        return "backend/menu/menu";
    }

    @ApiOperation(value = "获取菜单管理列表", notes = "获取菜单管理列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("backend:menu:menu")
    public ResponseResult<PageBean> list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<MenuDO> menuList = menuService.list(query);
        int total = menuService.count(query);
        PageBean pageBean = new PageBean(menuList, total);
        return ResponseResult.ok().put(pageBean);
    }

    @ApiOperation(value = "新增菜单管理页面", notes = "新增菜单管理页面")
    @GetMapping("/add")
    @RequiresPermissions("backend:menu:add")
    String add() {
        return "backend/menu/add";
    }

    @ApiOperation(value = "修改菜单管理页面", notes = "修改菜单管理页面")
    @GetMapping("/edit/{menuId}")
    @RequiresPermissions("backend:menu:edit")
    String edit(@PathVariable("menuId") Long menuId, Model model) {
            MenuDO menu = menuService.get(menuId);
        model.addAttribute("menu", menu);
        return "backend/menu/edit";
    }

    @ApiOperation(value = "查看菜单管理页面", notes = "查看菜单管理页面")
    @GetMapping("/detail/{menuId}")
    @RequiresPermissions("backend:menu:detail")
    String detail(@PathVariable("menuId") Long menuId, Model model) {
			MenuDO menu = menuService.get(menuId);
        model.addAttribute("menu", menu);
        return "backend/menu/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增菜单管理", notes = "新增菜单管理")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("backend:menu:add")
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
    @RequiresPermissions("backend:menu:edit")
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
    @RequiresPermissions("backend:menu:remove")
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
    @RequiresPermissions("backend:menu:batchRemove")
    public ResponseResult remove(@RequestParam("ids[]") Long[] menuIds) {
            menuService.batchRemove(menuIds);
        return ResponseResult.ok();
    }

}
