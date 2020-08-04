package com.xiaofei.service;

import com.xiaofei.entity.TbChatRecord;

import java.util.List;

/**
 * @author xiaofei
 * @Classname ChatRecordService
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
public interface ChatRecordService {
    // 保存聊天记录
    void insert(TbChatRecord chatRecord);

    // 查询聊天记录
    List<TbChatRecord> findByUserIdAndFriendId(Long userId, Long friendId, Integer size);

    List<TbChatRecord> findNotReadRecord(Long userId, Long friendId);

}
