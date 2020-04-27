package com.lagou.io;

import java.io.InputStream;

/**
 * @Author zhangqing
 * @Date 2020/4/21 21:52
 * @desc
 **/
public class Resources {

    //根据路径，将配置文件加载成字节输入流
    public static InputStream getResourceAsStream(String path){
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}
