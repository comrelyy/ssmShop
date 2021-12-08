package com.relyy.shop.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Description 获取数据库表信息
 * @Created by Reeve Cai
 * @Date 2021/12/8
 */
@Mapper
public interface GeneratorMapper {

	@Select("select table_name as tableName,engine,table_comment as tableComment,create_time as craeteTime " +
			"from information_schema.tables " +
			"where table_schema = (select database()) and table_name like concat('%','${tableName}','%')")
	List<Map<String,Object>> list(String tableName);

	@Select("select count(*) from information_schema.tables where table_schema = (select database()) " +
			" and table_name <> 'flyway_schema_history'")
	int count();

	@Select("select table_name as tableName,engine,table_comment as tableComment,create_time as craeteTime " +
			"from information_schema.tables " +
			"where table_schema = (select database()) and table_name = '${tableName}'")
	Map<String,String> get(String tableName);

	@Select("select column_name as columnName,data_type as dataType,column_comment as columnComment,column_key as columnKey,extra " +
			"from information_schema.columns " +
			"where table_name ='${tableName}' and table_schema = (select database()) order by ordinal_position ")
	List<Map<String,Object>> listColumns(String tableName);

	@Select("select column_name as columnName,data_type as dataType,column_comment as columnComment,column_key as columnKey,extra " +
			"from information_schema.columns " +
			"where table_name ='${tableName}' and table_schema = (select database()) and column_key ='PRI' limit 1 ")
	Map<String,Object> getPriColum(String tableName);
}
