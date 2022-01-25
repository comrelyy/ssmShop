DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT,
    `file_name`    varchar(128) NOT NULL COMMENT '文件名称',
    `file_type`    int(11) DEFAULT NULL COMMENT '文件类型',
    `url`          varchar(200)          DEFAULT NULL COMMENT 'URL地址',
    `status`       tinyint(2) DEFAULT '1' COMMENT '1 有效 0 已删除',
    `create_user`  bigint(20) DEFAULT NULL COMMENT '创建用户id',
    `gmt_create`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_user`  bigint(20) DEFAULT NULL COMMENT '修改用户id',
    `gmt_modified` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='上传文件信息';