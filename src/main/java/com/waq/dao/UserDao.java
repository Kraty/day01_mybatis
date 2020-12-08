package com.waq.dao;

import com.waq.domain.QueryVo;
import com.waq.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {


    
    // 查询所有操作
    @Select("select * from user")
    @Results(id = "resultMap", value = {
            @Result(property = "username", column = "username")
    })
    List<User> findAll();

    // 增加用户
    @Insert("insert into user (username, address, sex, birthday) values " +
            "(#{username}, #{address}, #{sex}, #{birthday})")
    @SelectKey(statement = {"select last_insert_id()"}, keyProperty = "id", before = false, resultType = int.class)
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
    @ResultMap("resultMap")
    List<User> findByName(String username);

    // 获取总记录数
    @Select("select count(id) from user")
    int findTotal();

    // 包装类对象作为查询条件
    @Select("select * from user where username like #{user.username}")
    List<User> findByVo(QueryVo vo);

}
