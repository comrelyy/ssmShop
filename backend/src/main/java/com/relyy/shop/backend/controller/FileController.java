package com.relyy.shop.backend.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relyy.shop.backend.common.PageBean;
import com.relyy.shop.backend.common.Query;
import com.relyy.shop.backend.common.ResponseResult;
import com.relyy.shop.backend.entity.FileDO;
import com.relyy.shop.backend.services.FileService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 上传文件信息
 *
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2022-01-25 17:15:38
 */

@Controller
@RequestMapping("/backend/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping()
    @RequiresPermissions("backend:file:file")
    String file() {
        return "backend/file/file";
    }

    @ApiOperation(value = "获取上传文件信息列表", notes = "获取上传文件信息列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("backend:file:file")
    public ResponseResult<PageBean> list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
	    IPage<FileDO> page = fileService.listByPage(query);
        PageBean pageBean = new PageBean(page);
        return ResponseResult.ok().put(pageBean);
    }

    @ApiOperation(value = "新增上传文件信息页面", notes = "新增上传文件信息页面")
    @GetMapping("/add")
    @RequiresPermissions("backend:file:add")
    String add() {
        return "backend/file/add";
    }

    @ApiOperation(value = "修改上传文件信息页面", notes = "修改上传文件信息页面")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("backend:file:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            FileDO file = fileService.get(id);
        model.addAttribute("file", file);
        return "backend/file/edit";
    }

    @ApiOperation(value = "查看上传文件信息页面", notes = "查看上传文件信息页面")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("backend:file:detail")
    String detail(@PathVariable("id") Long id, Model model) {
			FileDO file = fileService.get(id);
        model.addAttribute("file", file);
        return "backend/file/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增上传文件信息", notes = "新增上传文件信息")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("backend:file:add")
    public ResponseResult save( FileDO file) {
        if (fileService.save(file) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改上传文件信息", notes = "修改上传文件信息")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("backend:file:edit")
    public ResponseResult update( FileDO file) {
            fileService.update(file);
        return ResponseResult.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除上传文件信息", notes = "删除上传文件信息")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("backend:file:remove")
    public ResponseResult remove( Long id) {
        if (fileService.remove(id) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "批量删除上传文件信息", notes = "批量删除上传文件信息")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("backend:file:batchRemove")
    public ResponseResult remove(@RequestParam("ids[]") Long[] ids) {
            fileService.batchRemove(ids);
        return ResponseResult.ok();
    }

}
