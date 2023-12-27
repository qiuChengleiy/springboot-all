package com.springboot.all.mybatisplus.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.all.mybatisplus.entity.User;
import com.springboot.all.mybatisplus.mapper.UserMapper;
import com.springboot.all.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/all")
    @ResponseBody
    public List<User> getUserList() {
      //  return userService.getUserInfo();
        return null;
    }

    @GetMapping("/save")
    @ResponseBody
    public void saveUser() {
//        User user = new User();
//        user.setName("小明");
//        user.setAge(18);
//        user.setVersion(1);
//        userService.save(user);

//        User user1 = new User();
//        user1.setName("小明1");
//        user1.setAge(20);
//        user1.setVersion(1);
//
//        User user2 = new User();
//        user2.setName("小明2");
//        user2.setAge(21);
//        user2.setVersion(1);
//        List<User> userList = new ArrayList<>();
//        userList.add(user1);
//        userList.add(user2);
//        userService.saveBatch(userList);


        User user = new User();
        user.setName("小明");
        user.setAge(18);
        user.setVersion(1);
        userMapper.insert(user);
    }

    @GetMapping("/update")
    @ResponseBody
    public void updateUser() {
        User user = new User();
        user.setId(1731552348470849545L);
        user.setName("小明-UPDATE");
        userService.updateById(user);

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","小明1");
        User user1 = new User();
        user1.setName("小明1-UPDATE");
        userService.update(user1, updateWrapper);

        userMapper.update(updateWrapper);
    }

    @GetMapping("/saveOrUpdate")
    @ResponseBody
    public void saveOrUpdateUser() {
//        User user = new User();
//        user.setName("小明-saveOrUpdate");
//        userService.saveOrUpdate(user);
//
//        User user1 = new User();
//        user1.setId(1731552348470849551L);
//        user1.setName("小明2");
//        userService.saveOrUpdate(user1);

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","小明-saveOrUpdate");
        User user1 = new User();
        user1.setName("小明-saveOrUpdate1");
        userService.saveOrUpdate(user1, updateWrapper);
    }

    @GetMapping("/remove")
    @ResponseBody
    public void remove() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name","小明-saveOrUpdate1");
        userService.remove(queryWrapper);


        Map<String, Object> map = new HashMap<>();
        map.put("name", "小明2");
        userService.removeByMap(map);

        userMapper.delete(queryWrapper);
    }

    @GetMapping("/page")
    @ResponseBody
    public Object page() {
        // 创建Page对象，指定当前页和每页显示的数据条数
        Page<User> page = new Page<>(1, 10);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","%小明%");
        page = userService.page(page, queryWrapper);

        // 获取总记录数
        long size = page.getSize();
        long total = page.getTotal();
        long current = page.getCurrent();
        // 获取分页数据列表
        List<User> list = page.getRecords();

        JSONObject json = new JSONObject();
        json.put("size", size);
        json.put("total", total);
        json.put("current", current);
        json.put("list", list);
        return json;
    }

    @GetMapping("/activeRecord")
    @ResponseBody
    public void activeRecord() {
        User user = new User();
        user.setName("小明");
        user.setAge(18);
        user.setVersion(1);
        user.insert();

        List<User> list = user.selectAll();

        User user1 = new User();
        user1.setId(1731552348470849545L);
        user1.setName("小明hhh");
        user1.setAge(18);
        user1.setVersion(1);
        user1.updateById();

        user1.deleteById();
        user1.deleteById(1731552348470849545L);

    }
}
