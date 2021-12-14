package com.relyy.shop.backend.mapper;

import com.relyy.shop.backend.entity.DeptDO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
 * 部门管理
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:55:05
 */
@Mapper
public interface DeptMapper extends BaseMapper<DeptDO> {

	@Select("select `dept_id`, `parent_id`, `name`, `order_num`, `del_flag` from tb_dept where dept_id = #{id}")
	DeptDO get(Long deptId);
	
	@Select("<script>" +
	"select * from tb_dept " + 
			"<where>" + 
		  		  "<if test=\"deptId != null and deptId != ''\">"+ "and dept_id = #{deptId} " + "</if>" +
		  		  "<if test=\"parentId != null and parentId != ''\">"+ "and parent_id = #{parentId} " + "</if>" +
		  		  "<if test=\"name != null and name != ''\">"+ "and name = #{name} " + "</if>" +
		  		  "<if test=\"orderNum != null and orderNum != ''\">"+ "and order_num = #{orderNum} " + "</if>" +
		  		  "<if test=\"delFlag != null and delFlag != ''\">"+ "and del_flag = #{delFlag} " + "</if>" +
		  			"</where>"+ 
			" <choose>" + 
	            "<when test=\"sort != null and sort.trim() != ''\">" + 
	                "order by ${sort} ${order}" + 
	            "</when>" + 
				"<otherwise>" + 
	                "order by dept_id desc" +
				"</otherwise>" + 
	        "</choose>"+
			"<if test=\"offset != null and limit != null\">"+
			"limit #{offset}, #{limit}" + 
			"</if>"+
			"</script>")
	List<DeptDO> list(Map<String,Object> map);
	
	@Select("<script>" +
	"select count(*) from tb_dept " + 
			"<where>" + 
		  		  "<if test=\"deptId != null and deptId != ''\">"+ "and dept_id = #{deptId} " + "</if>" +
		  		  "<if test=\"parentId != null and parentId != ''\">"+ "and parent_id = #{parentId} " + "</if>" +
		  		  "<if test=\"name != null and name != ''\">"+ "and name = #{name} " + "</if>" +
		  		  "<if test=\"orderNum != null and orderNum != ''\">"+ "and order_num = #{orderNum} " + "</if>" +
		  		  "<if test=\"delFlag != null and delFlag != ''\">"+ "and del_flag = #{delFlag} " + "</if>" +
		  			"</where>"+ 
			"</script>")
	int count(Map<String,Object> map);
	
	@Insert("insert into tb_dept (`parent_id`, `name`, `order_num`, `del_flag`)"
	+ "values (#{parentId}, #{name}, #{orderNum}, #{delFlag})")
	int save(DeptDO dept);

    int saveSelective(DeptDO dept);
	
	@Update("<script>"+ 
			"update tb_dept " + 
					"<set>" + 
		            "<if test=\"deptId != null\">`dept_id` = #{deptId}, </if>" +
                    "<if test=\"parentId != null\">`parent_id` = #{parentId}, </if>" +
                    "<if test=\"name != null\">`name` = #{name}, </if>" +
                    "<if test=\"orderNum != null\">`order_num` = #{orderNum}, </if>" +
                    "<if test=\"delFlag != null\">`del_flag` = #{delFlag}, </if>" +
          					"</set>" + 
					"where dept_id = #{deptId}"+
			"</script>")
	int update(DeptDO dept);
	
	@Delete("delete from tb_dept where dept_id =#{deptId}")
	int remove(Long dept_id);
	
	@Delete("<script>"+ 
			"delete from tb_dept where dept_id in " +
					"<foreach item=\"deptId\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" +
						"#{deptId}" +
					"</foreach>"+
			"</script>")
	int batchRemove(Long[] deptIds);
}
