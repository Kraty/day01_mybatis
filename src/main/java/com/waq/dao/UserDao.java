package com.waq.dao;

import com.waq.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {

    // 查询所有操作
    @Select("select * from user")
    List<User> findAll();

    // 增加用户
    @Select("insert into user (username, address, sex, birthday) values " +
            "(#{username}, #{address}, #{sex}, #{birthday})")
    void saveUser(User user);

    // 删除用户
    @Select("delete from user where id=#{id}")
    void deleteUser(Integer id);

    // 修改用户
    @Select("update user set username=#{username}, address=#{address}, " +
            "sex=#{sex}, birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

}
