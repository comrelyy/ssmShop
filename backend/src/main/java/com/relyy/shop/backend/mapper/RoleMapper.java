package com.relyy.shop.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.relyy.shop.backend.entity.RoleDO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
/**
 * 角色
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:48:40
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {

	@Select("select `role_id`, `role_name`, `role_sign`, `remark`, `user_id_create`, `gmt_create`, `gmt_modified` from tb_role where role_id = #{roleId}")
	RoleDO get(Long roleId);
	
	@Select("<script>" +
	"select * from tb_role " + 
			"<where>" + 
		  		  "<if test=\"roleId != null and roleId != ''\">"+ "and role_id = #{roleId} " + "</if>" +
		  		  "<if test=\"roleName != null and roleName != ''\">"+ "and role_name = #{roleName} " + "</if>" +
		  		  "<if test=\"roleSign != null and roleSign != ''\">"+ "and role_sign = #{roleSign} " + "</if>" +
		  		  "<if test=\"remark != null and remark != ''\">"+ "and remark = #{remark} " + "</if>" +
		  		  "<if test=\"userIdCreate != null and userIdCreate != ''\">"+ "and user_id_create = #{userIdCreate} " + "</if>" +
		  		  "<if test=\"gmtCreate != null and gmtCreate != ''\">"+ "and gmt_create = #{gmtCreate} " + "</if>" +
		  		  "<if test=\"gmtModified != null and gmtModified != ''\">"+ "and gmt_modified = #{gmtModified} " + "</if>" +
		  			"</where>"+ 
			" <choose>" + 
	            "<when test=\"sort != null and sort.trim() != ''\">" + 
	                "order by ${sort} ${order}" + 
	            "</when>" + 
				"<otherwise>" + 
	                "order by role_id desc" +
				"</otherwise>" + 
	        "</choose>"+
			"<if test=\"offset != null and limit != null\">"+
			"limit #{offset}, #{limit}" + 
			"</if>"+
			"</script>")
	List<RoleDO> list(Map<String,Object> map);
	
	@Select("<script>" +
	"select count(*) from tb_role " +
			"<where>" + 
		  		  "<if test=\"roleId != null and roleId != ''\">"+ "and role_id = #{roleId} " + "</if>" +
		  		  "<if test=\"roleName != null and roleName != ''\">"+ "and role_name = #{roleName} " + "</if>" +
		  		  "<if test=\"roleSign != null and roleSign != ''\">"+ "and role_sign = #{roleSign} " + "</if>" +
		  		  "<if test=\"remark != null and remark != ''\">"+ "and remark = #{remark} " + "</if>" +
		  		  "<if test=\"userIdCreate != null and userIdCreate != ''\">"+ "and user_id_create = #{userIdCreate} " + "</if>" +
		  		  "<if test=\"gmtCreate != null and gmtCreate != ''\">"+ "and gmt_create = #{gmtCreate} " + "</if>" +
		  		  "<if test=\"gmtModified != null and gmtModified != ''\">"+ "and gmt_modified = #{gmtModified} " + "</if>" +
		  			"</where>"+ 
			"</script>")
	int count(Map<String,Object> map);
	
	@Insert("insert into tb_role (`role_name`, `role_sign`, `remark`, `user_id_create`)"
	+ "values (#{roleName}, #{roleSign}, #{remark}, #{userIdCreate})")
	int save(RoleDO role);

    int saveSelective(RoleDO role);
	
	@Update("<script>"+ 
			"update tb_role " + 
					"<set>" + 
		            "<if test=\"roleId != null\">`role_id` = #{roleId}, </if>" +
                    "<if test=\"roleName != null\">`role_name` = #{roleName}, </if>" +
                    "<if test=\"roleSign != null\">`role_sign` = #{roleSign}, </if>" +
                    "<if test=\"remark != null\">`remark` = #{remark}, </if>" +
                    "<if test=\"userIdCreate != null\">`user_id_create` = #{userIdCreate}, </if>" +
          					"</set>" + 
					"where role_id = #{roleId}"+
			"</script>")
	int update(RoleDO role);
	
	@Delete("delete from tb_role where role_id =#{roleId}")
	int remove(Long role_id);
	
	@Delete("<script>"+ 
			"delete from tb_role where role_id in " +
					"<foreach item=\"roleId\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" +
						"#{roleId}" +
					"</foreach>"+
			"</script>")
	int batchRemove(Long[] roleIds);
}
