package com.relyy.shop.backend.controller;

import cn.hutool.core.lang.Tuple;
import com.relyy.shop.backend.common.ResponseResult;
import com.relyy.shop.backend.common.Tree;
import com.relyy.shop.backend.entity.MenuDO;
import com.relyy.shop.backend.entity.UserDO;
import com.relyy.shop.backend.services.MenuService;
import com.relyy.shop.backend.services.UserService;
import com.relyy.shop.backend.utils.MD5Util;
import com.relyy.shop.backend.utils.RandomValidateCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @Description
 * @Created by Reeve Cai
 * @Date 2021/12/9
 */
@Slf4j
@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;

	@GetMapping("/login")
	public String login(Model model){
		return "login";
	}

	@GetMapping("/getVerify")
	public void getVerify(HttpServletRequest request, HttpServletResponse response){
		try{
			//设置返回数据的格式
			response.setContentType("image/jpeg");
			//设置响应头，告诉浏览器不需要缓存
			response.setHeader("Pragma","No-cache");
			response.setHeader("Cache-Control","no-cache");
			//设置相应过期时间
			response.setDateHeader("Expires",0);

			HttpSession session = request.getSession();

			Tuple randStrAndImage = RandomValidateCodeUtil.getRandCode();
			session.removeAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
			session.setAttribute(RandomValidateCodeUtil.RANDOMCODEKEY,randStrAndImage.get(0));
			//将图片写入客户端
			ImageIO.write(randStrAndImage.get(1),"JPEG",response.getOutputStream());
		} catch(Exception e){
		    log.error("",e);
		}
	}

	@ResponseBody
	@PostMapping("/login")
	public ResponseResult<String> signIn(String username, String password,String verify,HttpServletRequest request){
		log.info("USER:[{}],verify:[{}],request login",username,verify);
		final HttpSession session = request.getSession();
		try {
			if (!StringUtils.equals(verify,(String)session.getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY))) {
				return ResponseResult.error("请输入正确的验证码");
			}
			UserDO userByName = userService.getUserByName(username, MD5Util.encrypt(username,password));
			if (Objects.isNull(userByName)){
				return ResponseResult.error("用户不存在");
			}
			session.setAttribute("userId",userByName.getUserId());
		}catch (Exception e){
			e.printStackTrace();
			return ResponseResult.error("登录异常");
		}
		return ResponseResult.ok();
	}

	@GetMapping({"","/","/index"})
	public String index(Model model,HttpServletRequest request){
		Object userId = request.getSession().getAttribute("userId");
		if (Objects.isNull(userId)){
			return "/login";
		}
		UserDO userDO = userService.get((Long) userId);
		model.addAttribute("name",userDO.getName());
		//todo
		model.addAttribute("picUrl","/img/a8.jpg");
		List<Tree<MenuDO>> menus = menuService.listMenuByUserId(userDO.getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("username", userDO.getUsername());
		//todo
		return "/index";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

	@GetMapping("/logout")
	String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("userId");
		return "redirect:/login";
	}
}
