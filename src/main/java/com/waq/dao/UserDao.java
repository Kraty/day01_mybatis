package com.waq.dao;

import com.waq.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {

    // 查询所有操作
    @Select("select * from user")
    List<User> findAll();

    // 增加用户
    @Insert("insert into user (username, address, sex, birthday) values " +
            "(#{username}, #{address}, #{sex}, #{birthday})")
    void saveUser(User user);

    // 删除用户
    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer id);

    // 修改用户
    @Update("update user set username=#{username}, address=#{address}, " +
            "sex=#{sex}, birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    // 查询一个
    @Select("select * from user where id=#{id}")
    User findById(Integer id);

    // 模糊查询
    @Select("select * from user where username like #{name}")
    List<User> findByName(String username);

}
