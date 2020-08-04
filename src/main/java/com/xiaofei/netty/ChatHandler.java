package com.xiaofei.netty;

import com.alibaba.fastjson.JSON;
import com.xiaofei.entity.TbChatRecord;
import com.xiaofei.entity.pojo.Message;
import com.xiaofei.service.ChatRecordService;
import com.xiaofei.util.SpringUtil;
import com.xiaofei.util.UserChannelMap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

/**
 * @author xiaofei
 * @Classname HearBeanHandler
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 * 处理信息的Handler
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final static Logger log = LoggerFactory.getLogger(ChatHandler.class);
    // 保存所有客户端连接
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:MM");


    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        // 收到数据后自动调用,新消息
        String msg = textWebSocketFrame.text();
        log.info("收到消息"+msg);
        Message message = JSON.parseObject(msg, Message.class);

        ChatRecordService recordService = SpringUtil.getBean(ChatRecordService.class);

        switch (message.getType()) {
            case 0:
                // 建立用户与管道关联
                Long userid = message.getChatRecord().getUserid();
                UserChannelMap.put(userid, channelHandlerContext.channel());
                log.info("通道建立成功");
                break;

            case 1:
                // 发送消息聊天
                TbChatRecord chatRecord = message.getChatRecord();
                // 如果在线，发送
                Channel channel = UserChannelMap.get(chatRecord.getFriendid());
                if (channel != null) {
                    chatRecord.setHasRead(1);
                    recordService.insert(chatRecord);
                    channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
                } else {
                    chatRecord.setHasRead(0);
                    recordService.insert(chatRecord);
                    log.info("用户不在线!!!");
                }
                break;
            case 2:
                log.info("收到心跳~~~");
                break;

        }

    }

    // 新用户连接会调用
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        clients.add(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        UserChannelMap.removeByChannelId(ctx.channel().id().asLongText());
        ctx.channel().close();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.warn("客户端连接断开");
        UserChannelMap.removeByChannelId(ctx.channel().id().asLongText());
        ctx.channel().close();
    }
}
