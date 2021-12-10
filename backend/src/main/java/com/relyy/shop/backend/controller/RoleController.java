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


import com.relyy.shop.backend.entity.RoleDO;
import com.relyy.shop.backend.services.RoleService;
import com.relyy.shop.backend.common.PageBean;
import com.relyy.shop.backend.common.Query;
import com.relyy.shop.backend.common.ResponseResult;

/**
 * 角色
 *
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:48:40
 */

@Controller
@RequestMapping("/backend/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping()
    @RequiresPermissions("backend:role:role")
    String role() {
        return "backend/role/role";
    }

    @ApiOperation(value = "获取角色列表", notes = "获取角色列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("backend:role:role")
    public ResponseResult<PageBean> list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<RoleDO> roleList = roleService.list(query);
        int total = roleService.count(query);
        PageBean pageBean = new PageBean(roleList, total);
        return new ResponseResult<PageBean>(true,pageBean);
    }

    @ApiOperation(value = "新增角色页面", notes = "新增角色页面")
    @GetMapping("/add")
    @RequiresPermissions("backend:role:add")
    String add() {
        return "backend/role/add";
    }

    @ApiOperation(value = "修改角色页面", notes = "修改角色页面")
    @GetMapping("/edit/{roleId}")
    @RequiresPermissions("backend:role:edit")
    String edit(@PathVariable("roleId") Long roleId, Model model) {
            RoleDO role = roleService.get(roleId);
        model.addAttribute("role", role);
        return "backend/role/edit";
    }

    @ApiOperation(value = "查看角色页面", notes = "查看角色页面")
    @GetMapping("/detail/{roleId}")
    @RequiresPermissions("backend:role:detail")
    String detail(@PathVariable("roleId") Long roleId, Model model) {
			RoleDO role = roleService.get(roleId);
        model.addAttribute("role", role);
        return "backend/role/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增角色", notes = "新增角色")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("backend:role:add")
    public ResponseResult save( RoleDO role) {
        if (roleService.save(role) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改角色", notes = "修改角色")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("backend:role:edit")
    public ResponseResult update( RoleDO role) {
            roleService.update(role);
        return ResponseResult.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("backend:role:remove")
    public ResponseResult remove( Long roleId) {
        if (roleService.remove(roleId) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "批量删除角色", notes = "批量删除角色")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("backend:role:batchRemove")
    public ResponseResult remove(@RequestParam("ids[]") Long[] roleIds) {
            roleService.batchRemove(roleIds);
        return ResponseResult.ok();
    }

}
