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


import com.relyy.shop.backend.entity.DictDO;
import com.relyy.shop.backend.services.DictService;
import com.relyy.shop.backend.common.PageBean;
import com.relyy.shop.backend.common.Query;
import com.relyy.shop.backend.common.ResponseResult;

/**
 * 字典表
 *
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-09 14:29:14
 */

@Controller
@RequestMapping("/backend/dict")
public class DictController {
    @Autowired
    private DictService dictService;

    @GetMapping()
    @RequiresPermissions("backend:dict:dict")
    String dict() {
        return "backend/dict/dict";
    }

    @ApiOperation(value = "获取字典表列表", notes = "获取字典表列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("backend:dict:dict")
    public ResponseResult<PageBean> list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<DictDO> dictList = dictService.list(query);
        int total = dictService.count(query);
        PageBean pageBean = new PageBean(dictList, total);
        return new ResponseResult<PageBean>(true,pageBean);
    }

    @ApiOperation(value = "新增字典表页面", notes = "新增字典表页面")
    @GetMapping("/add")
    @RequiresPermissions("backend:dict:add")
    String add() {
        return "backend/dict/add";
    }

    @ApiOperation(value = "修改字典表页面", notes = "修改字典表页面")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("backend:dict:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            DictDO dict = dictService.get(id);
        model.addAttribute("dict", dict);
        return "backend/dict/edit";
    }

    @ApiOperation(value = "查看字典表页面", notes = "查看字典表页面")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("backend:dict:detail")
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
    @RequiresPermissions("backend:dict:add")
    public ResponseResult save( DictDO dict) {
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
    @RequiresPermissions("backend:dict:edit")
    public ResponseResult update( DictDO dict) {
            dictService.update(dict);
        return ResponseResult.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除字典表", notes = "删除字典表")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("backend:dict:remove")
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
    @RequiresPermissions("backend:dict:batchRemove")
    public ResponseResult remove(@RequestParam("ids[]") Long[] ids) {
            dictService.batchRemove(ids);
        return ResponseResult.ok();
    }

}
