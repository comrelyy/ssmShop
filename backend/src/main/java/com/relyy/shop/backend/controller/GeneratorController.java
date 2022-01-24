package com.relyy.shop.backend.controller;

import com.google.common.collect.Maps;
import com.relyy.shop.backend.common.PageBean;
import com.relyy.shop.backend.common.ResponseResult;
import com.relyy.shop.backend.entity.GenColumnsDO;
import com.relyy.shop.backend.entity.GenTableDO;
import com.relyy.shop.backend.services.GeneratorService;
import com.relyy.shop.backend.utils.GeneratorUtil;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description 自定义生成器
 * @Created by Reeve Cai
 * @Date 2021/12/7
 */
@Controller
@RequestMapping("/common/generator")
public class GeneratorController extends BaseController {

	private String perfix = "common/generator";

	@Autowired
	private GeneratorService generatorService;

	@GetMapping()
	public String generator(){
		return perfix + "/list";
	}

	@ResponseBody
	@GetMapping("/list")
	public ResponseResult<List<GenTableDO>> list(String tableName){
		return ResponseResult.ok().put(generatorService.list(tableName));
	}

	@GetMapping("/addTable")
	public String addTable(){
		return perfix + "/addTable";
	}

	@ResponseBody
	@PostMapping("/saveTable")
	public ResponseResult saveTable(GenTableDO tableDO) {
		Long userId = getUserId();
		tableDO.setCreateUser(userId);
		Configuration config = GeneratorUtil.getConfig();
		tableDO.setPackageName(config.getProperty("package")+"");
		tableDO.setSrcDir(config.getProperty("srcPath")+"");
		generatorService.saveTable(tableDO);
		return ResponseResult.ok();
	}
	@GetMapping("/editProperties")
	public String editProperties(Model model){
		Configuration config = GeneratorUtil.getConfig();
		Map<Object, Object> property = Maps.newHashMap();
		property.put("author", config.getProperty("author"));
		property.put("email", config.getProperty("email"));
		property.put("package", config.getProperty("package"));
		property.put("autoRemovePre", config.getProperty("autoRemovePre"));
		property.put("tablePrefix", config.getProperty("tablePrefix"));
		property.put("srcPath", config.getProperty("srcPath"));
		model.addAttribute("property", property);
		return perfix + "/editProperties";
	}

	@ResponseBody
	@PostMapping("/updateProperties")
	public ResponseResult updateProperties(@RequestParam Map<String, Object> map) {
		try {
			PropertiesConfiguration conf = new PropertiesConfiguration("generator.properties");
			conf.setProperty("author", map.get("author"));
			conf.setProperty("email", map.get("email"));
			conf.setProperty("package", map.get("package"));
			conf.setProperty("autoRemovePre", map.get("autoRemovePre"));
			conf.setProperty("tablePrefix", map.get("tablePrefix"));
			conf.setProperty("srcPath", map.get("srcPath"));
			conf.save();
		} catch (ConfigurationException e) {
			return ResponseResult.error("保存配置文件出错");
		}
		return ResponseResult.ok();
	}

	@GetMapping("/genColumns")
	String genColumns(String tableName, Model model) {
		model.addAttribute("tableName", tableName);
		return "common/genColumns/genColumns";
	}


	@ResponseBody
	@GetMapping("/genColumns/list")
	@SneakyThrows
	public ResponseResult<PageBean> genColumnsList(String tableName) {
		List<GenColumnsDO> genColumns = generatorService.listColumnsByTableName(tableName);
		int total = genColumns.size();
		PageBean pageBean = new PageBean(genColumns, Long.valueOf(total));
		return ResponseResult.ok().put(pageBean);
	}

	/**
	 * 执行代码生成
	 */
	@ApiOperation(value = "执行代码生成", notes = "执行代码生成")
	@ResponseBody
	@PostMapping("/genColumns/execGen")
	public ResponseResult execGen(@RequestBody List<GenColumnsDO> list) {

		return ResponseResult.ok();
	}

	/**
	 * 新增列
	 */
	@ApiOperation(value = "新增列", notes = "新增列")
	@GetMapping("/genColumns/addColumn/{tableName}")
	public String addColumn(@PathVariable("tableName") String tableName, Model model) {
		model.addAttribute("tableName", tableName);
		return "common/genColumns/add";
	}

	@ResponseBody
	@PostMapping("/saveColumn")
	public ResponseResult saveColumn(GenColumnsDO columnsDO) {
		Long userId = getUserId();
		columnsDO.setCreateUser(userId);
		generatorService.genColumnsSave(columnsDO);
		return ResponseResult.ok();
	}

	@ResponseBody
	@PostMapping("/genCode")
	public ResponseResult genCode(String tableName) {
		String[] tableNames = new String[]{tableName};
		generatorService.generatorCode(tableNames);
		return ResponseResult.ok("代码生成成功，请到本地项目中查看！");
	}
}
