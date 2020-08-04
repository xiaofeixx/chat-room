package com.xiaofei.controller;

import com.xiaofei.entity.TbChatRecord;
import com.xiaofei.entity.pojo.Result;
import com.xiaofei.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaofei
 * @Classname ChatRecordController
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@RestController
@RequestMapping("/charrecord")
public class ChatRecordController {

    @Autowired
    private ChatRecordService chatRecordService;

    /***
     * * @param userId
     * @param friendId
     *@return {@link List<TbChatRecord>}
     *@throws
     *@description 查找聊天记录
     */
    @GetMapping("/findRecord")
    public Result findByUserIdAndFriendId(Long userId, Long friendId, Integer size) {

        try {
            List<TbChatRecord> records = chatRecordService.findByUserIdAndFriendId(userId, friendId, size);
            return new Result(true, "查询成功", records);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"出现异常");
        }
    }

    /***
     * * @param
     *@return {@link List< TbChatRecord>}
     *@throws
     *@description 查询未读记录
     */
    @GetMapping("/findNotReadRecord")
    public Result findNotReadRecord(Long userId, Long friendId) {
        List<TbChatRecord> records = chatRecordService.findNotReadRecord(userId, friendId);
        return new Result(true, "查询未读消息成功", records);
    }
}
