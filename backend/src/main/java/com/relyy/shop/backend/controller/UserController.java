package com.relyy.shop.backend.controller;

import com.relyy.shop.backend.common.PageBean;
import com.relyy.shop.backend.common.Query;
import com.relyy.shop.backend.common.ResponseResult;
import com.relyy.shop.backend.entity.UserDO;
import com.relyy.shop.backend.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-09 17:04:48
 */

@Controller
@RequestMapping("/backend/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @GetMapping()
    @RequiresPermissions("backend:user:user")
    String user() {
        return "backend/user/user";
    }

    @ApiOperation(value = "获取列表", notes = "获取列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("backend:user:user")
    public ResponseResult<PageBean> list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<UserDO> userList = userService.list(query);
        int total = userService.count(query);
        PageBean pageBean = new PageBean(userList, total);
        return new ResponseResult<PageBean>(true, pageBean);
    }

    @ApiOperation(value = "新增页面", notes = "新增页面")
    @GetMapping("/add")
    @RequiresPermissions("backend:user:add")
    String add() {
        return "backend/user/add";
    }

    @ApiOperation(value = "修改页面", notes = "修改页面")
    @GetMapping("/edit/{userId}")
    @RequiresPermissions("backend:user:edit")
    String edit(@PathVariable("userId") Long userId, Model model) {
            UserDO user = userService.get(userId);
        model.addAttribute("user", user);
        return "backend/user/edit";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("backend:user:add")
    public ResponseResult save( UserDO user) {
        if (userService.save(user) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改", notes = "修改")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("backend:user:edit")
    public ResponseResult update( UserDO user) {
            userService.update(user);
        return ResponseResult.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("backend:user:remove")
    public ResponseResult remove( Long userId) {
        if (userService.remove(userId) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "批量删除", notes = "批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("backend:user:batchRemove")
    public ResponseResult remove(@RequestParam("ids[]") Long[] userIds) {
            userService.batchRemove(userIds);
        return ResponseResult.ok();
    }

}
