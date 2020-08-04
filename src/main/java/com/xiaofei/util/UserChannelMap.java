package com.xiaofei.util;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaofei
 * @Classname UserChannelMap
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 * 建立用户id与channel的关联
 */

public class UserChannelMap {
    // 保存用户id与通道对象
    private static Map<Long, Channel> userChannelMap;

    static {
        userChannelMap = new ConcurrentHashMap<>();
    }

    public static void put(Long userId, Channel channel) {
        userChannelMap.put(userId, channel);
    }

    public static void remove(Long userId) {
        userChannelMap.remove(userId);
    }

    public static  void removeByChannelId(String channelId) {

        if (channelId == null){
            return;
        }
        for (Long key : userChannelMap.keySet()) {
            Channel channel = userChannelMap.get(key);
            if (channelId.equals(channel.id().asLongText())){
                userChannelMap.remove(key);
                break;
            }
        }
    }

    /***
     * * @param friendid
     *@return {@link Channel}
     *@throws
     *@description 根据好友id获取好友通道
     */
    public static Channel get(Long friendid) {
        return userChannelMap.get(friendid);
    }
}
