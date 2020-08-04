package com.xiaofei.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author xiaofei
 * @Classname HearBeanHandler
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 * 监听spring容器，当容器启动时启动netty
 */
@Component
public class NettyListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private WebSocketServer websocketServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null) {
            try {
                websocketServer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
