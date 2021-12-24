package com.relyy.shop.backend.controller;

import com.google.common.collect.Maps;
import com.relyy.shop.backend.common.PageBean;
import com.relyy.shop.backend.common.Query;
import com.relyy.shop.backend.common.ResponseResult;
import com.relyy.shop.backend.entity.RoleDO;
import com.relyy.shop.backend.entity.UserDO;
import com.relyy.shop.backend.services.DictService;
import com.relyy.shop.backend.services.RoleService;
import com.relyy.shop.backend.services.UserService;
import com.relyy.shop.backend.utils.MD5Util;
import com.relyy.shop.backend.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    private static final String prefix = "/backend/user";

    @Autowired
    private UserService userService;
    @Autowired
    private DictService dictService;
    @Autowired
    private RoleService roleService;

    @GetMapping()
    @RequiresPermissions("backend:user:user")
    String user() {
        return "backend/user/user";
    }

    @ApiOperation(value = "获取所有用户列表", notes = "获取列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("backend:user:user")
    public ResponseResult<PageBean> list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<UserDO> userList = userService.list(query);
        int total = userService.count(query);
        PageBean pageBean = new PageBean(userList, total);
        return ResponseResult.ok().put(pageBean);
    }

    @ApiOperation(value = "新增页面", notes = "新增页面")
    @GetMapping("/add")
    @RequiresPermissions("backend:user:add")
    String add(Model model) {
        List<RoleDO> roleDOS = roleService.list(Maps.newHashMap());
        model.addAttribute("roles", roleDOS);
        return "backend/user/add";
    }

    @ApiOperation(value = "修改页面", notes = "修改页面")
    @GetMapping("/edit/{userId}")
    @RequiresPermissions("backend:user:edit")
    String edit(@PathVariable("userId") Long userId, Model model) {
        UserDO user = userService.get(userId);
        List<RoleDO> roleDOS = roleService.list(Maps.newHashMap());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleDOS);
        return "backend/user/edit";
    }

    /**
     * 用户名校验
     */
    @ApiOperation(value = "用户名校验", notes = "用户名校验")
    @ResponseBody
    @PostMapping("/checkUserName")
    @RequiresPermissions("backend:user:checkUserName")
    public boolean checkUserName(@RequestParam("username") String username) {
        UserDO userByName = userService.getUserByName(username,null);
        if (userByName != null) {
            return false;
        }
        return true;
    }
    /**
     * 保存
     */
    @ApiOperation(value = "新增", notes = "新增")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("backend:user:add")
    public ResponseResult save(UserDO user,HttpServletRequest request) {
        if (Objects.isNull(user) || Objects.isNull(user.getUsername())){
            return ResponseResult.error("请补充完整用户信息");
        }
        UserDO userByName = userService.getUserByName(user.getUsername(),null);
        if (userByName != null) {
            return ResponseResult.error("用户名已被占用");
        }
        user.setPassword(MD5Util.encrypt(user.getUsername(),user.getPassword()));
        user.setUserId(generatorUserId());
        Long createUserId = getUserId(request);
        user.setUserIdCreate(createUserId);
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
    public ResponseResult update(UserDO user) {
        if (Objects.isNull(user) && Objects.isNull(user.getUserId())) {
            return ResponseResult.error("用户信息错误");
        }
        UserDO userDO = userService.get(user.getUserId());
        if (userDO != null) {
            user.setId(userDO.getId());
            userService.update(user);
        }else {
            return ResponseResult.error("无法获取用户有效信息");
        }
        return ResponseResult.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("backend:user:remove")
    public ResponseResult remove(@RequestParam("userId") Long userId) {
        if (userService.remove(userId) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 用户禁用
     */
    @ApiOperation(value = "用户禁用", notes = "用户禁用")
    @PostMapping("/forbidden")
    @ResponseBody
    @RequiresPermissions("backend:user:forbidden")
    public ResponseResult forbidden(@RequestParam("userId") Long userId) {
        if (userService.forbidden(userId) > 0) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 用户恢复
     */
    @ApiOperation(value = "用户恢复", notes = "用户恢复")
    @PostMapping("/active")
    @ResponseBody
    @RequiresPermissions("backend:user:active")
    public ResponseResult active(@RequestParam("userId") Long userId) {
        if (userService.active(userId) > 0) {
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

    @GetMapping("/personal")
    String personal(Model model, HttpServletRequest request) {
        Long userId = getUserId(request);
        if (null == userId) return "/login";
        UserDO userDO  = userService.get(userId);
        model.addAttribute("user",userDO);
        model.addAttribute("hobbyList",dictService.getHobbyList(userDO.getHobby()));
        //model.addAttribute("sexList",dictService.getSexList());
        return prefix + "/personal";
    }

    @ResponseBody
    @PostMapping("/resetPwd")
    ResponseResult<String> resetPwd(UserVO userVO, HttpServletRequest request){
        Long userId = getUserId(request);
        if (userId == null) {
            return ResponseResult.error().put("用户未登录");
        }
        UserDO userDO = userService.get(userId);
        //todo 校验输入的旧密码是否正确 暂时可以无条件更新，因为不知道admin的密码
        String encrypt = MD5Util.encrypt(userDO.getUsername(), userVO.getPwdNew());
        userDO.setPassword(encrypt);
        userService.update(userDO);
        return ResponseResult.ok().put("密码修改成功");
    }

    @RequiresPermissions("sys:user:resetPwd")
    @GetMapping("/resetPwd/{id}")
    String resetPwd(@PathVariable("id") Long userId, Model model) {

        UserDO userDO = new UserDO();
        userDO.setUserId(userId);
        model.addAttribute("user", userDO);
        return prefix + "/reset_pwd";
    }

    @RequiresPermissions("sys:user:resetPwd")
    @PostMapping("/adminResetPwd")
    @ResponseBody
    ResponseResult<String> adminResetPwd(UserVO userVO) {
        try{
            Long userId = userVO.getUserDO().getUserId();
            UserDO userDO = userService.get(userId);
            String encrypt = MD5Util.encrypt(userDO.getUsername(), userVO.getPwdNew());
            userDO.setPassword(encrypt);
            userService.update(userDO);
        }catch (Exception e){
            return ResponseResult.error("重置密码失败");
        }
        return ResponseResult.ok().put("密码修改成功");
    }

    @ResponseBody
    @PostMapping("/updatePeronal")
    ResponseResult<String> updatePeronal(UserDO userDO){
        int update = userService.updateByUserId(userDO);
        if (update > 0){
           return ResponseResult.ok().put("个人信息修改成功");
        }
        return ResponseResult.error("个人信息修改成功");
    }
}
