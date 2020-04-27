package com.lagou.io;

import com.lagou.dao.IUserDao;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @Author zhangqing
 * @Date 2020/4/21 21:56
 * @desc
 **/
public class IpersisitenceTest {

    @Test
    public void test() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);


//新增
//        User user = new User();
//        user.setUsername("thanks");
//        user.setBirthday("2020-01-02");
//        user.setPassword("111111");
//        Boolean insert = sqlSession.insert("com.lagou.dao.IUserDao.insertUser", user);
//        System.out.println(insert);

//代理实现新增
//        User user = new User();
//        user.setUsername("thanks");
//        user.setBirthday("2020-01-02");
//        user.setPassword("111111");
//        Boolean insert = userDao.insertUser(user);
//        System.out.println(insert);

//删除
//        User user = new User();
//        user.setId(4);
//        Boolean delete = sqlSession.delete("com.lagou.dao.IUserDao.deleteUser", user);
//        System.out.println(delete);

//代理实现删除
//        User user = new User();
//        user.setId(5);
//        Boolean delete = userDao.deleteUser(user);
//        System.out.println(delete);

//修改
//        User user = new User();
//        user.setId(1);
//        user.setPassword("password");
//        user.setUsername("张三");
//        user.setBirthday("2020-03-03");
//        Boolean update = sqlSession.update("com.lagou.dao.IUserDao.updateUser", user);
//        System.out.println(update);

//代理实现修改
//        User user = new User();
//        user.setId(1);
//        user.setPassword("password");
//        user.setUsername("张三");
//        user.setBirthday("2020-03-03");
//        Boolean update = userDao.updateUser(user);
//        System.out.println(update);
        //查询所有
//        List<User> users = sqlSession.selectList("user.selectList");
//        for (User user1 : users) {
//            System.out.println(user1);
//        }

        //查找所有代理
//        List<User> all = userDao.findAll();
//        for (User user1 : all) {
//            System.out.println(user1);
//        }

//根据条件查找
//        User byCondition = userDao.findByCondition(user);
//        System.out.println(byCondition);
    }
}
