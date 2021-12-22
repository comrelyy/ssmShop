package com.relyy.shop.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.relyy.shop.backend.entity.MenuDO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
/**
 * 菜单管理
 * 
 * @author Reeve Cai
 * @email relyy2014@163.com
 * @date 2021-12-10 14:52:17
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuDO> {

	@Select("select `menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `gmt_create`, `gmt_modified` from tb_menu where menu_id = #{id}")
	MenuDO get(Long menuId);
	
	@Select("<script>" +
	"select * from tb_menu " + 
			"<where>" + 
		  		  "<if test=\"menuId != null and menuId != ''\">"+ "and menu_id = #{menuId} " + "</if>" +
		  		  "<if test=\"parentId != null and parentId != ''\">"+ "and parent_id = #{parentId} " + "</if>" +
		  		  "<if test=\"name != null and name != ''\">"+ "and name = #{name} " + "</if>" +
		  		  "<if test=\"url != null and url != ''\">"+ "and url = #{url} " + "</if>" +
		  		  "<if test=\"perms != null and perms != ''\">"+ "and perms = #{perms} " + "</if>" +
		  		  "<if test=\"type != null and type != ''\">"+ "and type = #{type} " + "</if>" +
		  		  "<if test=\"icon != null and icon != ''\">"+ "and icon = #{icon} " + "</if>" +
		  		  "<if test=\"orderNum != null and orderNum != ''\">"+ "and order_num = #{orderNum} " + "</if>" +
		  		  "<if test=\"gmtCreate != null and gmtCreate != ''\">"+ "and gmt_create = #{gmtCreate} " + "</if>" +
		  		  "<if test=\"gmtModified != null and gmtModified != ''\">"+ "and gmt_modified = #{gmtModified} " + "</if>" +
		  			"</where>"+ 
			" <choose>" + 
	            "<when test=\"sort != null and sort.trim() != ''\">" + 
	                "order by ${sort} ${order}" + 
	            "</when>" + 
				"<otherwise>" + 
	                "order by menu_id desc" +
				"</otherwise>" + 
	        "</choose>"+
			"<if test=\"offset != null and limit != null\">"+
			"limit #{offset}, #{limit}" + 
			"</if>"+
			"</script>")
	List<MenuDO> list(Map<String,Object> map);
	
	@Select("<script>" +
	"select count(*) from tb_menu " + 
			"<where>" + 
		  		  "<if test=\"menuId != null and menuId != ''\">"+ "and menu_id = #{menuId} " + "</if>" +
		  		  "<if test=\"parentId != null and parentId != ''\">"+ "and parent_id = #{parentId} " + "</if>" +
		  		  "<if test=\"name != null and name != ''\">"+ "and name = #{name} " + "</if>" +
		  		  "<if test=\"url != null and url != ''\">"+ "and url = #{url} " + "</if>" +
		  		  "<if test=\"perms != null and perms != ''\">"+ "and perms = #{perms} " + "</if>" +
		  		  "<if test=\"type != null and type != ''\">"+ "and type = #{type} " + "</if>" +
		  		  "<if test=\"icon != null and icon != ''\">"+ "and icon = #{icon} " + "</if>" +
		  		  "<if test=\"orderNum != null and orderNum != ''\">"+ "and order_num = #{orderNum} " + "</if>" +
		  		  "<if test=\"gmtCreate != null and gmtCreate != ''\">"+ "and gmt_create = #{gmtCreate} " + "</if>" +
		  		  "<if test=\"gmtModified != null and gmtModified != ''\">"+ "and gmt_modified = #{gmtModified} " + "</if>" +
		  			"</where>"+ 
			"</script>")
	int count(Map<String,Object> map);
	
	@Insert("insert into tb_menu (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)"
	+ "values (#{parentId}, #{name}, #{url}, #{perms}, #{type}, #{icon}, #{orderNum})")
	int save(MenuDO menu);

    int saveSelective(MenuDO menu);
	
	@Update("<script>"+ 
			"update tb_menu " + 
					"<set>" + 
		            "<if test=\"menuId != null\">`menu_id` = #{menuId}, </if>" +
                    "<if test=\"parentId != null\">`parent_id` = #{parentId}, </if>" +
                    "<if test=\"name != null\">`name` = #{name}, </if>" +
                    "<if test=\"url != null\">`url` = #{url}, </if>" +
                    "<if test=\"perms != null\">`perms` = #{perms}, </if>" +
                    "<if test=\"type != null\">`type` = #{type}, </if>" +
                    "<if test=\"icon != null\">`icon` = #{icon}, </if>" +
                    "<if test=\"orderNum != null\">`order_num` = #{orderNum}, </if>" +
          					"</set>" + 
					"where menu_id = #{menuId}"+
			"</script>")
	int update(MenuDO menu);
	
	@Delete("delete from tb_menu where menu_id =#{menuId}")
	int remove(Long menu_id);
	
	@Delete("<script>"+ 
			"delete from tb_menu where menu_id in " +
					"<foreach item=\"menuId\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" +
						"#{menuId}" +
					"</foreach>"+
			"</script>")
	int batchRemove(Long[] menuIds);

	@Select("select distinct m.menu_id , parent_id, name, url,perms,`type`,icon,order_num,gmt_create, gmt_modified " +
			"from tb_menu m " +
			"left join tb_role_menu rm on m.menu_id = rm.menu_id " +
			"left join tb_user_role u on rm.role_id =ur.role_id " +
			"where ur.user_id = #{id} " +
			"and m.type in(0,1) " +
			"order by m.order_num")
	List<MenuDO> listMenuByUserId(Long userId);
}
