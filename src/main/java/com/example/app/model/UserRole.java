package com.example.app.model;

import java.io.Serializable;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/13 10:57 AM
 * @description
 */
public class UserRole implements Serializable {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    private String name;

    private String roleName;
}
