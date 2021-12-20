package com.relyy.shop.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.relyy.shop.backend.entity.UserDO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
/**
 * 
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-09 17:04:48
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

//	@Select("select `user_id`, `username`, `name`, `password`, `dept_id`, `email`, `mobile`, `status`, `user_id_create`, `gmt_create`, `gmt_modified`, `sex`, `birth`, `pic_id`, `live_address`, `hobby`, `province`, `city`, `district` from tb_user where user_id = #{id}")
//	UserDO get(Long userId);

	@Select("<script>" +
	"select * from tb_user " + 
			"<where>" + 
		  		  "<if test=\"userId != null and userId != ''\">"+ "and user_id = #{userId} " + "</if>" +
		  		  "<if test=\"username != null and username != ''\">"+ "and username like concat('%',#{username},'%') " + "</if>" +
		  		  "<if test=\"name != null and name != ''\">"+ "and name like concat('%',#{name},'%') " + "</if>" +
		  		  "<if test=\"password != null and password != ''\">"+ "and password = #{password} " + "</if>" +
		  		  "<if test=\"deptId != null and deptId != ''\">"+ "and dept_id = #{deptId} " + "</if>" +
		  		  "<if test=\"email != null and email != ''\">"+ "and email = #{email} " + "</if>" +
		  		  "<if test=\"mobile != null and mobile != ''\">"+ "and mobile = #{mobile} " + "</if>" +
		  		  "<if test=\"status != null and status != ''\">"+ "and status = #{status} " + "</if>" +
		  		  "<if test=\"userIdCreate != null and userIdCreate != ''\">"+ "and user_id_create = #{userIdCreate} " + "</if>" +
		  		  "<if test=\"gmtCreate != null and gmtCreate != ''\">"+ "and gmt_create = #{gmtCreate} " + "</if>" +
		  		  "<if test=\"gmtModified != null and gmtModified != ''\">"+ "and gmt_modified = #{gmtModified} " + "</if>" +
		  		  "<if test=\"sex != null and sex != ''\">"+ "and sex = #{sex} " + "</if>" +
		  		  "<if test=\"birth != null and birth != ''\">"+ "and birth = #{birth} " + "</if>" +
		  		  "<if test=\"picId != null and picId != ''\">"+ "and pic_id = #{picId} " + "</if>" +
		  		  "<if test=\"liveAddress != null and liveAddress != ''\">"+ "and live_address = #{liveAddress} " + "</if>" +
		  		  "<if test=\"hobby != null and hobby != ''\">"+ "and hobby = #{hobby} " + "</if>" +
		  		  "<if test=\"province != null and province != ''\">"+ "and province = #{province} " + "</if>" +
		  		  "<if test=\"city != null and city != ''\">"+ "and city = #{city} " + "</if>" +
		  		  "<if test=\"district != null and district != ''\">"+ "and district = #{district} " + "</if>" +
		  			"</where>"+ 
			" <choose>" + 
	            "<when test=\"sort != null and sort.trim() != ''\">" + 
	                "order by ${sort} ${order}" + 
	            "</when>" + 
				"<otherwise>" + 
	                "order by user_id desc" +
				"</otherwise>" + 
	        "</choose>"+
			"<if test=\"offset != null and limit != null\">"+
			"limit #{offset}, #{limit}" + 
			"</if>"+
			"</script>")
	List<UserDO> list(Map<String,Object> map);
	
	@Select("<script>" +
	"select count(*) from tb_user " + 
			"<where>" + 
		  		  "<if test=\"userId != null and userId != ''\">"+ "and user_id = #{userId} " + "</if>" +
		  		  "<if test=\"username != null and username != ''\">"+ "and username like concat('%',#{username},'%') " + "</if>" +
		  		  "<if test=\"name != null and name != ''\">"+ "and name like concat('%',#{name},'%') " + "</if>" +
		  		  "<if test=\"password != null and password != ''\">"+ "and password = #{password} " + "</if>" +
		  		  "<if test=\"deptId != null and deptId != ''\">"+ "and dept_id = #{deptId} " + "</if>" +
		  		  "<if test=\"email != null and email != ''\">"+ "and email = #{email} " + "</if>" +
		  		  "<if test=\"mobile != null and mobile != ''\">"+ "and mobile = #{mobile} " + "</if>" +
		  		  "<if test=\"status != null and status != ''\">"+ "and status = #{status} " + "</if>" +
		  		  "<if test=\"userIdCreate != null and userIdCreate != ''\">"+ "and user_id_create = #{userIdCreate} " + "</if>" +
		  		  "<if test=\"gmtCreate != null and gmtCreate != ''\">"+ "and gmt_create = #{gmtCreate} " + "</if>" +
		  		  "<if test=\"gmtModified != null and gmtModified != ''\">"+ "and gmt_modified = #{gmtModified} " + "</if>" +
		  		  "<if test=\"sex != null and sex != ''\">"+ "and sex = #{sex} " + "</if>" +
		  		  "<if test=\"birth != null and birth != ''\">"+ "and birth = #{birth} " + "</if>" +
		  		  "<if test=\"picId != null and picId != ''\">"+ "and pic_id = #{picId} " + "</if>" +
		  		  "<if test=\"liveAddress != null and liveAddress != ''\">"+ "and live_address = #{liveAddress} " + "</if>" +
		  		  "<if test=\"hobby != null and hobby != ''\">"+ "and hobby = #{hobby} " + "</if>" +
		  		  "<if test=\"province != null and province != ''\">"+ "and province = #{province} " + "</if>" +
		  		  "<if test=\"city != null and city != ''\">"+ "and city = #{city} " + "</if>" +
		  		  "<if test=\"district != null and district != ''\">"+ "and district = #{district} " + "</if>" +
		  			"</where>"+ 
			"</script>")
	int count(Map<String,Object> map);
	
	@Insert("insert into tb_user (`user_id`,`username`, `name`, `password`, `dept_id`, `email`, `mobile`, `status`, `user_id_create`, `sex`, `birth`, `pic_id`, `live_address`, `hobby`, `province`, `city`, `district`)"
	+ "values (#{userId},#{username}, #{name}, #{password}, #{deptId}, #{email}, #{mobile}, #{status}, #{userIdCreate}, #{sex}, #{birth}, #{picId}, #{liveAddress}, #{hobby}, #{province}, #{city}, #{district})")
	int save(UserDO user);

    int saveSelective(UserDO user);
	
	@Update("<script>"+ 
			"update tb_user " + 
					"<set>" +
                    "<if test=\"username != null\">`username` = #{username}, </if>" +
                    "<if test=\"name != null\">`name` = #{name}, </if>" +
                    "<if test=\"password != null\">`password` = #{password}, </if>" +
                    "<if test=\"deptId != null\">`dept_id` = #{deptId}, </if>" +
                    "<if test=\"email != null\">`email` = #{email}, </if>" +
                    "<if test=\"mobile != null\">`mobile` = #{mobile}, </if>" +
                    "<if test=\"status != null\">`status` = #{status}, </if>" +
                    "<if test=\"userIdCreate != null\">`user_id_create` = #{userIdCreate}, </if>" +
                    "<if test=\"sex != null\">`sex` = #{sex}, </if>" +
                    "<if test=\"birth != null\">`birth` = #{birth}, </if>" +
                    "<if test=\"picId != null\">`pic_id` = #{picId}, </if>" +
                    "<if test=\"liveAddress != null\">`live_address` = #{liveAddress}, </if>" +
                    "<if test=\"hobby != null\">`hobby` = #{hobby}, </if>" +
                    "<if test=\"province != null\">`province` = #{province}, </if>" +
                    "<if test=\"city != null\">`city` = #{city}, </if>" +
                    "<if test=\"district != null\">`district` = #{district}, </if>" +
          					"</set>" + 
					"where user_id = #{userId}"+
			"</script>")
	int update(UserDO user);
	
	@Update("UPDATE tb_user set status = 2 where user_id =#{userId}")
	int remove(@Param("userId") Long userId);

	@Update("UPDATE tb_user set status = #{status} where user_id =#{userId}")
	int updateStatus(@Param("userId")Long userId,@Param("status")Integer status);
	
	@Delete("<script>"+ 
			"UPDATE tb_user set status = 2 where user_id in " +
					"<foreach item=\"userId\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" +
						"#{userId}" +
					"</foreach>"+
			"</script>")
	int batchRemove(Long[] userIds);
}

