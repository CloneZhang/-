package com.lagou.dao;

import com.lagou.pojo.User;

import java.util.List;

public interface IUserDao {

    //查询所有用户
    public List<User> findAll() throws Exception;


    //根据条件进行用户查询
    public User findByCondition(User user) throws Exception;

    //根据条件进行用户查询
    public Boolean insertUser(User user) throws Exception;

    public Boolean deleteUser(User user) throws Exception;

    public Boolean updateUser(User user) throws Exception;

}
