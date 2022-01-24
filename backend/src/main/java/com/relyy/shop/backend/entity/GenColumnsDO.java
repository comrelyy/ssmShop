package com.relyy.shop.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Created by Reeve Cai
 * @Date 2021/12/8
 */
@Accessors(chain = true)
@Data
@TableName("sys_gen_columns")
public class GenColumnsDO implements Serializable {
	private static final long serialVersionUID = -3273642840993597932L;

//	//主键
//	//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
//	//所以通过序列化成字符串来解决
//	@JsonSerialize(using = Long2StringSerializer.class)
    @TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 列名
	 */
	private String columnName;
	/**
	 * 列类型
	 */
	private String columnType;
	/**
	 * 映射java类型
	 */
	private String javaType;
	/**
	 *列注释
	 */
	private String columnComment;
	/**
	 *列排序（升序）
	 */
	private Integer columnSort;
	/**
	 *列标签名
	 */
	private String columnLabel;
	/**
	 * 页面显示类型：1、文本框 2、下拉框 3、数值4、日期 5、文本域
	 * 6、富文本 7、上传图片【单文件】 8、上传图片【多文件】9、上传文件【单文件】
	 * 10、上传文件【多文件】11、隐藏域 12、不显示
	 */
	private Integer pageType;
	//是否必填
	private Integer isRequired;
	//默认值
	private String defaultValue;

	//索引类型 主键 唯一 默认 null
	private String columnKey;
	//页面显示为下拉时使用，字典类型从字典表中取出
	private String dictType;

	// 属性名称(第一个字母大写)，如：user_name => UserName
	@TableField(exist = false)
	private String attrNameUpFirst;
	// 属性名称(第一个字母小写)，如：user_name => userName
	@TableField(exist = false)
	private String attrName;

	private String extra;

	/**
	 * '要执行的操作，add 新增； del 删除； update 更新；done 完成；unable 未使用 默认'
	 */
	private String status;

	private Long createUser;
	private Long updateUser;
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
