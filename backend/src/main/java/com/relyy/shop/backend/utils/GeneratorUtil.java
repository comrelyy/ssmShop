package com.relyy.shop.backend.utils;


import com.relyy.shop.backend.common.Constant;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 利用volecity模板引擎生成代码
 * @Created by Reeve Cai
 * @Date 2021/12/6
 */
public class GeneratorUtil {

	public static List<String> getTemplates(){
		List<String> templates = new ArrayList<String>();
		templates.add("templates/common/generator/domain.java.vm");
		templates.add("templates/common/generator/Dao.java.vm");
		//templates.add("templates/common/generator/Mapper.java.vm");
		templates.add("templates/common/generator/Mapper.xml.vm");
		templates.add("templates/common/generator/Service.java.vm");
		templates.add("templates/common/generator/ServiceImpl.java.vm");
		templates.add("templates/common/generator/Controller.java.vm");
		templates.add("templates/common/generator/list.html.vm");
		templates.add("templates/common/generator/add.html.vm");
		templates.add("templates/common/generator/edit.html.vm");
		templates.add("templates/common/generator/detail.html.vm");
		templates.add("templates/common/generator/list.js.vm");
		templates.add("templates/common/generator/add.js.vm");
		templates.add("templates/common/generator/edit.js.vm");
		templates.add("templates/common/generator/menu.sql.vm");
		//templates.add("templates/common/generator/menu.sql.vm");
		return templates;
	}

	/**
	 * 列名转换成Java属性名
	 */
	public static String columnToJava(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
	}

	/**
	 * 获取配置信息
	 */
	public static Configuration getConfig() {
		try {
			return new PropertiesConfiguration("generator.properties");
		} catch (ConfigurationException e) {
			throw new RuntimeException("获取配置文件失败，", e);
		}
	}

	/**
	 * 表名转换成Java类名
	 */
	public static String tableToJava(String tableName, String tablePrefix, String autoRemovePre) {
		if (Constant.AUTO_REOMVE_PRE.equals(autoRemovePre)) {
			tableName = tableName.substring(tableName.indexOf("_") + 1);
		}
		if (StringUtils.isNotBlank(tablePrefix)) {
			tableName = tableName.replace(tablePrefix, "");
		}

		return columnToJava(tableName);
	}

	/**
	 * 获取文件名
	 */
	public static String getFileName(String template, String classname, String className, String packageName) {
		String moduleName = packageName.substring(packageName.lastIndexOf(".") + 1);

		String javaPackagePath = "main" + File.separator + "java" + File.separator;
		//String modulesname=config.getString("packageName");
		if (StringUtils.isNotBlank(packageName)) {
			javaPackagePath += packageName.replace(".", File.separator) + File.separator;
		}

		if (template.contains("domain.java.vm")) {
			return javaPackagePath + "entity" + File.separator + className + "DO.java";
		}

		if (template.contains("Dao.java.vm")) {
			return javaPackagePath + "mapper" + File.separator + className + "Mapper.java";
		}

//		if(template.contains("Mapper.java.vm")){
//			return packagePath + "dao" + File.separator + className + "Mapper.java";
//		}

		if (template.contains("Service.java.vm")) {
			return javaPackagePath + "service" + File.separator + className + "Service.java";
		}

		if (template.contains("ServiceImpl.java.vm")) {
			return javaPackagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
		}

		if (template.contains("Controller.java.vm")) {
			return javaPackagePath + "controller" + File.separator + className + "Controller.java";
		}

//		if (template.contains("Mapper.xml.vm")) {
//			return "main" + File.separator + "resources" + File.separator + "mybatis" + File.separator + moduleName + File.separator + className + "Mapper.xml";
//		}

		if (template.contains("list.html.vm")) {
			return "main" + File.separator + "resources" + File.separator + "templates" + File.separator
					+ moduleName + File.separator + classname + File.separator + classname + ".html";
			//				+ "modules" + File.separator + "generator" + File.separator + className.toLowerCase() + ".html";
		}
		if (template.contains("add.html.vm")) {
			return "main" + File.separator + "resources" + File.separator + "templates" + File.separator
					+ moduleName + File.separator + classname + File.separator + "add.html";
		}
		if (template.contains("edit.html.vm")) {
			return "main" + File.separator + "resources" + File.separator + "templates" + File.separator
					+ moduleName + File.separator + classname + File.separator + "edit.html";
		}
		if (template.contains("detail.html.vm")) {
			return "main" + File.separator + "resources" + File.separator + "templates" + File.separator
					+ moduleName + File.separator + classname + File.separator + "detail.html";
		}

		if (template.contains("list.js.vm")) {
			return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js" + File.separator
					+ "appjs" + File.separator + moduleName + File.separator + classname + File.separator + classname + ".js";
			//		+ "modules" + File.separator + "generator" + File.separator + className.toLowerCase() + ".js";
		}
		if (template.contains("add.js.vm")) {
			return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js" + File.separator
					+ "appjs" + File.separator + moduleName + File.separator + classname + File.separator + "add.js";
		}
		if (template.contains("edit.js.vm")) {
			return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js" + File.separator
					+ "appjs" + File.separator + moduleName + File.separator + classname + File.separator + "edit.js";
		}
		if (template.contains("menu.sql.vm")) {
			return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "sql"
					+ File.separator + moduleName + File.separator + classname + File.separator + "menu.js";
		}

//		if(template.contains("menu.sql.vm")){
//			return className.toLowerCase() + "_menu.sql";
//		}

		return null;
	}

	public static void generatorCode(){

	}

}