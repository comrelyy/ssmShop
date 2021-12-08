package com.relyy.shop.backend.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.relyy.shop.backend.utils.Long2StringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Created by Reeve Cai
 * @Date 2021/12/8
 */
@Data
public class GenColumnsDO implements Serializable {
	private static final long serialVersionUID = -3273642840993597932L;

	//主键
	//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
	//所以通过序列化成字符串来解决
	@JsonSerialize(using = Long2StringSerializer.class)
	private Long id;
	//表名
	private String tableName;
	//列名
	private String columnName;
	//列类型
	private String columnType;
	//映射java类型
	private String javaType;
	//列注释
	private String columnComment;
	//列排序（升序）
	private Integer columnSort;
	//列标签名
	private String columnLabel;
	//页面显示类型：1、文本框 2、下拉框 3、数值4、日期 5、文本域6、富文本 7、上传图片【单文件】 8、上传图片【多文件】9、上传文件【单文件】 10、上传文件【多文件】11、隐藏域 12、不显示
	private Integer pageType;
	//是否必填
	private Integer isRequired;
	//页面显示为下拉时使用，字典类型从字典表中取出
	private String dictType;

	// 属性名称(第一个字母大写)，如：user_name => UserName
	private String attrName;
	// 属性名称(第一个字母小写)，如：user_name => userName
	private String attrname;

	private String extra;
}
