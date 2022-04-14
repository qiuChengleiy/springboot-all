package com.example.app.mapper;

import com.example.app.model.UserRole;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/13 10:54 AM
 * @description
 */
public interface UserRoleMapper {
    List<UserRole> getRoles();

    UserRole getRole(Integer id);

    int updateRole(Integer id);

    int addRole(String userRole);

}
