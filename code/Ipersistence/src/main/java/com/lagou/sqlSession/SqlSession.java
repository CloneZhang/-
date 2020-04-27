package com.lagou.sqlSession;

import com.lagou.pojo.MapperStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author zhangqing
 * @Date 2020/4/22 22:43
 * @desc
 **/
public interface SqlSession {

    public <E> List<E> selectList(String statementId,Object... params) throws Exception;

    public <T> T selectOne(String statementId,Object... params) throws Exception;

    public Boolean insert(String statementId,Object... params) throws Exception;
    //为Dao接口生成代理实现类
    public <T> T getMapper(Class<?> mapperClass);

    Boolean delete(String statementId,Object... params) throws Exception;

    Boolean update(String statementId,Object... params) throws Exception;
}
