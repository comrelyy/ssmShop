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


import com.relyy.shop.backend.entity.DeptDO;
import com.relyy.shop.backend.services.DeptService;
import com.relyy.shop.backend.common.PageBean;
import com.relyy.shop.backend.common.Query;
import com.relyy.shop.backend.common.ResponseResult;

/**
 * 部门管理
 *
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:55:05
 */

@Controller
@RequestMapping("/backend/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping()
    @RequiresPermissions("backend:dept:dept")
    String dept() {
        return "backend/dept/dept";
    }

    @ApiOperation(value = "获取部门管理列表", notes = "获取部门管理列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("backend:dept:dept")
    public ResponseResult<PageBean> list(@RequestParam Map<String, Object> params) {
        //查询列表数据
//        Query query = new Query(params);
//        List<DeptDO> deptList = deptService.list(query);
//        int total = deptService.count(query);
//        PageBean pageBean = new PageBean(deptList, total);
        return ResponseResult.ok();//.put(pageBean);
    }

    @ApiOperation(value = "新增部门管理页面", notes = "新增部门管理页面")
    @GetMapping("/add")
    @RequiresPermissions("backend:dept:add")
    String add() {
        return "backend/dept/add";
    }

    @ApiOperation(value = "修改部门管理页面", notes = "修改部门管理页面")
    @GetMapping("/edit/{deptId}")
    @RequiresPermissions("backend:dept:edit")
    String edit(@PathVariable("deptId") Long deptId, Model model) {
            DeptDO dept = deptService.get(deptId);
        model.addAttribute("dept", dept);
        return "backend/dept/edit";
    }

    @ApiOperation(value = "查看部门管理页面", notes = "查看部门管理页面")
    @GetMapping("/detail/{deptId}")
    @RequiresPermissions("backend:dept:detail")
    String detail(@PathVariable("deptId") Long deptId, Model model) {
			DeptDO dept = deptService.get(deptId);
        model.addAttribute("dept", dept);
        return "backend/dept/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增部门管理", notes = "新增部门管理")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("backend:dept:add")
    public ResponseResult save( DeptDO dept) {
        if (deptService.save(dept) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改部门管理", notes = "修改部门管理")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("backend:dept:edit")
    public ResponseResult update( DeptDO dept) {
            deptService.update(dept);
        return ResponseResult.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除部门管理", notes = "删除部门管理")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("backend:dept:remove")
    public ResponseResult remove( Long deptId) {
        if (deptService.remove(deptId) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "批量删除部门管理", notes = "批量删除部门管理")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("backend:dept:batchRemove")
    public ResponseResult remove(@RequestParam("ids[]") Long[] deptIds) {
            deptService.batchRemove(deptIds);
        return ResponseResult.ok();
    }

}
