package com.lagou.sqlSession;

import com.lagou.config.XMLConfigBuilder;
import com.lagou.pojo.Configeration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @Author zhangqing
 * @Date 2020/4/22 20:35
 * @desc
 **/
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws DocumentException, PropertyVetoException {

        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configeration configeration = xmlConfigBuilder.parseConfig(inputStream);
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configeration);

        return defaultSqlSessionFactory;
    }
}
