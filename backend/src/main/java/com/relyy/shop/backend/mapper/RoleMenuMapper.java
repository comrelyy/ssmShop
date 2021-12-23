package com.relyy.shop.backend.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description 角色与菜单的关系
 * @Created by Reeve Cai
 * @Date 2021/12/23
 */
@Mapper
public interface RoleMenuMapper {

	@Select("select menu_id from tb_role_menu where role_id = #{roleId}")
	List<Long> getMenuIdByRoleId(Long roleId);

	@Insert("insert into tb_role_menu(menu_id,role_id) values(#{menuId},#{roleId})")
	int insertOne(@Param("menuId")Long menuId,@Param("roleId")Long roleId);

	@Insert("<script>" +
			"insert into tb_role_menu(menu_id,role_id) values " +
			"<foreach collection=\"menuIds\" item=\"menuId\"  separator=\",\">" +
			"(#{menuId},#{roleId})" +
			"</foreach>" +
			"</script>")
	int batchInsert(List<Long> menuIds,Long roleId);

	@Delete("delete from tb_role_menu where role_id = #{roleId}")
	int delByRoleId(Long roleId);

	@Delete("<script>" +
			"delete from tb_role_menu where role_id = #{roleId} and menu_id in" +
			"<foreach item=\"menuId\" collection=\"menuIds\"  open=\"(\" separator=\",\" close=\")\">" +
			"#{menuId}" +
			"</foreach>" +
			"</script>")
	int batchDel(@Param("menuIds")List<Long> menuIds,@Param("roleId")Long roleId);
}
