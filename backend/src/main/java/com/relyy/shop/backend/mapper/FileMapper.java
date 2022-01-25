package com.relyy.shop.backend.mapper;

import com.relyy.shop.backend.entity.FileDO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
 * 上传文件信息
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2022-01-25 17:15:38
 */
@Mapper
public interface FileMapper extends BaseMapper<FileDO> {

	@Select("select `id`, `file_name`, `file_type`, `url`, `status`, `create_user`, `gmt_create`, `update_user`, `gmt_modified` from tb_file where id = #{id}")
	FileDO get(Long id);
	
	@Select("<script>" +
	"select * from tb_file " + 
			"<where>" + 
		  		  "<if test=\"id != null and id != ''\">"+ "and id = #{id} " + "</if>" +
		  		  "<if test=\"fileName != null and fileName != ''\">"+ "and file_name = #{fileName} " + "</if>" +
		  		  "<if test=\"fileType != null and fileType != ''\">"+ "and file_type = #{fileType} " + "</if>" +
		  		  "<if test=\"url != null and url != ''\">"+ "and url = #{url} " + "</if>" +
		  		  "<if test=\"status != null and status != ''\">"+ "and status = #{status} " + "</if>" +
		  		  "<if test=\"createUser != null and createUser != ''\">"+ "and create_user = #{createUser} " + "</if>" +
		  		  "<if test=\"gmtCreate != null and gmtCreate != ''\">"+ "and gmt_create = #{gmtCreate} " + "</if>" +
		  		  "<if test=\"updateUser != null and updateUser != ''\">"+ "and update_user = #{updateUser} " + "</if>" +
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
	List<FileDO> list(Map<String,Object> map);
	
	@Select("<script>" +
	"select count(*) from tb_file " + 
			"<where>" + 
		  		  "<if test=\"id != null and id != ''\">"+ "and id = #{id} " + "</if>" +
		  		  "<if test=\"fileName != null and fileName != ''\">"+ "and file_name = #{fileName} " + "</if>" +
		  		  "<if test=\"fileType != null and fileType != ''\">"+ "and file_type = #{fileType} " + "</if>" +
		  		  "<if test=\"url != null and url != ''\">"+ "and url = #{url} " + "</if>" +
		  		  "<if test=\"status != null and status != ''\">"+ "and status = #{status} " + "</if>" +
		  		  "<if test=\"createUser != null and createUser != ''\">"+ "and create_user = #{createUser} " + "</if>" +
		  		  "<if test=\"gmtCreate != null and gmtCreate != ''\">"+ "and gmt_create = #{gmtCreate} " + "</if>" +
		  		  "<if test=\"updateUser != null and updateUser != ''\">"+ "and update_user = #{updateUser} " + "</if>" +
		  		  "<if test=\"gmtModified != null and gmtModified != ''\">"+ "and gmt_modified = #{gmtModified} " + "</if>" +
		  			"</where>"+ 
			"</script>")
	int count(Map<String,Object> map);
	
	@Insert("insert into tb_file (`file_name`, `file_type`, `url`, `status`, `create_user`, `gmt_create`, `update_user`, `gmt_modified`)"
	+ "values (#{fileName}, #{fileType}, #{url}, #{status}, #{createUser}, #{gmtCreate}, #{updateUser}, #{gmtModified})")
	int save(FileDO file);

    int saveSelective(FileDO file);
	
	@Update("<script>"+ 
			"update tb_file " + 
					"<set>" + 
		            "<if test=\"id != null\">`id` = #{id}, </if>" +
                    "<if test=\"fileName != null\">`file_name` = #{fileName}, </if>" +
                    "<if test=\"fileType != null\">`file_type` = #{fileType}, </if>" +
                    "<if test=\"url != null\">`url` = #{url}, </if>" +
                    "<if test=\"status != null\">`status` = #{status}, </if>" +
                    "<if test=\"createUser != null\">`create_user` = #{createUser}, </if>" +
                    "<if test=\"gmtCreate != null\">`gmt_create` = #{gmtCreate}, </if>" +
                    "<if test=\"updateUser != null\">`update_user` = #{updateUser}, </if>" +
                    "<if test=\"gmtModified != null\">`gmt_modified` = #{gmtModified}, </if>" +
          					"</set>" + 
					"where id = #{id}"+
			"</script>")
	int update(FileDO file);
	
	@Delete("delete from tb_file where id =#{id}")
	int remove(Long id);
	
	@Delete("<script>"+ 
			"delete from tb_file where id in " +
					"<foreach item=\"id\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" +
						"#{id}" +
					"</foreach>"+
			"</script>")
	int batchRemove(Long[] ids);
}
