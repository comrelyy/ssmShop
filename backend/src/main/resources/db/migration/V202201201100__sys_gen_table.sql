DROP TABLE IF EXISTS `sys_gen_columns`;
CREATE TABLE `sys_gen_columns`
(
    `id`             int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `table_name`     varchar(64)    DEFAULT '' COMMENT '表名',
    `column_name`    varchar(64)    DEFAULT '' COMMENT '列名',
    `column_type`    varchar(64)    DEFAULT NULL COMMENT '列类型',
    `column_key`    varchar(64)    DEFAULT NULL COMMENT '列类型',
    `java_type`      varchar(64)    DEFAULT NULL COMMENT '映射java类型',
    `column_comment` varchar(256)  DEFAULT '' COMMENT '列注释',
    `column_sort`    tinyint(4)     DEFAULT NULL COMMENT '列排序（升序）',
    `column_label`   varchar(256)    DEFAULT NULL COMMENT '列标签',
    `page_type`      tinyint(4)     DEFAULT '1' COMMENT '页面显示类型：1、文本框 2、下拉框 3、数值4、日期 5、文本域6、富文本 7、上传图片【单文件】 8、上传图片【多文件】9、上传文件【单文件】 10、上传文件【多文件】11、隐藏域 12、不显示',
    `is_required`    tinyint(1)     DEFAULT NULL COMMENT '是否必填',
    `default_value`  varchar(64)     DEFAULT NULL COMMENT '默认值',
    `extra`          varchar(64)     DEFAULT NULL COMMENT '其他信息',
    `dict_type`      varchar(100)   DEFAULT '' COMMENT '页面显示为下拉时使用，字典类型从字典表中取出',
    `status`         varchar(32)    DEFAULT 'unable' COMMENT '要执行的操作，add 新增； del 删除； update 更新；done 完成；unable 未使用 ',
    `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户id',
    `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_user` bigint(20) DEFAULT NULL COMMENT '修改用户id',
    `gmt_modified` datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT='生成表对应的列';

DROP TABLE IF EXISTS `sys_gen_table`;
CREATE TABLE `sys_gen_table`
(
    `id`             int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `table_name`     varchar(64) NOT NULL COMMENT '表名',
    `class_name`     varchar(64) DEFAULT NULL COMMENT '实体类名称',
    `table_comment`  varchar(64)  DEFAULT NULL COMMENT '表说明',
    `table_engine`   varchar(32) DEFAULT 'InnoDb' COMMENT '数据表引擎',
    `table_charset`  varchar(32) DEFAULT 'utf8mb4' COMMENT '数据表字符类型',
    `package_name`   varchar(256)DEFAULT NULL COMMENT '生成包路径',
    `module_name`    varchar(256)DEFAULT NULL COMMENT '生成模块名',
    `src_dir`        varchar(256)DEFAULT NULL COMMENT 'src目录',
    `status`         varchar(32) DEFAULT 'unable' COMMENT '要执行的操作，add 新增； del 删除； update 更新；done 完成；unable 未使用 ',
    `create_user`    bigint(20)  DEFAULT NULL COMMENT '创建用户id',
    `gmt_create`     datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_user`    bigint(20)  DEFAULT NULL COMMENT '修改用户id',
    `gmt_modified`   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT ='代码生成表';
DROP TABLE IF EXISTS `sys_gen_table_column`;

