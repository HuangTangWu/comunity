package com.zhang.comunity.service;

import com.zhang.comunity.entity.User;
import com.zhang.comunity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/19 15:29
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void addOrUpdateUser(User user){
        User dbUser=userMapper.getUserByAccount(user.getAccountId());
        if (dbUser==null){
            //添加操作
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modify(user.getGmt_create());
            userMapper.addUser(user);
        }else{
            //跟新操作
            dbUser.setGmt_modify(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setToken(user.getToken());
            dbUser.setName(user.getName());
            userMapper.updateUser(dbUser);
        }
    }

    public List<User> getUsers() {
        return userMapper.getUsers();
    }
}
