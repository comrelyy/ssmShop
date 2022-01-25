package com.relyy.shop.backend.entity;

import java.io.Serializable;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.relyy.shop.backend.utils.Long2StringSerializer;
import lombok.Data;


import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;



/**
 * 上传文件信息
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2022-01-25 17:15:38
 */
@Accessors(chain = true)
@Data
@TableName("tb_file")
public class FileDO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	/**
	* 
 	*/
		//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
	//所以通过序列化成字符串来解决
	@JsonSerialize(using = Long2StringSerializer.class)
			@TableId(type = IdType.AUTO)
			private Long id;
	/**
	* 文件名称
 	*/
				private String fileName;
	/**
	* 文件类型
 	*/
				private Integer fileType;
	/**
	* URL地址
 	*/
				private String url;
	/**
	* 1 有效 0 已删除
 	*/
				private Integer status;
	/**
	* 创建用户id
 	*/
		//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
	//所以通过序列化成字符串来解决
	@JsonSerialize(using = Long2StringSerializer.class)
				private Long createUser;
	/**
	* 创建时间
 	*/
				@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date gmtCreate;
	/**
	* 修改用户id
 	*/
		//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
	//所以通过序列化成字符串来解决
	@JsonSerialize(using = Long2StringSerializer.class)
				private Long updateUser;
	/**
	* 修改时间
 	*/
				@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date gmtModified;
}
