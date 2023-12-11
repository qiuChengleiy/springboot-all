package com.springboot.all.mybatisplus.test;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.all.mybatisplus.MyBatisPlusApp;
import com.springboot.all.mybatisplus.mapper.UserMapper;
import com.springboot.all.mybatisplus.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Author qcl
 * @Description
 * @Date 12:11 PM 12/4/2023
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MyBatisPlusApp.class})
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectListTest() {
        // select all
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "user list size 不等于 5");
    }

    @Test
    public void selectByName() {
        // select all
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("name", "x%");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        //User(id=1731552348403740673, name=xiaohong2, age=22)
        //User(id=1731552348403740674, name=xiaohong3, age=23)
        //User(id=1731552348470849537, name=xiaohong4, age=24)
        //User(id=1731552348470849538, name=xiaohong5, age=25)
        //User(id=1731552348470849539, name=xiaohong6, age=26)
    }

    @Test
    public void selectByConditionName() {
        // select all
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(User.builder().name("x").build());
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        //User(id=1731552348403740673, name=xiaohong2, age=22)
        //User(id=1731552348403740674, name=xiaohong3, age=23)
        //User(id=1731552348470849537, name=xiaohong4, age=24)
        //User(id=1731552348470849538, name=xiaohong5, age=25)
        //User(id=1731552348470849539, name=xiaohong6, age=26)
    }

    @Test
    public void insertTest() {
        // insert
//        userMapper.insert(User.builder().name("xiaohong1").age(21).build());
//        userMapper.insert(User.builder().name("xiaohong2").age(22).build());
//        userMapper.insert(User.builder().name("xiaohong3").age(23).build());
//        userMapper.insert(User.builder().name("xiaohong4").age(24).build());
//        userMapper.insert(User.builder().name("xiaohong5").age(25).build());
        userMapper.insert(User.builder().name("xiaohong6").age(26).build());
    }

    @Test
    public void updateTest() {
        userMapper.updateById(User.builder().id(1731552348470849539L).name("hhhhh").version(1).build());
        userMapper.updateById(User.builder().id(1731552348470849539L).name("hhhhh").version(1).build());
    }

    @Test
    public void deleteTest() {
        // delete
        userMapper.deleteById(User.builder().id(1731552348286300161L).build());
    }
}
