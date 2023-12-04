package com.springboot.all.mybatisplus.test;

import com.springboot.all.mybatisplus.MyBatisPlusApp;
import com.springboot.all.mybatisplus.mapper.UserMapper;
import com.springboot.all.mybatisplus.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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
    public void insertTest() {
        // insert
        userMapper.insert(User.builder().name("xiaohong1").age(21).build());
        userMapper.insert(User.builder().name("xiaohong2").age(22).build());
        userMapper.insert(User.builder().name("xiaohong3").age(23).build());
        userMapper.insert(User.builder().name("xiaohong4").age(24).build());
        userMapper.insert(User.builder().name("xiaohong5").age(25).build());
    }

    @Test
    public void updateTest() {
        // update
        userMapper.updateById(User.builder().id(1731552348286300161L).name("xiaohong1-update").build());
    }

    @Test
    public void deleteTest() {
        // delete
        userMapper.deleteById(User.builder().id(1731552348286300161L).build());
    }
}
