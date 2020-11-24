package com.waq.test;

import com.waq.dao.UserDao;
import com.waq.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author waq
 * mybatis的入门案例
 */

public class MybatisTest {

    // 入门案例
    public static void main(String[] args) throws IOException {

        // 1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.创建SQL session factory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 3.使用工厂生产一个SQL session对象
        SqlSession sqlSession = factory.openSession();
        // 4.使用SQL session创建dao接口的代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        // 5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        // 6.释放资源
        sqlSession.close();
        in.close();

    }

}
