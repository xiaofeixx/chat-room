package com.xiaofei.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;
/**
 * @author xiaofei
 * @Classname HearBeanHandler
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 * 初始化
 */
public class WebsocketInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new IdleStateHandler(4, 8, 12, TimeUnit.SECONDS));
        pipeline.addLast(new HearBeanHandler());
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 添加自定义的handler
        pipeline.addLast(new ChatHandler());

    }
}
