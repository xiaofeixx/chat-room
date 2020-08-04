package com.xiaofei.dao;

import com.xiaofei.entity.TbChatRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaofei
 * @Classname CgatRecordMapper
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@Repository
public interface ChatRecordMapper {

    @Insert("insert into tb_chat_record(userid, friendid, has_read, createtime, has_delete, message)" +
            " values(#{userid}, #{friendid}, #{hasRead}, #{createtime}, #{hasDelete}, #{message}) ")
    void insert(TbChatRecord chatRecord);

    @Select("select * from tb_chat_record where userid=#{userId} and friendid=#{friendId}  union" +
            " select * from tb_chat_record where userid=#{friendId} and friendid=#{userId} limit #{size};")
    List<TbChatRecord> findByUserIdAndFriendId(@Param("userId") Long userId, @Param("friendId") Long friendId, @Param("size") Integer size);

    @Select("select * from tb_chat_record userid=#{friendId} and friendid=#{userId} and has_read=0")
    List<TbChatRecord> findNotReadRecord(Long userId, Long friendId);
}
