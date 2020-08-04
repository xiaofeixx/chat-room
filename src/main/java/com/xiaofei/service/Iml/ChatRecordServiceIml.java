package com.xiaofei.service.Iml;

import com.xiaofei.dao.ChatRecordMapper;
import com.xiaofei.entity.TbChatRecord;
import com.xiaofei.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author xiaofei
 * @Classname ChatRecordServiceIml
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@Service
@Transactional
public class ChatRecordServiceIml implements ChatRecordService {

    @Autowired
    private ChatRecordMapper chatRecordMapper;

    @Override
    public void insert(TbChatRecord chatRecord) {
        chatRecord.setCreatetime(new Date());
        chatRecord.setHasDelete(0);

        chatRecordMapper.insert(chatRecord);
    }

    @Override
    public List<TbChatRecord> findByUserIdAndFriendId(Long userId, Long friendId, Integer size) {
       return chatRecordMapper.findByUserIdAndFriendId(userId, friendId, size);
    }

    @Override
    public List<TbChatRecord> findNotReadRecord(Long userId, Long friendId) {
        return chatRecordMapper.findNotReadRecord(userId, friendId);
    }
}
