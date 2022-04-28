package com.example.shiro.dao;

import lombok.Data;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/26 11:13 AM
 * @description
 */
@Data
public class User {
    public Integer id;
    public String username;
    public String password;
    public String role;
    public String state;
    public String permission;
}
