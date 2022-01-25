-- 菜单SQL
INSERT INTO `tb_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    VALUES ('1', '上传文件信息', 'backend/file', 'backend:file:file', '1', 'fa', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `tb_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '查看', null, 'backend:file:detail', '2', null, '6';
INSERT INTO `tb_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '新增', null, 'backend:file:add', '2', null, '6';
INSERT INTO `tb_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '修改', null, 'backend:file:edit', '2', null, '6';
INSERT INTO `tb_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '删除', null, 'backend:file:remove', '2', null, '6';
INSERT INTO `tb_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '批量删除', null, 'backend:file:batchRemove', '2', null, '6';
