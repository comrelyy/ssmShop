package com.relyy.shop.backend.controller;

import cn.hutool.core.lang.Tuple;
import com.relyy.shop.backend.common.ResponseResult;
import com.relyy.shop.backend.utils.RandomValidateCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Created by Reeve Cai
 * @Date 2021/12/9
 */
@Slf4j
@Controller
public class LoginController {

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
		log.info("USER:[{}],login success",username);
		//todo
		return ResponseResult.ok();
	}

	@GetMapping({"","/","/index"})
	public String index(){
		//todo
		return "/index";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}
}
