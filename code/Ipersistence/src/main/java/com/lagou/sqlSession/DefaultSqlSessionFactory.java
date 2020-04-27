package com.lagou.sqlSession;

import com.lagou.pojo.Configeration;

/**
 * @Author zhangqing
 * @Date 2020/4/22 22:39
 * @desc
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private  Configeration configeration;

    public DefaultSqlSessionFactory(Configeration configeration) {
        this.configeration=configeration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configeration);
    }
}
