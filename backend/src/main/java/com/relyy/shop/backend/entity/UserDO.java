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
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-09 17:04:48
 */
@Data
@TableName("tb_user")
public class UserDO implements Serializable {

	private static final long serialVersionUID = 8297786351231628095L;
	@TableId
	private Long id;
	/**
	 * 用户id
	 */
	//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
	//所以通过序列化成字符串来解决
	@JsonSerialize(using = Long2StringSerializer.class)
	private Long userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 *
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 *
	 */
	//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
	//所以通过序列化成字符串来解决
	@JsonSerialize(using = Long2StringSerializer.class)
	private Long deptId;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 状态 0:禁用，1:正常
	 */
	private Integer status;
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
	 * 修改时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtModified;
	/**
	 * 性别
	 */
	//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
	//所以通过序列化成字符串来解决
	@JsonSerialize(using = Long2StringSerializer.class)
	private Long sex;
	/**
	 * 出身日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	/**
	 *
	 */
	//java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
	//所以通过序列化成字符串来解决
	@JsonSerialize(using = Long2StringSerializer.class)
	private Long picId;
	/**
	 * 现居住地
	 */
	private String liveAddress;
	/**
	 * 爱好
	 */
	private String hobby;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 所在城市
	 */
	private String city;
	/**
	 * 所在地区
	 */
	private String district;
}
