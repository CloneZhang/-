package com.lagou.sqlSession;

import com.lagou.config.BoundSql;
import com.lagou.pojo.Configeration;
import com.lagou.pojo.MapperStatement;
import com.lagou.utils.GenericTokenParser;
import com.lagou.utils.ParameterMapping;
import com.lagou.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangqing
 * @Date 2020/4/22 23:11
 * @desc
 **/
public class simpleExcutor implements Executor{

    @Override
    public <E> List<E> query(Configeration configeration, MapperStatement mapperStatement, Object... params) throws Exception {
        //获取连接
        Connection connection = configeration.getDataSource().getConnection();
        //获取sql
        String sql = mapperStatement.getSql();
        BoundSql boundSql =  getBoundSql(sql);

        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        String paramterType = mapperStatement.getParamterType();
        Class<?> paramtertypeClass = getClassType(paramterType);

        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {

            String content = parameterMappingList.get(i).getContent();

            //反射
            Field declaredField = paramtertypeClass.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);

            preparedStatement.setObject(i+1,o);
        }
        // 5. 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mapperStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);

        ArrayList<Object> objects = new ArrayList<>();
// 6. 封装返回结果集
        while (resultSet.next()){
            Object o =resultTypeClass.newInstance();
            //元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {

                // 字段名
                String columnName = metaData.getColumnName(i);
                // 字段的值
                Object value = resultSet.getObject(columnName);

                //使用反射或者内省，根据数据库表和实体的对应关系，完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o,value);
            }
            objects.add(o);

        }
        return (List<E>) objects;
    }

    @Override
    public Boolean insert(Configeration configeration, MapperStatement mapperStatement, Object... params) throws SQLException, Exception {
        //获取连接
        Connection connection = configeration.getDataSource().getConnection();
        //获取sql
        String sql = mapperStatement.getSql();
        BoundSql boundSql =  getBoundSql(sql);

        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        String paramterType = mapperStatement.getParamterType();
        Class<?> paramtertypeClass = getClassType(paramterType);

        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {

            String content = parameterMappingList.get(i).getContent();

            //反射
            Field declaredField = paramtertypeClass.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);

            preparedStatement.setObject(i+1,o);
        }
        // 5. 执行sql
        return preparedStatement.execute();
    }

    @Override
    public Boolean delete(Configeration configeration, MapperStatement mapperStatement, Object[] params) throws Exception {
        //获取连接
        Connection connection = configeration.getDataSource().getConnection();
        //获取sql
        String sql = mapperStatement.getSql();
        BoundSql boundSql =  getBoundSql(sql);

        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        String paramterType = mapperStatement.getParamterType();
        Class<?> paramtertypeClass = getClassType(paramterType);

        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {

            String content = parameterMappingList.get(i).getContent();

            //反射
            Field declaredField = paramtertypeClass.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);

            preparedStatement.setObject(i+1,o);
        }
        // 5. 执行sql
        return preparedStatement.execute();
    }

    @Override
    public Boolean update(Configeration configeration, MapperStatement mapperStatement, Object[] params) throws Exception{
        //获取连接
        Connection connection = configeration.getDataSource().getConnection();
        //获取sql
        String sql = mapperStatement.getSql();
        BoundSql boundSql =  getBoundSql(sql);

        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        String paramterType = mapperStatement.getParamterType();
        Class<?> paramtertypeClass = getClassType(paramterType);

        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {

            String content = parameterMappingList.get(i).getContent();

            //反射
            Field declaredField = paramtertypeClass.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);

            preparedStatement.setObject(i+1,o);
        }
        // 5. 执行sql
        return preparedStatement.execute();
    }

    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if(paramterType!=null){
            Class<?> aClass = Class.forName(paramterType);
            return aClass;
        }
        return null;
    }

    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{","}",parameterMappingTokenHandler);
        String parse = genericTokenParser.parse(sql);
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parse,parameterMappings);
        return boundSql;
    }
}
