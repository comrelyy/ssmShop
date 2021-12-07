package com.relyy.shop.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description 自定义生成器
 * @Created by Reeve Cai
 * @Date 2021/12/7
 */
@Controller("/backend/generator")
public class GeneratorController {

	private String perfix = "common/generator";

	@GetMapping()
	public String generator(){
		return perfix + "/list";
	}
}
