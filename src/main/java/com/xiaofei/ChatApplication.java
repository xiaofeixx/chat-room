package com.xiaofei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiaofei
 * @Classname Application
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.xiaofei.dao"})
public class ChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class);
    }
}
