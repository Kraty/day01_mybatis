package com.waq.dao;

import com.waq.domain.User;

import java.util.List;

public interface UserDao {

    // 查询所有操作
    List<User> findAll();

}
