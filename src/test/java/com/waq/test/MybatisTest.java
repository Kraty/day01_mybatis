package com.waq.test;

import com.waq.dao.UserDao;
import com.waq.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author waq
 * mybatis的入门案例
 */

public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;

    // 查询所有用户
    @Test
    public void testFindAll() {

        // 5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }


    }

    // 添加新用户
    @Test
    public void testSaveUser() {

        User user = new User();
        user.setUsername("小花");
        user.setAddress("北京顺兴区");
        user.setSex("男");
        user.setBirthday(new Date());
        userDao.saveUser(user);

    }

    // 删除用户
    @Test
    public void testDeleteUser() {

        userDao.deleteUser(49);

    }

    // 修改用户
    @Test
    public void testUpdateUser() {

        User user = new User();
        user.setId(50);
        user.setUsername("小花花");
        user.setAddress("北京顺兴区");
        user.setSex("女");
        user.setBirthday(new Date());
        userDao.updateUser(user);

    }

    // 查询一个用户
    @Test
    public void testFindById() {

        User user = userDao.findById(50);
        System.out.println(user);

    }

    // 模糊查询
    @Test
    public void testFindByName() {

        List<User> users = userDao.findByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Before
    public void init() throws Exception {

        // 1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.创建SQL session factory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3.使用工厂生产一个SQL session对象
        sqlSession = factory.openSession();
        // 4.使用SQL session创建dao接口的代理对象
        userDao = sqlSession.getMapper(UserDao.class);

    }

    @After
    public void destroy() throws IOException {

        // 事务提交
        sqlSession.commit();

        // 释放资源
        sqlSession.close();
        in.close();

    }

}
