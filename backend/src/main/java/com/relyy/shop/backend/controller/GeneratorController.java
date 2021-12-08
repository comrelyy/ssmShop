package com.relyy.shop.backend.controller;

import com.relyy.shop.backend.common.ResponseResult;
import com.relyy.shop.backend.services.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Description 自定义生成器
 * @Created by Reeve Cai
 * @Date 2021/12/7
 */
@Controller("/backend/generator")
public class GeneratorController {

	private String perfix = "common/generator";

	@Autowired
	private GeneratorService generatorService;

	@GetMapping()
	public String generator(){
		return perfix + "/list";
	}

	@ResponseBody
	@GetMapping("/list")
	public List<Map<String,Object>> list(String tableName){
		return generatorService.list(tableName);
	}

	@ResponseBody
	@PostMapping("/genCode")
	public ResponseResult genCode(String tableName) {
		String[] tableNames = new String[]{tableName};
		generatorService.generatorCode(tableNames);
		return ResponseResult.ok("代码生成成功，请到本地项目中查看！");
	}
}
