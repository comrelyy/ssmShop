package com.relyy.shop.backend.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Created by Reeve Cai
 * @Date 2021/12/8
 */
@Data
public class TableDO {
	/**
	 *表的名称
	 */
	private String tableName;
	/**
	 *表的备注
	 */
	private String comments;
	/**
	 * 表的主键
	 */
	private GenColumnsDO priKeyColumn;
	/**
	 * 表的列名(不包含主键)
	 */
	private List<GenColumnsDO> columns;

	/**
	 * 类名(第一个字母大写)，如：sys_user => SysUser
	 */
	private String classNameUpFirst;
	/**
	 *类名(第一个字母小写)，如：sys_user => sysUser
	 */
	private String className;
}
