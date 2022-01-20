DROP TABLE IF EXISTS `sys_gen_columns`;
CREATE TABLE `sys_gen_columns` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   `table_name` varchar(64) CHARACTER SET utf8 DEFAULT '' COMMENT '表名',
                                   `column_name` varchar(64) CHARACTER SET utf8 DEFAULT '' COMMENT '列名',
                                   `column_type` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '列类型',
                                   `java_type` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '映射java类型',
                                   `column_comment` varchar(1024) CHARACTER SET utf8 DEFAULT '' COMMENT '列注释',
                                   `column_sort` tinyint(4) DEFAULT NULL COMMENT '列排序（升序）',
                                   `column_label` varchar(64) DEFAULT NULL COMMENT '鍒楁爣绛惧悕',
                                   `page_type` tinyint(4) DEFAULT '1' COMMENT '页面显示类型：1、文本框 2、下拉框 3、数值4、日期 5、文本域6、富文本 7、上传图片【单文件】 8、上传图片【多文件】9、上传文件【单文件】 10、上传文件【多文件】11、隐藏域 12、不显示',
                                   `is_required` tinyint(1) DEFAULT NULL COMMENT '是否必填',
                                   `dict_type` varchar(100) CHARACTER SET utf8 DEFAULT '' COMMENT '页面显示为下拉时使用，字典类型从字典表中取出',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_gen_table
