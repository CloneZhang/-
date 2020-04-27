package com.lagou.config;

import com.lagou.io.Resources;
import com.lagou.pojo.Configeration;
import com.lagou.pojo.MapperStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @Author zhangqing
 * @Date 2020/4/22 22:12
 * @desc
 **/
public class XMLMapperBuilder {

    private Configeration configeration;

    public XMLMapperBuilder(Configeration configeration) {
        this.configeration=configeration;
    }

    public Configeration parseMapper(InputStream in) throws DocumentException {

        Document document = new SAXReader().read(in);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");

        List<Element> list = rootElement.selectNodes("//select");
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MapperStatement mappedStatement = new MapperStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            String key = namespace + "." + id;
            configeration.getMapperStatementMap().put(key, mappedStatement);
        }

        List<Element> inserList = rootElement.selectNodes("//insert");
        for (Element element : inserList) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MapperStatement mappedStatement = new MapperStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            String key = namespace + "." + id;
            configeration.getMapperStatementMap().put(key, mappedStatement);
        }

        List<Element> deleteList = rootElement.selectNodes("//delete");
        for (Element element : deleteList) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MapperStatement mappedStatement = new MapperStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            String key = namespace + "." + id;
            configeration.getMapperStatementMap().put(key, mappedStatement);
        }

        List<Element> updateList = rootElement.selectNodes("//update");
        for (Element element : updateList) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MapperStatement mappedStatement = new MapperStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            String key = namespace + "." + id;
            configeration.getMapperStatementMap().put(key, mappedStatement);
        }

        return configeration;
    }
}
