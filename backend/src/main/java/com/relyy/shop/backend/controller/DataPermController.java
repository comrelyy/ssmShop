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


import com.relyy.shop.backend.entity.DataPermDO;
import com.relyy.shop.backend.services.DataPermService;
import com.relyy.shop.backend.common.PageBean;
import com.relyy.shop.backend.common.Query;
import com.relyy.shop.backend.common.ResponseResult;

/**
 * 数据权限管理
 *
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:57:22
 */

@Controller
@RequestMapping("/backend/dataPerm")
public class DataPermController {
    @Autowired
    private DataPermService dataPermService;

    @GetMapping()
    @RequiresPermissions("backend:dataPerm:dataPerm")
    String dataPerm() {
        return "backend/dataPerm/dataPerm";
    }

    @ApiOperation(value = "获取数据权限管理列表", notes = "获取数据权限管理列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("backend:dataPerm:dataPerm")
    public ResponseResult<PageBean> list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<DataPermDO> dataPermList = dataPermService.list(query);
        int total = dataPermService.count(query);
        PageBean pageBean = new PageBean(dataPermList, total);
        return ResponseResult.ok().put(pageBean);
    }

    @ApiOperation(value = "新增数据权限管理页面", notes = "新增数据权限管理页面")
    @GetMapping("/add")
    @RequiresPermissions("backend:dataPerm:add")
    String add() {
        return "backend/dataPerm/add";
    }

    @ApiOperation(value = "修改数据权限管理页面", notes = "修改数据权限管理页面")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("backend:dataPerm:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            DataPermDO dataPerm = dataPermService.get(id);
        model.addAttribute("dataPerm", dataPerm);
        return "backend/dataPerm/edit";
    }

    @ApiOperation(value = "查看数据权限管理页面", notes = "查看数据权限管理页面")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("backend:dataPerm:detail")
    String detail(@PathVariable("id") Long id, Model model) {
			DataPermDO dataPerm = dataPermService.get(id);
        model.addAttribute("dataPerm", dataPerm);
        return "backend/dataPerm/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增数据权限管理", notes = "新增数据权限管理")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("backend:dataPerm:add")
    public ResponseResult save( DataPermDO dataPerm) {
        if (dataPermService.save(dataPerm) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改数据权限管理", notes = "修改数据权限管理")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("backend:dataPerm:edit")
    public ResponseResult update( DataPermDO dataPerm) {
            dataPermService.update(dataPerm);
        return ResponseResult.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除数据权限管理", notes = "删除数据权限管理")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("backend:dataPerm:remove")
    public ResponseResult remove( Long id) {
        if (dataPermService.remove(id) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "批量删除数据权限管理", notes = "批量删除数据权限管理")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("backend:dataPerm:batchRemove")
    public ResponseResult remove(@RequestParam("ids[]") Long[] ids) {
            dataPermService.batchRemove(ids);
        return ResponseResult.ok();
    }

}
