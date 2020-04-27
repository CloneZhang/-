package com.lagou.sqlSession;

import com.lagou.pojo.Configeration;

import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author zhangqing
 * @Date 2020/4/22 22:44
 * @desc
 **/
public class DefaultSqlSession implements SqlSession {

    private  Configeration configeration;
    public DefaultSqlSession(Configeration configeration) {
        this.configeration= configeration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        simpleExcutor simpleExcutor = new simpleExcutor();
        List<E> query = simpleExcutor.query(configeration, configeration.getMapperStatementMap().get(statementId), params);
        return query;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {

        List<T> list = selectList(statementId, params);

        if(list.size()==1){
            return (T)list.get(0);
        }
        else {
            throw new RuntimeException("查询结果为空或者过多");
        }

    }

    @Override
    public Boolean insert(String statementId, Object... params) throws Exception {
        simpleExcutor simpleExcutor = new simpleExcutor();
        return simpleExcutor.insert(configeration, configeration.getMapperStatementMap().get(statementId), params);
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        // 使用JDK动态代理来为Dao接口生成代理对象，并返回

        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 底层都还是去执行JDBC代码 //根据不同情况，来调用selctList或者selectOne
                // 准备参数 1：statmentid :sql语句的唯一标识：namespace.id= 接口全限定名.方法名
                // 方法名：findAll
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();

                String statementId = className+"."+methodName;

                // 准备参数2：params:args
                // 获取被调用方法的返回值类型
                Type genericReturnType = method.getGenericReturnType();
                // 判断是否进行了 泛型类型参数化
                if(genericReturnType instanceof ParameterizedType){
                    List<Object> objects = selectList(statementId, args);
                    return objects;
                }

                switch (methodName){
                    case "insertUser": return insert(statementId,args);
                    case "updateUser": return update(statementId,args);
                    case "deleteUser": return delete(statementId,args);
                    case "findAll": return selectOne(statementId,args);
                    case "findByCondition": return selectOne(statementId,args);
                    default: return null;
                }
            }
        });

        return (T) proxyInstance;
    }

    @Override
    public Boolean delete(String statementId,Object... params) throws Exception {
        simpleExcutor simpleExcutor = new simpleExcutor();
        return simpleExcutor.delete(configeration, configeration.getMapperStatementMap().get(statementId), params);
    }

    @Override
    public Boolean update(String statementId, Object... params) throws Exception {
        simpleExcutor simpleExcutor = new simpleExcutor();
        return simpleExcutor.update(configeration, configeration.getMapperStatementMap().get(statementId), params);
    }
}
