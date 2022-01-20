delimiter $$
drop procedure if exists addColum$$
create procedure addColum() begin
    declare columnCount int;
    select count(column_name) into columnCount
    from information_schema.columns
    where table_name = 'store.tb_user' and column_name = 'role_id';

    if columnCount = 0 then
        alter table store.tb_user add role_id bigint(20) default NULL comment '用户角色id';
    end if;
end$$
call addColum()$$
drop procedure if exists addColum$$

delimiter ;

drop table if EXISTS store.tb_user_role;
update store.tb_user set role_id = 1;
-- drop procedure if exists addColum;
-- create procedure addColum()
-- begin
--     declare columnCount int;
--     select count(column_name) into columnCount
--     information_schema.columns
--     where table_name = 'tb_user' and column_name = 'dept_id';
--
--     if columnCount = 0 then
--         alter table tb_user add dept_id bigint(20) default NULL comment '用户部门id';
--     end if;
-- end;
-- alter table tb_user add role_id bigint(20) default NULL comment '用户角色id';
-- alter table tb_user add dept_id bigint(20) default NULL comment '用户部门id';

-- call addColum();