-- ----------------------------
DROP TABLE IF EXISTS `sys_gen_table`;
CREATE TABLE `sys_gen_table` (
                                 `id` bigint(20) NOT NULL COMMENT '主键',
                                 `table_name` varchar(64) NOT NULL COMMENT '表名',
                                 `class_name` varchar(100) NOT NULL COMMENT '实体类名称',
                                 `comments` varchar(500) NOT NULL COMMENT '表说明',
                                 `category` tinyint(1) NOT NULL DEFAULT '0' COMMENT '分类：0：数据表，1：树表',
                                 `package_name` varchar(500) DEFAULT NULL COMMENT '生成包路径',
                                 `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
                                 `sub_module_name` varchar(30) DEFAULT NULL COMMENT '生成子模块名',
                                 `function_name` varchar(200) DEFAULT NULL COMMENT '生成功能名，用于类描述',
                                 `function_name_simple` varchar(50) DEFAULT NULL COMMENT '生成功能名（简写），用于功能提示，如“保存xx成功”',
                                 `author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
                                 `src_dir` varchar(1000) DEFAULT NULL COMMENT 'src目录',
                                 `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
                                 `create_by` bigint(20) NOT NULL COMMENT '创建者',
                                 `create_date` datetime NOT NULL COMMENT '创建时间',
                                 `update_by` bigint(20) NOT NULL COMMENT '更新者',
                                 `update_date` datetime NOT NULL COMMENT '更新时间',
                                 `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成表';

-- ----------------------------
-- Table structure for sys_gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `sys_gen_table_column`;
CREATE TABLE `sys_gen_table_column` (
                                        `id` bigint(20) NOT NULL COMMENT '主键',
                                        `table_id` bigint(20) NOT NULL COMMENT '表id',
                                        `column_name` varchar(64) NOT NULL COMMENT '列名',
                                        `column_sort` decimal(10,0) DEFAULT NULL COMMENT '列排序（升序）',
                                        `column_type` varchar(100) NOT NULL COMMENT '类型',
                                        `column_label` varchar(50) DEFAULT NULL COMMENT '列标签名',
                                        `comments` varchar(500) NOT NULL COMMENT '列备注说明',
                                        `attr_name` varchar(200) NOT NULL COMMENT '类的属性名',
                                        `attr_type` varchar(200) NOT NULL COMMENT '类的属性类型',
                                        `is_pk` char(1) DEFAULT NULL COMMENT '是否主键',
                                        `is_null` char(1) DEFAULT NULL COMMENT '是否可为空',
                                        `is_insert` char(1) DEFAULT NULL COMMENT '是否插入字段',
                                        `is_update` char(1) DEFAULT NULL COMMENT '是否更新字段',
                                        `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段',
                                        `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段',
                                        `query_type` varchar(200) DEFAULT NULL COMMENT '查询方式',
                                        `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段',
                                        `show_type` varchar(200) DEFAULT NULL COMMENT '表单类型',
                                        `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
                                        PRIMARY KEY (`id`),
                                        KEY `idx_gen_table_column_tn` (`table_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成表列';
-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `user_id` bigint(20) NOT NULL unique COMMENT '用户id',
                            `username` varchar(50) DEFAULT NULL COMMENT '用户名',
                            `name` varchar(100) DEFAULT NULL,
                            `password` varchar(50) DEFAULT NULL COMMENT '密码',
                            `dept_id` bigint(20) DEFAULT NULL,
                            `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
                            `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
                            `status` tinyint(4) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
                            `user_id_create` bigint(20) DEFAULT NULL COMMENT '创建用户id',
                            `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `gmt_modified` datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            `sex` tinyint(4) DEFAULT null COMMENT '性别 1 男，2:女',
                            `birth` datetime DEFAULT NULL COMMENT '出身日期',
                            `pic_id` bigint(20) DEFAULT NULL,
                            `live_address` varchar(500) DEFAULT NULL COMMENT '现居住地',
                            `hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
                            `province` varchar(255) DEFAULT NULL COMMENT '省份',
                            `city` varchar(255) DEFAULT NULL COMMENT '所在城市',
                            `district` varchar(255) DEFAULT NULL COMMENT '所在地区',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='后台用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1,'1', 'admin', '超级管理员', '57df11455a8bfc767eae31f472a61d09', '14', 'admin@example.com', '17699999999', '1', '1', now(), now(), '96', '2021-12-14 00:00:00', '148', 'ccc', '122;121;', '北京市', '北京市市辖区', '东城区');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
                            `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
                            `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识',
                            `remark` varchar(100) DEFAULT NULL COMMENT '备注',
                            `user_id_create` bigint(20) DEFAULT NULL COMMENT '创建用户id',
                            `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `gmt_modified` datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '超级用户角色', 'admin', '拥有最高权限', '2', now() , now());

DROP TABLE IF EXISTS `tb_dict`;
CREATE TABLE `tb_dict` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
                            `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '标签名',
                            `value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '数据值',
                            `type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
                            `description` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
                            `sort` decimal(10,0) DEFAULT NULL COMMENT '排序（升序）',
                            `parent_id` bigint(20) DEFAULT '0' COMMENT '父级编号',
                            `create_by` int(11) DEFAULT NULL COMMENT '创建者',
                            `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
                            `update_date` datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            `remarks` varchar(255)  DEFAULT NULL COMMENT '备注信息',
                            `del_flag` char(1)  DEFAULT '1' COMMENT '删除标记 0 删除，1 未删除',
                            PRIMARY KEY (`id`),
                            KEY `sys_dict_value` (`value`),
                            KEY `sys_dict_label` (`name`),
                            KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `tb_dict` VALUES ('1', '正常', '0', 'del_flag', '删除标记', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('3', '显示', '1', 'show_hide', '显示/隐藏', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('4', '隐藏', '0', 'show_hide', '显示/隐藏', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('5', '是', '1', 'yes_no', '是/否', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('6', '否', '0', 'yes_no', '是/否', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('7', '红色', 'red', 'color', '颜色值', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('8', '绿色', 'green', 'color', '颜色值', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('9', '蓝色', 'blue', 'color', '颜色值', '30', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('10', '黄色', 'yellow', 'color', '颜色值', '40', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('11', '橙色', 'orange', 'color', '颜色值', '50', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('12', '默认主题', 'default', 'theme', '主题方案', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('13', '天蓝主题', 'cerulean', 'theme', '主题方案', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('14', '橙色主题', 'readable', 'theme', '主题方案', '30', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('15', '红色主题', 'united', 'theme', '主题方案', '40', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('16', 'Flat主题', 'flat', 'theme', '主题方案', '60', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('17', '国家', '1', 'sys_area_type', '区域类型', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('18', '省份、直辖市', '2', 'sys_area_type', '区域类型', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('19', '地市', '3', 'sys_area_type', '区域类型', '30', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('20', '区县', '4', 'sys_area_type', '区域类型', '40', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('21', '公司', '1', 'sys_office_type', '机构类型', '60', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('22', '部门', '2', 'sys_office_type', '机构类型', '70', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('23', '小组', '3', 'sys_office_type', '机构类型', '80', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('24', '其它', '4', 'sys_office_type', '机构类型', '90', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('25', '综合部', '1', 'sys_office_common', '快捷通用部门', '30', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('26', '开发部', '2', 'sys_office_common', '快捷通用部门', '40', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('27', '人力部', '3', 'sys_office_common', '快捷通用部门', '50', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('28', '一级', '1', 'sys_office_grade', '机构等级', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('29', '二级', '2', 'sys_office_grade', '机构等级', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('30', '三级', '3', 'sys_office_grade', '机构等级', '30', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('31', '四级', '4', 'sys_office_grade', '机构等级', '40', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('32', '所有数据', '1', 'sys_data_scope', '数据范围', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('33', '所在公司及以下数据', '2', 'sys_data_scope', '数据范围', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('34', '所在公司数据', '3', 'sys_data_scope', '数据范围', '30', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('35', '所在部门及以下数据', '4', 'sys_data_scope', '数据范围', '40', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('36', '所在部门数据', '5', 'sys_data_scope', '数据范围', '50', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('37', '仅本人数据', '8', 'sys_data_scope', '数据范围', '90', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('38', '按明细设置', '9', 'sys_data_scope', '数据范围', '100', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('39', '系统管理', '1', 'sys_user_type', '用户类型', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('40', '部门经理', '2', 'sys_user_type', '用户类型', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('41', '普通用户', '3', 'sys_user_type', '用户类型', '30', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('42', '基础主题', 'basic', 'cms_theme', '站点主题', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('43', '蓝色主题', 'blue', 'cms_theme', '站点主题', '20', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('44', '红色主题', 'red', 'cms_theme', '站点主题', '30', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('45', '文章模型', 'article', 'cms_module', '栏目模型', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('46', '图片模型', 'picture', 'cms_module', '栏目模型', '20', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('47', '下载模型', 'download', 'cms_module', '栏目模型', '30', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('48', '链接模型', 'link', 'cms_module', '栏目模型', '40', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('49', '专题模型', 'special', 'cms_module', '栏目模型', '50', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('50', '默认展现方式', '0', 'cms_show_modes', '展现方式', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('51', '首栏目内容列表', '1', 'cms_show_modes', '展现方式', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('52', '栏目第一条内容', '2', 'cms_show_modes', '展现方式', '30', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('53', '发布', '0', 'cms_del_flag', '内容状态', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('54', '删除', '1', 'cms_del_flag', '内容状态', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('55', '审核', '2', 'cms_del_flag', '内容状态', '15', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('56', '首页焦点图', '1', 'cms_posid', '推荐位', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('57', '栏目页文章推荐', '2', 'cms_posid', '推荐位', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('58', '咨询', '1', 'cms_guestbook', '留言板分类', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('59', '建议', '2', 'cms_guestbook', '留言板分类', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('60', '投诉', '3', 'cms_guestbook', '留言板分类', '30', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('61', '其它', '4', 'cms_guestbook', '留言板分类', '40', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('62', '公休', '1', 'oa_leave_type', '请假类型', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('63', '病假', '2', 'oa_leave_type', '请假类型', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('64', '事假', '3', 'oa_leave_type', '请假类型', '30', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('65', '调休', '4', 'oa_leave_type', '请假类型', '40', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('66', '婚假', '5', 'oa_leave_type', '请假类型', '60', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('67', '接入日志', '1', 'sys_log_type', '日志类型', '30', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('68', '异常日志', '2', 'sys_log_type', '日志类型', '40', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('69', '请假流程', 'leave', 'act_type', '流程类型', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('70', '审批测试流程', 'test_audit', 'act_type', '流程类型', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('71', '分类1', '1', 'act_category', '流程分类', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('72', '分类2', '2', 'act_category', '流程分类', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('73', '增删改查', 'crud', 'gen_category', '代码生成分类', '10', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('74', '增删改查（包含从表）', 'crud_many', 'gen_category', '代码生成分类', '20', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('75', '树结构', 'tree', 'gen_category', '代码生成分类', '30', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('76', '=', '=', 'gen_query_type', '查询方式', '10', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('77', '!=', '!=', 'gen_query_type', '查询方式', '20', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('78', '&gt;', '&gt;', 'gen_query_type', '查询方式', '30', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('79', '&lt;', '&lt;', 'gen_query_type', '查询方式', '40', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('80', 'Between', 'between', 'gen_query_type', '查询方式', '50', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('81', 'Like', 'like', 'gen_query_type', '查询方式', '60', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('82', 'Left Like', 'left_like', 'gen_query_type', '查询方式', '70', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('83', 'Right Like', 'right_like', 'gen_query_type', '查询方式', '80', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('84', '文本框', 'input', 'gen_show_type', '字段生成方案', '10', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('85', '文本域', 'textarea', 'gen_show_type', '字段生成方案', '20', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('86', '下拉框', 'select', 'gen_show_type', '字段生成方案', '30', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('87', '复选框', 'checkbox', 'gen_show_type', '字段生成方案', '40', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('88', '单选框', 'radiobox', 'gen_show_type', '字段生成方案', '50', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('89', '日期选择', 'dateselect', 'gen_show_type', '字段生成方案', '60', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('90', '人员选择', 'userselect', 'gen_show_type', '字段生成方案', '70', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('91', '部门选择', 'officeselect', 'gen_show_type', '字段生成方案', '80', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('92', '区域选择', 'areaselect', 'gen_show_type', '字段生成方案', '90', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('93', 'String', 'String', 'gen_java_type', 'Java类型', '10', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('94', 'Long', 'Long', 'gen_java_type', 'Java类型', '20', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('95', '仅持久层', 'dao', 'gen_category', '代码生成分类', '40', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('96', '男', '1', 'sex', '性别', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('97', '女', '2', 'sex', '性别', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('98', 'Integer', 'Integer', 'gen_java_type', 'Java类型', '30', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('99', 'Double', 'Double', 'gen_java_type', 'Java类型', '40', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('100', 'Date', 'java.util.Date', 'gen_java_type', 'Java类型', '50', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('104', 'Custom', 'Custom', 'gen_java_type', 'Java类型', '90', '0', '1', now(), '1', now(), null, '1');
INSERT INTO `tb_dict` VALUES ('105', '会议通告', '1', 'oa_notify_type', '通知通告类型', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('106', '奖惩通告', '2', 'oa_notify_type', '通知通告类型', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('107', '活动通告', '3', 'oa_notify_type', '通知通告类型', '30', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('108', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('109', '发布', '1', 'oa_notify_status', '通知通告状态', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('110', '未读', '0', 'oa_notify_read', '通知通告状态', '10', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('111', '已读', '1', 'oa_notify_read', '通知通告状态', '20', '0', '1', now(), '1', now(), null, '0');
INSERT INTO `tb_dict` VALUES ('112', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', now(), '1', now(), '', '0');
INSERT INTO `tb_dict` VALUES ('113', '删除', '1', 'del_flag', '删除标记', null, null, null, now(), '1', now(),'', '');
INSERT INTO `tb_dict` VALUES ('121', '编码', 'code', 'hobby', '爱好', null, null, null, now(), '1', now(),'', '');
INSERT INTO `tb_dict` VALUES ('122', '绘画', 'painting', 'hobby', '爱好', null, null, null, now(), '1', now(),'', '');
INSERT INTO `tb_dict` VALUES ('123', 'Integer', 'Integer', 'java_type', 'Java数据类型', '1', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('124', 'Long', 'Long', 'java_type', 'Java数据类型', '2', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('125', 'Float', 'Float', 'java_type', 'Java数据类型', '3', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('126', 'Double', 'Double', 'java_type', 'Java数据类型', '4', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('127', 'BigDecimal', 'BigDecimal', 'java_type', 'Java数据类型', '5', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('128', 'Boolean', 'Boolean', 'java_type', 'Java数据类型', '6', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('129', 'String', 'String', 'java_type', 'Java数据类型', '7', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('130', 'Date', 'Date', 'java_type', 'Java数据类型', '8', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('131', '文本框', '1', 'page_type', '页面显示类型', '1', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('132', '下拉框', '2', 'page_type', '页面显示类型', '2', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('133', '数值', '3', 'page_type', '页面显示类型', '3', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('134', '日期', '4', 'page_type', '页面显示类型', '4', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('135', '文本域', '5', 'page_type', '页面显示类型', '5', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('136', '富文本', '6', 'page_type', '页面显示类型', '6', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('137', '上传图片【单文件】', '7', 'page_type', '页面显示类型', '7', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('138', '隐藏域', '11', 'page_type', '页面显示类型', '11', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('139', '不显示', '12', 'page_type', '页面显示类型', '12', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('140', '男频', '0', 'work_direction', '作品方向', '0', null, null, now(), '1', now(),'', null);
INSERT INTO `tb_dict` VALUES ('141', '女频', '1', 'work_direction', '作品方向', '1', null, null, now(), '1', now(),'', null);

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
                            `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
                            `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
                            `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
                            `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
                            `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
                            `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
                            `order_num` int(11) DEFAULT NULL COMMENT '排序',
                            `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `gmt_modified` datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '0', '基础管理', '', '', '0', 'fa fa-bars', '0', now(), now());
INSERT INTO `tb_menu` VALUES ('2', '3', '系统菜单', 'backend/menu/', 'sys:menu:menu', '1', 'fa fa-th-list', '2', now(), now());
INSERT INTO `tb_menu` VALUES ('3', '0', '系统管理', null, null, '0', 'fa fa-desktop', '1', now(), now());
INSERT INTO `tb_menu` VALUES ('6', '3', '用户管理', 'backend/user/', 'sys:user:user', '1', 'fa fa-user', '0', now(), now());
INSERT INTO `tb_menu` VALUES ('7', '3', '角色管理', 'backend/role', 'sys:role:role', '1', 'fa fa-paw', '1', now(), now());
INSERT INTO `tb_menu` VALUES ('12', '6', '新增', '', 'sys:user:add', '2', '', '0', now(), now());
INSERT INTO `tb_menu` VALUES ('13', '6', '编辑', '', 'sys:user:edit', '2', '', '0', now(), now());
INSERT INTO `tb_menu` VALUES ('14', '6', '删除', null, 'sys:user:remove', '2', null, '0', now(), now());
INSERT INTO `tb_menu` VALUES ('15', '7', '新增', '', 'sys:role:add', '2', '', '0', now(), now());
INSERT INTO `tb_menu` VALUES ('20', '2', '新增', '', 'sys:menu:add', '2', '', '0', now(), now());
INSERT INTO `tb_menu` VALUES ('21', '2', '编辑', '', 'sys:menu:edit', '2', '', '0', now(), now());
INSERT INTO `tb_menu` VALUES ('22', '2', '删除', '', 'sys:menu:remove', '2', '', '0', now(), now());
INSERT INTO `tb_menu` VALUES ('24', '6', '批量删除', '', 'sys:user:batchRemove', '2', '', '0', now(), now());
INSERT INTO `tb_menu` VALUES ('25', '6', '停用', null, 'sys:user:disable', '2', null, '0', now(), now());
INSERT INTO `tb_menu` VALUES ('26', '6', '重置密码', '', 'sys:user:resetPwd', '2', '', '0', now(), now());
INSERT INTO `tb_menu` VALUES ('27', '91', '系统日志', 'common/log', 'common:log', '1', 'fa fa-warning', '0', now(), now());
INSERT INTO `tb_menu` VALUES ('28', '27', '刷新', null, 'sys:log:list', '2', null, '0', now(), now());
INSERT INTO `tb_menu` VALUES ('29', '27', '删除', null, 'sys:log:remove', '2', null, '0', now(), now());
INSERT INTO `tb_menu` VALUES ('30', '27', '清空', null, 'sys:log:clear', '2', null, '0', now(), now());
INSERT INTO `tb_menu` VALUES ('48', '77', '代码生成', 'common/generator', 'common:generator', '1', 'fa fa-code', '3', now(), now());
INSERT INTO `tb_menu` VALUES ('55', '7', '编辑', '', 'sys:role:edit', '2', '', null, now(), now());
INSERT INTO `tb_menu` VALUES ('56', '7', '删除', '', 'sys:role:remove', '2', null, null, now(),now());
INSERT INTO `tb_menu` VALUES ('57', '91', '运行监控', '/druid/index.html', '', '1', 'fa fa-caret-square-o-right', '1', now(),now());
INSERT INTO `tb_menu` VALUES ('61', '2', '批量删除', '', 'sys:menu:batchRemove', '2', null, null, now(),now());
INSERT INTO `tb_menu` VALUES ('62', '7', '批量删除', '', 'sys:role:batchRemove', '2', null, null, now(),now());
INSERT INTO `tb_menu` VALUES ('71', '1', '文件管理', '/common/sysFile', 'common:sysFile:sysFile', '1', 'fa fa-folder-open', '2', now(),now());
INSERT INTO `tb_menu` VALUES ('73', '3', '部门管理', '/backend/dept', 'system:sysDept:sysDept', '1', 'fa fa-users', '3', now(),now());
INSERT INTO `tb_menu` VALUES ('74', '73', '增加', '/backend/dept/add', 'system:sysDept:add', '2', null, '1', now(),now());
INSERT INTO `tb_menu` VALUES ('75', '73', '刪除', '/backend/dept/remove', 'system:sysDept:remove', '2', null, '2', now(),now());
INSERT INTO `tb_menu` VALUES ('76', '73', '编辑', '/backend/dept/edit', 'system:sysDept:edit', '2', null, '3', now(),now());
INSERT INTO `tb_menu` VALUES ('77', '0', '研发工具', '', '', '0', 'fa fa-gear', '5', now(),now());
INSERT INTO `tb_menu` VALUES ('78', '1', '数据字典', '/backend/dict', 'common:dict:dict', '1', 'fa fa-book', '1', now(),now());
INSERT INTO `tb_menu` VALUES ('79', '78', '增加', '/backend/dict/add', 'common:dict:add', '2', null, '2', now(),now());
INSERT INTO `tb_menu` VALUES ('80', '78', '编辑', '/backend/dict/edit', 'common:dict:edit', '2', null, '2', now(),now());
INSERT INTO `tb_menu` VALUES ('81', '78', '删除', '/backend/dict/remove', 'common:dict:remove', '2', '', '3', now(),now());
INSERT INTO `tb_menu` VALUES ('83', '78', '批量删除', '/backend/dict/batchRemove', 'common:dict:batchRemove', '2', '', '4', now(),now());
INSERT INTO `tb_menu` VALUES ('91', '0', '系统监控', '', '', '0', 'fa fa-video-camera', '4', now(),now());
INSERT INTO `tb_menu` VALUES ('92', '91', '在线用户', 'backend/online', '', '1', 'fa fa-user', null, now(),now());
INSERT INTO `tb_menu` VALUES ('104', '77', 'swagger', '/swagger-ui.html', '', '1', '', null, now(),now());
INSERT INTO `tb_menu` VALUES ('202', '0', '测试管理', '', '', '0', 'fa fa-s15', '12', now(),now());
INSERT INTO `tb_menu` VALUES ('203', '202', '订单管理', 'test/order', 'test:order:order', '1', '', '1', now(),now());
INSERT INTO `tb_menu` VALUES ('204', '203', '新增', '', 'test:order:add', '2', '', null, now(),now());
INSERT INTO `tb_menu` VALUES ('205', '203', '编辑', '', 'test:order:edit', '2', '', null, now(),now());
INSERT INTO `tb_menu` VALUES ('206', '203', '删除', '', 'test:order:remove', '2', '', null, now(),now());
INSERT INTO `tb_menu` VALUES ('207', '203', '批量删除', '', 'test:order:batchRemove', '2', '', null, now(),now());
INSERT INTO `tb_menu` VALUES ('208', '203', '详情', '', 'test:order:detail', '2', '', '0', now(),now());
INSERT INTO `tb_menu` VALUES ('209', '3', '数据权限', 'backend/dataPerm', 'system:dataPerm:dataPerm', '1', 'fa', '6', now(),now());
INSERT INTO `tb_menu` VALUES ('210', '209', '查看', null, 'backend:dataPerm:detail', '2', null, '6', now(),now());
INSERT INTO `tb_menu` VALUES ('211', '209', '新增', null, 'backend:dataPerm:add', '2', null, '6', now(),now());
INSERT INTO `tb_menu` VALUES ('212', '209', '修改', null, 'backend:dataPerm:edit', '2', null, '6', now(),now());
INSERT INTO `tb_menu` VALUES ('213', '209', '删除', null, 'backend:dataPerm:remove', '2', null, '6', now(),now());
INSERT INTO `tb_menu` VALUES ('214', '209', '批量删除', null, 'backend:dataPerm:batchRemove', '2', null, '6', now(),now());
INSERT INTO `tb_menu` VALUES ('221', '0', '作家管理', '', '', '0', 'fa fa-user-o', '10', now(),now());
INSERT INTO `tb_menu` VALUES ('222', '221', '作者列表', 'novel/author', 'novel:author:author', '1', 'fa', '6', now(),now());
INSERT INTO `tb_menu` VALUES ('223', '222', '查看', null, 'novel:author:detail', '2', null, '6', now(),now());
INSERT INTO `tb_menu` VALUES ('224', '222', '新增', null, 'novel:author:add', '2', null, '6', now(),now());
INSERT INTO `tb_menu` VALUES ('225', '222', '修改', null, 'novel:author:edit', '2', null, '6', now(),now());
INSERT INTO `tb_menu` VALUES ('226', '222', '删除', null, 'novel:author:remove', '2', null, '6', now(),now());
INSERT INTO `tb_menu` VALUES ('227', '222', '批量删除', null, 'novel:author:batchRemove', '2', null, '6', now(),now());
INSERT INTO `tb_menu` VALUES ('228', '221', '邀请码管理', 'novel/authorCode', 'novel:authorCode:authorCode', '1', 'fa', '3', now(),now());
INSERT INTO `tb_menu` VALUES ('229', '228', '查看', null, 'novel:authorCode:detail', '2', null, '6', now(),now());
INSERT INTO `tb_menu` VALUES ('230', '228', '新增', null, 'novel:authorCode:add', '2', null, '6', now(),now());
INSERT INTO `tb_menu` VALUES ('231', '228', '修改', null, 'novel:authorCode:edit', '2', null, '6', now(),now());
INSERT INTO `tb_menu` VALUES ('232', '228', '删除', null, 'novel:authorCode:remove', '2', null, '6', now(),now());
INSERT INTO `tb_menu` VALUES ('233', '228', '批量删除', null, 'novel:authorCode:batchRemove', '2', null, '6', now(),now());
INSERT INTO `tb_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `gmt_create`, `gmt_modified`) VALUES (246, 241, '批量删除', NULL, 'novel:news:batchRemove', 2, NULL, 6, now(),now());
INSERT INTO `tb_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `gmt_create`, `gmt_modified`) VALUES (245, 241, '删除', NULL, 'novel:news:remove', 2, NULL, 6, now(),now());
INSERT INTO `tb_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `gmt_create`, `gmt_modified`) VALUES (244, 241, '修改', NULL, 'novel:news:edit', 2, NULL, 6, now(),now());
INSERT INTO `tb_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `gmt_create`, `gmt_modified`) VALUES (243, 241, '新增', NULL, 'novel:news:add', 2, NULL, 6, now(),now());
INSERT INTO `tb_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `gmt_create`, `gmt_modified`) VALUES (242, 241, '查看', NULL, 'novel:news:detail', 2, NULL, 6, now(),now());
INSERT INTO `tb_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `gmt_create`, `gmt_modified`) VALUES (241, 234, '新闻列表', 'novel/news', 'novel:news:news', 1, 'fa', 8, now(),now());
INSERT INTO `tb_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `gmt_create`, `gmt_modified`) VALUES (240, 235, '批量删除', NULL, 'novel:category:batchRemove', 2, NULL, 6, now(),now());
INSERT INTO `tb_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `gmt_create`, `gmt_modified`) VALUES (239, 235, '删除', NULL, 'novel:category:remove', 2, NULL, 6, now(),now());
INSERT INTO `tb_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `gmt_create`, `gmt_modified`) VALUES (238, 235, '修改', NULL, 'novel:category:edit', 2, NULL, 6, now(),now());
INSERT INTO `tb_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `gmt_create`, `gmt_modified`) VALUES (237, 235, '新增', NULL, 'novel:category:add', 2, NULL, 6, now(),now());
INSERT INTO `tb_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `gmt_create`, `gmt_modified`) VALUES (236, 235, '查看', NULL, 'novel:category:detail', 2, NULL, 6, now(),now());
INSERT INTO `tb_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `gmt_create`, `gmt_modified`) VALUES (235, 234, '类别管理', 'novel/category', 'novel:category:category', 1, 'fa', 6, now(),now());
INSERT INTO `tb_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `gmt_create`, `gmt_modified`) VALUES (234, 0, '新闻管理', '', '', 0, 'fa fa-newspaper-o', 8, now(),now());

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                 `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

INSERT INTO `tb_user_role` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
                                 `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '225');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '224');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '223');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '208');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '207');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '206');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '205');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '204');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '92');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '57');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '30');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '29');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '28');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '104');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '48');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '214');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '213');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '212');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '211');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '210');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '76');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '75');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '74');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '62');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '56');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '55');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '15');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '26');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '25');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '24');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '14');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '13');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '12');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '61');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '22');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '21');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '20');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '83');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '81');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '80');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '79');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '71');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '222');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '203');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '202');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '27');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '91');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '77');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '209');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '73');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '7');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '6');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '2');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '3');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '78');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '1');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '228');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '233');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '232');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '231');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '230');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '229');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '221');
INSERT INTO `tb_role_menu`(`role_id`,`menu_id`) VALUES ('1', '-1');

-- ----------------------------
-- Table structure for tb_dept
-- ----------------------------
DROP TABLE IF EXISTS `tb_dept`;
CREATE TABLE `tb_dept` (
                            `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
                            `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
                            `order_num` int(11) DEFAULT NULL COMMENT '排序',
                            `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
                            PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='部门管理';