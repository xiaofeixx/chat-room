# 聊天服务后端

实现技术包括：springboot + mybatis + netty + mysql +fdfs

实现了用户注册，用户好友添加，删除，查询，聊天记录查询，以及用户聊天等功能。

## netty消息封装

```javascript
message: {
            type: type,
            chatRecord: {
                id: msgid,
                userid: userid,     // 用户id
                friendid: friendid, // 好友id
                message: msg,       // 聊天信息
            },
            ext: ext                // 扩展消息
}
```

### type

- 0：发送消息为连接消息
- 1：消息为聊天服务
- 2：心跳检测

### 心跳检测

后台心跳检测读写为12，所以前端发送心跳时间必须小于12(也可根据自己需求更改)，如下：

```javascript
           setInterval(() => {
                const message = getMessage(2, null, null, null, null, null);
                websocket.send(JSON.stringify(message))
            }, 10000)
```

对应接口功能请看代码注释