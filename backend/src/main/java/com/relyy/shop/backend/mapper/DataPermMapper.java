package com.relyy.shop.backend.mapper;

import com.relyy.shop.backend.entity.DataPermDO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
 * 数据权限管理
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:57:22
 */
@Mapper
public interface DataPermMapper extends BaseMapper<DataPermDO> {

	@Select("select `id`, `name`, `table_name`, `module_name`, `crl_attr_name`, `crl_column_name`, `perm_code`, `order_num`, `gmt_create`, `gmt_modified` from tb_data_perm where id = #{id}")
	DataPermDO get(Long id);
	
	@Select("<script>" +
	"select * from tb_data_perm " + 
			"<where>" + 
		  		  "<if test=\"id != null and id != ''\">"+ "and id = #{id} " + "</if>" +
		  		  "<if test=\"name != null and name != ''\">"+ "and name = #{name} " + "</if>" +
		  		  "<if test=\"tableName != null and tableName != ''\">"+ "and table_name = #{tableName} " + "</if>" +
		  		  "<if test=\"moduleName != null and moduleName != ''\">"+ "and module_name = #{moduleName} " + "</if>" +
		  		  "<if test=\"crlAttrName != null and crlAttrName != ''\">"+ "and crl_attr_name = #{crlAttrName} " + "</if>" +
		  		  "<if test=\"crlColumnName != null and crlColumnName != ''\">"+ "and crl_column_name = #{crlColumnName} " + "</if>" +
		  		  "<if test=\"permCode != null and permCode != ''\">"+ "and perm_code = #{permCode} " + "</if>" +
		  		  "<if test=\"orderNum != null and orderNum != ''\">"+ "and order_num = #{orderNum} " + "</if>" +
		  		  "<if test=\"gmtCreate != null and gmtCreate != ''\">"+ "and gmt_create = #{gmtCreate} " + "</if>" +
		  		  "<if test=\"gmtModified != null and gmtModified != ''\">"+ "and gmt_modified = #{gmtModified} " + "</if>" +
		  			"</where>"+ 
			" <choose>" + 
	            "<when test=\"sort != null and sort.trim() != ''\">" + 
	                "order by ${sort} ${order}" + 
	            "</when>" + 
				"<otherwise>" + 
	                "order by id desc" +
				"</otherwise>" + 
	        "</choose>"+
			"<if test=\"offset != null and limit != null\">"+
			"limit #{offset}, #{limit}" + 
			"</if>"+
			"</script>")
	List<DataPermDO> list(Map<String,Object> map);
	
	@Select("<script>" +
	"select count(*) from tb_data_perm " + 
			"<where>" + 
		  		  "<if test=\"id != null and id != ''\">"+ "and id = #{id} " + "</if>" +
		  		  "<if test=\"name != null and name != ''\">"+ "and name = #{name} " + "</if>" +
		  		  "<if test=\"tableName != null and tableName != ''\">"+ "and table_name = #{tableName} " + "</if>" +
		  		  "<if test=\"moduleName != null and moduleName != ''\">"+ "and module_name = #{moduleName} " + "</if>" +
		  		  "<if test=\"crlAttrName != null and crlAttrName != ''\">"+ "and crl_attr_name = #{crlAttrName} " + "</if>" +
		  		  "<if test=\"crlColumnName != null and crlColumnName != ''\">"+ "and crl_column_name = #{crlColumnName} " + "</if>" +
		  		  "<if test=\"permCode != null and permCode != ''\">"+ "and perm_code = #{permCode} " + "</if>" +
		  		  "<if test=\"orderNum != null and orderNum != ''\">"+ "and order_num = #{orderNum} " + "</if>" +
		  		  "<if test=\"gmtCreate != null and gmtCreate != ''\">"+ "and gmt_create = #{gmtCreate} " + "</if>" +
		  		  "<if test=\"gmtModified != null and gmtModified != ''\">"+ "and gmt_modified = #{gmtModified} " + "</if>" +
		  			"</where>"+ 
			"</script>")
	int count(Map<String,Object> map);
	
	@Insert("insert into tb_data_perm (`name`, `table_name`, `module_name`, `crl_attr_name`, `crl_column_name`, `perm_code`, `order_num`)"
	+ "values (#{name}, #{tableName}, #{moduleName}, #{crlAttrName}, #{crlColumnName}, #{permCode}, #{orderNum})")
	int save(DataPermDO dataPerm);

    int saveSelective(DataPermDO dataPerm);
	
	@Update("<script>"+ 
			"update tb_data_perm " + 
					"<set>" + 
		            "<if test=\"id != null\">`id` = #{id}, </if>" +
                    "<if test=\"name != null\">`name` = #{name}, </if>" +
                    "<if test=\"tableName != null\">`table_name` = #{tableName}, </if>" +
                    "<if test=\"moduleName != null\">`module_name` = #{moduleName}, </if>" +
                    "<if test=\"crlAttrName != null\">`crl_attr_name` = #{crlAttrName}, </if>" +
                    "<if test=\"crlColumnName != null\">`crl_column_name` = #{crlColumnName}, </if>" +
                    "<if test=\"permCode != null\">`perm_code` = #{permCode}, </if>" +
                    "<if test=\"orderNum != null\">`order_num` = #{orderNum}, </if>" +
          					"</set>" + 
					"where id = #{id}"+
			"</script>")
	int update(DataPermDO dataPerm);
	
	@Delete("delete from tb_data_perm where id =#{id}")
	int remove(Long id);
	
	@Delete("<script>"+ 
			"delete from tb_data_perm where id in " +
					"<foreach item=\"id\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" +
						"#{id}" +
					"</foreach>"+
			"</script>")
	int batchRemove(Long[] ids);
}
