package com.lagou.sqlSession;

import com.lagou.pojo.Configeration;
import com.lagou.pojo.MapperStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author zhangqing
 * @Date 2020/4/22 23:09
 * @desc
 **/
public interface Executor {

    public <E> List<E> query(Configeration configeration, MapperStatement mapperStatement,Object... params) throws  Exception;

    public Boolean insert(Configeration configeration, MapperStatement mapperStatement,Object... params) throws  Exception;

    public Boolean delete(Configeration configeration, MapperStatement mapperStatement, Object[] params) throws  Exception;

    public Boolean update(Configeration configeration, MapperStatement mapperStatement, Object[] params) throws Exception;
}
