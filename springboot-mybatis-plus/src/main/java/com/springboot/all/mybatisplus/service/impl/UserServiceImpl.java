package com.springboot.all.mybatisplus.service.impl;

import com.springboot.all.mybatisplus.entity.User;
import com.springboot.all.mybatisplus.mapper.UserMapper;
import com.springboot.all.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pkq
 * @since 2023-12-14 05:38:58
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
