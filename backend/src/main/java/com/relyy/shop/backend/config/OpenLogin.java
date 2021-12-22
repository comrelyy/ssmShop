package com.relyy.shop.backend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description 项目启动完成时自动打开登录页
 * @Created by Reeve Cai
 * @Date 2021/12/22
 */
@Slf4j
@Component
public class OpenLogin implements CommandLineRunner {
	private static boolean isopen = false;
	@Override
	public void run(String... args) throws Exception {
		log.info("自动加载登录页");
		try{
			if (!isopen) {
				Process exec = Runtime.getRuntime().exec("cmd /c start http://127.0.0.1:8071/login");
				isopen = true;
			}
		} catch(Exception e){
		    log.error("登录页打开失败",e);
		}
	}
}
