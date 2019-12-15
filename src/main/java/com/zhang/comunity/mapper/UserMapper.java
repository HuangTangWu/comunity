package com.zhang.comunity.mapper;

import com.zhang.comunity.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/15 10:48
 */
@Mapper
public interface UserMapper {
     List<User> getUsers();

     int addUser(User user);
}
