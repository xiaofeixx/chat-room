package com.xiaofei.entity.pojo;

import com.xiaofei.entity.TbChatRecord;

/**
 * @author xiaofei
 * @Classname Message
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
public class Message {


    private Integer type;      // 消息类型

    private TbChatRecord chatRecord;  // 聊天记录

    private Object extension;         // 扩展消息

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public TbChatRecord getChatRecord() {
        return chatRecord;
    }

    public void setChatRecord(TbChatRecord chatRecord) {
        this.chatRecord = chatRecord;
    }

    public Object getExtension() {
        return extension;
    }

    public void setExtension(Object extension) {
        this.extension = extension;
    }
}
