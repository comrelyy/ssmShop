package com.relyy.shop.backend.mapper;

import com.relyy.shop.backend.entity.GenColumnsDO;
import com.relyy.shop.backend.entity.GenTableDO;
import org.apache.ibatis.annotations.Mapper;
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

	@Select("select table_name as tableName,ENGINE as tableEngine,table_comment as tableComment,create_time as gmtCreate " +
			"from information_schema.tables " +
			"where table_schema = (select database()) and table_name like concat('%','${tableName}','%')")
	List<GenTableDO> list(String tableName);

	@Select("select count(*) from information_schema.tables where table_schema = (select database()) " +
			" and table_name <> 'flyway_schema_history'")
	int count();

	@Select("select table_name as tableName,ENGINE as tableEngine,table_comment as tableComment,create_time as gmtCreate " +
			"from information_schema.tables " +
			"where table_schema = (select database()) and table_name like concat('%','${tableName}','%')")
	GenTableDO get(String tableName);

	@Select("select table_name as tableName, column_name as columnName,data_type as columnType,column_comment as columnComment,column_key as columnKey," +
			"ordinal_position as columnSort," +
			"column_default as defaultValue," +
			" (case is_nullable when 'YES' then 1 when 'NO' then 0 end ) as isRequired," +
			"EXTRA as extra " +
			"from information_schema.columns " +
			"where table_name ='${tableName}' and table_schema = (select database()) order by ordinal_position ")
	List<GenColumnsDO> listColumns(String tableName);

	@Select("select column_name as columnName,data_type as dataType,column_comment as columnComment,column_key as columnKey," +
			"ordinal_position as columnSort," +
			"column_default as defaultValue," +
			"is_nullable as isRequired," +
			"EXTRA as extra " +
			"from information_schema.columns " +
			"where table_name ='${tableName}' and table_schema = (select database()) and column_key ='PRI' limit 1 ")
	Map<String,Object> getPriColum(String tableName);
}
