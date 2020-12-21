package com.waq.test;

import com.waq.dao.AccountDao;
import com.waq.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AccountTest {

    private InputStream in;
    private SqlSession sqlSession;
    private AccountDao accountDao;

    // 查询所有账户
    @Test
    public void testFindAll() {

        // 5.使用代理对象执行方法
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
            System.out.println(account.getUser());
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
        accountDao = sqlSession.getMapper(AccountDao.class);

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
