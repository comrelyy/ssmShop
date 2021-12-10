package com.relyy.shop.backend.entity;

import java.io.Serializable;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.relyy.shop.backend.utils.Long2StringSerializer;
import lombok.Data;


import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;



/**
 * 菜单管理
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:52:17
 */
@Data
public class MenuDO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	/**
	* 
 	*/
		//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
	//所以通过序列化成字符串来解决
	@JsonSerialize(using = Long2StringSerializer.class)
			private Long menuId;
	/**
	* 父菜单ID，一级菜单为0
 	*/
		//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
	//所以通过序列化成字符串来解决
	@JsonSerialize(using = Long2StringSerializer.class)
			private Long parentId;
	/**
	* 菜单名称
 	*/
			private String name;
	/**
	* 菜单URL
 	*/
			private String url;
	/**
	* 授权(多个用逗号分隔，如：user:list,user:create)
 	*/
			private String perms;
	/**
	* 类型   0：目录   1：菜单   2：按钮
 	*/
			private Integer type;
	/**
	* 菜单图标
 	*/
			private String icon;
	/**
	* 排序
 	*/
			private Integer orderNum;
	/**
	* 创建时间
 	*/
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date gmtCreate;
	/**
	* 修改时间
 	*/
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date gmtModified;
}
