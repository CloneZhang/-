package com.lagou.sqlSession;


/**
 * @Author zhangqing
 * @Date 2020/4/22 20:40
 * @desc
 **/
public interface SqlSessionFactory {

    public SqlSession openSession();
}
