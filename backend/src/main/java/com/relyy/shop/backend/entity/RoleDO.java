package com.relyy.shop.backend.entity;

import java.io.Serializable;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.relyy.shop.backend.utils.Long2StringSerializer;
import lombok.Data;


import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;



/**
 * 角色
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:48:40
 */
@Data
public class RoleDO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	/**
	* 
 	*/
		//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
	//所以通过序列化成字符串来解决
	@JsonSerialize(using = Long2StringSerializer.class)
			private Long roleId;
	/**
	* 角色名称
 	*/
			private String roleName;
	/**
	* 角色标识
 	*/
			private String roleSign;
	/**
	* 备注
 	*/
			private String remark;
	/**
	* 创建用户id
 	*/
		//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
	//所以通过序列化成字符串来解决
	@JsonSerialize(using = Long2StringSerializer.class)
			private Long userIdCreate;
	/**
	* 创建时间
 	*/
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date gmtCreate;
	/**
	* 创建时间
 	*/
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date gmtModified;
}
