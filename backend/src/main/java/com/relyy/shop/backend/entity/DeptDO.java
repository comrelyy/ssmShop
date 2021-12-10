package com.relyy.shop.backend.entity;

import java.io.Serializable;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.relyy.shop.backend.utils.Long2StringSerializer;
import lombok.Data;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 部门管理
 *
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:55:05
 */
@Data
public class DeptDO implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 *
	 */
	//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
	//所以通过序列化成字符串来解决
	@JsonSerialize(using = Long2StringSerializer.class)
	private Long deptId;
	/**
	 * 上级部门ID，一级部门为0
	 */
	//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
	//所以通过序列化成字符串来解决
	@JsonSerialize(using = Long2StringSerializer.class)
	private Long parentId;
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 排序
	 */
	private Integer orderNum;
	/**
	 * 是否删除  -1：已删除  0：正常
	 */
	private Integer delFlag;
}
