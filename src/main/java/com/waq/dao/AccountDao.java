package com.waq.dao;

import com.waq.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountDao {

    /**
     * 查询所有用户，并获取每个账户所属的用户信息
     * 一对一时一般不要延迟加载
     *
     * @return Account
     */
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            @Result(column = "uid", property = "user",
                    one = @One(select = "com.waq.dao.UserDao.findById", fetchType = FetchType.EAGER))
    })
    List<Account> findAll();

    // 根据用户id查询账户
    @Select("select * from account where uid = #{id}")
    List<Account> findAccountById(Integer id);

}
