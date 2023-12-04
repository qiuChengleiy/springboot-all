package com.springboot.all.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.TableName;
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
    private Long id;
    private String name;
    private Integer age;
}
