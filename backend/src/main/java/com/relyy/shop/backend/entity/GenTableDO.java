package com.relyy.shop.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Accessors(chain = true)
@Data
@TableName("sys_gen_table")
public class GenTableDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String tableName;

    private String className;

    private String tableComment;

    private String tableEngine;

    private String tableCharset;

    private String packageName;

    private String moduleName;

    private String srcDir;

    /**
     * '要执行的操作，add 新增； del 删除； update 更新；done 完成；unable 未使用 默认'
     */
    private String status;

    private Long createUser;
    private Long updateUser;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
}
