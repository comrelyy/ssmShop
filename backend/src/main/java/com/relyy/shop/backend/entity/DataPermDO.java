package com.relyy.shop.backend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.relyy.shop.backend.utils.Long2StringSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 数据权限管理
 *
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:57:22
 */
@Data
@TableName("sys_data_perm")
public class DataPermDO implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 *
	 */
	//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
	//所以通过序列化成字符串来解决
	@JsonSerialize(using = Long2StringSerializer.class)
	@TableId
	private Long id;
	/**
	 * 权限名称
	 */
	private String name;
	/**
	 * 数据表名称
	 */
	private String tableName;
	/**
	 * 所属模块
	 */
	private String moduleName;
	/**
	 * 用户权限控制属性名
	 */
	private String crlAttrName;
	/**
	 * 数据表权限控制列名
	 */
	private String crlColumnName;
	/**
	 * 权限code，all_开头表示查看所有数据的权限，sup_开头表示查看下级数据的权限，own_开头表示查看本级数据的权限
	 */
	private String permCode;
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
