package com.springboot.all.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

/**
 * @Author qcl
 * @Description
 * @Date 11:09 AM 12/4/2023
 */
@Builder
@Data
@TableName("`user`")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "name", condition = SqlCondition.LIKE)
    private String name;
    private Integer age;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;
}
