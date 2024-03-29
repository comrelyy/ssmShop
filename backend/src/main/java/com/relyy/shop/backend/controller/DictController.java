package com.relyy.shop.backend.controller;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
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


import com.relyy.shop.backend.entity.DictDO;
import com.relyy.shop.backend.services.DictService;
import com.relyy.shop.backend.common.PageBean;
import com.relyy.shop.backend.common.Query;
import com.relyy.shop.backend.common.ResponseResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 字典表
 *
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-09 14:29:14
 */

@Controller
@RequestMapping("/backend/dict")
public class DictController extends BaseController{
    @Autowired
    private DictService dictService;

    @GetMapping()
    @RequiresPermissions("sys:dict:dict")
    String dict() {
        return "backend/dict/dict";
    }

    @ApiOperation(value = "获取字典表列表", notes = "获取字典表列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("sys:dict:dict")
    public ResponseResult<PageBean> list(@RequestParam Map<String, Object> params) throws Exception{
        //查询列表数据
        Query query = new Query(params,DictDO.class);
        IPage<DictDO> page = dictService.listByPage(query);
        //int total = dictService.count(query);
        PageBean pageBean = new PageBean(page);
        return ResponseResult.ok().put(pageBean);
    }

    @ApiOperation(value = "新增字典表页面", notes = "新增字典表页面")
    @GetMapping("/add")
    @RequiresPermissions("common:dict:add")
    String add() {
        return "backend/dict/add";
    }

    @ApiOperation(value = "修改字典表页面", notes = "修改字典表页面")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("common:dict:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            DictDO dict = dictService.get(id);
        model.addAttribute("dict", dict);
        return "backend/dict/edit";
    }

    @ApiOperation(value = "查看字典表页面", notes = "查看字典表页面")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("common:dict:detail")
    String detail(@PathVariable("id") Long id, Model model) {
			DictDO dict = dictService.get(id);
        model.addAttribute("dict", dict);
        return "backend/dict/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增字典表", notes = "新增字典表")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("common:dict:add")
    public ResponseResult save(DictDO dict,HttpServletRequest request) {
        Long userId = getUserId(request);
        dict.setCreateBy(userId);
        if (dictService.save(dict) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改字典表", notes = "修改字典表")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("common:dict:edit")
    public ResponseResult update(DictDO dict, HttpServletRequest request) {
        Long userId = getUserId(request);
        dict.setUpdateBy(userId);
        dictService.update(dict);
        return ResponseResult.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除字典表", notes = "删除字典表")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("common:dict:remove")
    public ResponseResult remove( Long id) {
        if (dictService.remove(id) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "批量删除字典表", notes = "批量删除字典表")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("sys:dict:batchRemove")
    public ResponseResult remove(@RequestParam("ids[]") Long[] ids) {
            dictService.batchRemove(ids);
        return ResponseResult.ok();
    }

    @ResponseBody
    @GetMapping("/type")
    //@RequiresPermissions("backend:dict:dict")
    public ResponseResult<List<DictDO>> type() {
        return ResponseResult.ok().put(dictService.listType());
    }
}
