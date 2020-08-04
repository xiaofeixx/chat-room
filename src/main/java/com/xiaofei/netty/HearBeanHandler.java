package com.xiaofei.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaofei
 * @Classname HearBeanHandler
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
public class HearBeanHandler extends ChannelInboundHandlerAdapter {

    private final static Logger log = LoggerFactory.getLogger(HearBeanHandler.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.READER_IDLE) {

                log.warn("读空闲");
            } else if (idleStateEvent.state() == IdleState.WRITER_IDLE) {

                log.warn("写空闲");
            } else if (idleStateEvent.state() == IdleState.ALL_IDLE) {
                log.warn("读写空闲触发，关闭通道!!!");
                ctx.channel().close();
            }
        }
    }
}
