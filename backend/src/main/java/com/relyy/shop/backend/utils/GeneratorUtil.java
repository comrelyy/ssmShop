package com.relyy.shop.backend.utils;


import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Maps;
import com.relyy.shop.backend.common.Constant;
import com.relyy.shop.backend.entity.GenColumnsDO;
import com.relyy.shop.backend.entity.TableDO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;
import java.util.*;

/**
 * @Description 利用volecity模板引擎生成代码
 * @Created by Reeve Cai
 * @Date 2021/12/6
 */
@Slf4j
public class GeneratorUtil {

	public static List<String> getTemplates(){
		List<String> templates = new ArrayList<String>();
		templates.add("templates/common/code_templates/entity.java.vm");
		//templates.add("templates/common/generator/Dao.java.vm");
		templates.add("templates/common/code_templates/Mapper.java.vm");
		//templates.add("templates/common/generator/Mapper.xml.vm");
		templates.add("templates/common/code_templates/Service.java.vm");
//		templates.add("templates/common/generator/ServiceImpl.java.vm");
		templates.add("templates/common/code_templates/Controller.java.vm");
		templates.add("templates/common/code_templates/list.html.vm");
		templates.add("templates/common/code_templates/add.html.vm");
		templates.add("templates/common/code_templates/edit.html.vm");
		templates.add("templates/common/code_templates/detail.html.vm");
		templates.add("templates/common/code_templates/list.js.vm");
		templates.add("templates/common/code_templates/add.js.vm");
		templates.add("templates/common/code_templates/edit.js.vm");
		templates.add("templates/common/code_templates/menu.sql.vm");
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
	public static String getFileName(String template, String className, String classNameUpFirst, String packageName) {
		String moduleName = packageName.substring(packageName.lastIndexOf(".") + 1);

		String javaPackagePath = "main" + File.separator + "java" + File.separator;
		//String modulesname=config.getString("packageName");
		if (StringUtils.isNotBlank(packageName)) {
			javaPackagePath += packageName.replace(".", File.separator) + File.separator;
		}

		if (template.contains("entity.java.vm")) {
			return javaPackagePath + "entity" + File.separator + classNameUpFirst + "DO.java";
		}

//		if (template.contains("Dao.java.vm")) {
//			return javaPackagePath + "mapper" + File.separator + className + "Mapper.java";
//		}

		if(template.contains("Mapper.java.vm")){
			return javaPackagePath + "mapper" + File.separator + classNameUpFirst + "Mapper.java";
		}

		if (template.contains("Service.java.vm")) {
			return javaPackagePath + "services" + File.separator + classNameUpFirst + "Service.java";
		}

//		if (template.contains("ServiceImpl.java.vm")) {
//			return javaPackagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
//		}

		if (template.contains("Controller.java.vm")) {
			return javaPackagePath + "controller" + File.separator + classNameUpFirst + "Controller.java";
		}

//		if (template.contains("Mapper.xml.vm")) {
//			return "main" + File.separator + "resources" + File.separator + "mybatis" + File.separator + moduleName + File.separator + className + "Mapper.xml";
//		}

		if (template.contains("list.html.vm")) {
			return "main" + File.separator + "resources" + File.separator + "templates" + File.separator
					+ moduleName + File.separator + className + File.separator + className + ".html";
			//				+ "modules" + File.separator + "generator" + File.separator + className.toLowerCase() + ".html";
		}
		if (template.contains("add.html.vm")) {
			return "main" + File.separator + "resources" + File.separator + "templates" + File.separator
					+ moduleName + File.separator + className + File.separator + "add.html";
		}
		if (template.contains("edit.html.vm")) {
			return "main" + File.separator + "resources" + File.separator + "templates" + File.separator
					+ moduleName + File.separator + className + File.separator + "edit.html";
		}
		if (template.contains("detail.html.vm")) {
			return "main" + File.separator + "resources" + File.separator + "templates" + File.separator
					+ moduleName + File.separator + className + File.separator + "detail.html";
		}

		if (template.contains("list.js.vm")) {
			return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js" + File.separator
					+ "appjs" + File.separator + moduleName + File.separator + className + File.separator + className + ".js";
			//		+ "modules" + File.separator + "generator" + File.separator + className.toLowerCase() + ".js";
		}
		if (template.contains("add.js.vm")) {
			return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js" + File.separator
					+ "appjs" + File.separator + moduleName + File.separator + className + File.separator + "add.js";
		}
		if (template.contains("edit.js.vm")) {
			return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js" + File.separator
					+ "appjs" + File.separator + moduleName + File.separator + className + File.separator + "edit.js";
		}
		if (template.contains("menu.sql.vm")) {
			return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "sql"
					+ File.separator + moduleName + File.separator + className + File.separator + "menu.js";
		}

//		if(template.contains("menu.sql.vm")){
//			return className.toLowerCase() + "_menu.sql";
//		}

		return null;
	}

	@SneakyThrows
	public static GenColumnsDO transGenColumnDO(String tableName,Map<String, Object> column,int columnSort){
		GenColumnsDO genColumnsDO = new GenColumnsDO();
		genColumnsDO.setTableName(tableName);
		genColumnsDO.setColumnName(column.get("columnName")+"");
		genColumnsDO.setColumnType(column.get("dataType")+"");
		genColumnsDO.setColumnComment(column.get("columnComment")+"");
		PropertiesConfiguration conf = new PropertiesConfiguration("generator.properties");
		genColumnsDO.setJavaType(conf.getString(column.get("dataType")+""));
		genColumnsDO.setColumnSort(Integer.valueOf(column.get("columnSort")+""));
		genColumnsDO.setExtra(column.get("extra")+"");
		genColumnsDO.setIsRequired(StringUtils.compare(column.get("isRequired")+"","YES") == 0 ? 1 : 0);
		if ("Date".equals(conf.getString(column.get("dataType")+""))) {
			genColumnsDO.setPageType(4);
		} else {
			genColumnsDO.setPageType(1);
		}
		genColumnsDO.setColumnLabel(column.get("columnComment")+"");
		genColumnsDO.setDefaultValue(column.get("defaultValue")+"");
		genColumnsDO.setColumnKey(column.get("columnKey")+"");
		return genColumnsDO;
	}

	public static void generatorCode(Map<String,String> tableMap, GenColumnsDO priKeyColumn,List<GenColumnsDO> columnsDOList){
		Configuration config = getConfig();
		//封装表信息
		TableDO tableDO = new TableDO();
		tableDO.setTableName(tableMap.get("tableName"));
		tableDO.setComments(tableMap.get("tableComment"));
		String className = tableToJava(tableDO.getTableName(), config.getString("tablePrefix"), config.getString("autoRemovePre"));
		tableDO.setClassNameUpFirst(className);
		tableDO.setClassName(StringUtils.uncapitalize(className));

		Collections.sort(columnsDOList, Comparator.comparingInt(GenColumnsDO::getColumnSort));
		columnsDOList.forEach(genColumnsDO -> {
			String column = columnToJava(genColumnsDO.getColumnName());
			genColumnsDO.setAttrNameUpFirst(column);
			genColumnsDO.setAttrName(StringUtils.uncapitalize(column));
		});

		String columnName = columnToJava(priKeyColumn.getColumnName());
		priKeyColumn.setAttrNameUpFirst(columnName);
		priKeyColumn.setAttrName(StringUtils.uncapitalize(columnName));

		tableDO.setPriKeyColumn(priKeyColumn);
		columnsDOList.add(0,priKeyColumn);
		tableDO.setColumns(columnsDOList);

		//设置velocity资源加载器
		Properties properties = new Properties();
		properties.put("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(properties);

		Map<String, Object> map = Maps.newHashMap();
		map.put("tableName", tableDO.getTableName());
		map.put("comments", tableDO.getComments());
		map.put("priKeyColumn", tableDO.getPriKeyColumn());
		map.put("classNameUpFirst", tableDO.getClassNameUpFirst());
		map.put("className", tableDO.getClassName());
		map.put("pathName", config.getString("package").substring(config.getString("package").lastIndexOf(".") + 1));
		map.put("columns", tableDO.getColumns());
		map.put("package", config.getString("package"));
		map.put("author", config.getString("author"));
		map.put("email", config.getString("email"));
		map.put("datetime", DateUtil.formatDateTime(new Date()));

		VelocityContext velocity = new VelocityContext(map);

		List<String> templates = getTemplates();
		templates.stream().forEach(template -> {
			StringWriter ioWrite = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(velocity,ioWrite);
			OutputStream outputStream = null;
			try{
				String fileName = getFileName(template, tableDO.getClassName(), tableDO.getClassNameUpFirst(), config.getString("package"));

				String srcPath = config.getString("srcPath");
				fileName = srcPath + File.separator + fileName;
				File file = new File(fileName);
				if (file.exists()) {
					file = new File(fileName + 1);
				}
				File parentFile = file.getParentFile();
				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				outputStream = new FileOutputStream(file);
				IOUtils.write(ioWrite.toString(),outputStream,"UTF-8");
			} catch(IOException e){
			    log.error("模板渲染失败，表名:[{}]",tableDO.getTableName(),e);
			}finally {
				try {
					outputStream.close();
				} catch (IOException e) {
					log.error("",e);
				}
			}
		});
	}

}