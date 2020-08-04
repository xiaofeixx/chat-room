package com.xiaofei.dao;

import com.xiaofei.entity.TbFriend;
import com.xiaofei.entity.TbFriendReq;
import com.xiaofei.entity.TbUser;
import com.xiaofei.entity.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaofei
 * @Classname UserMapper
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@Repository
public interface UserMapper {

    @Select("select * from tb_user where username=#{username} and password = #{password}")
    User login(@Param("username") String username, @Param("password") String password);

    @Select("select * from tb_user where username=#{username}")
    User userExist(@Param("username") String usernmae);

    @Insert("insert into tb_user(id, username, password, pic_small, pic_normal, nickname, qrcode, client_id, sign, phone)" +
            " VALUES(#{id},#{username},#{password},#{picSmall},#{picNormal},#{nickname},#{qrcode},#{clientId},#{sign},#{phone})")
    int insertUser(TbUser tbUser);

    @Update("update tb_user set nickname=#{nickname} and sign=#{sign} and phone=#{phone} and pic_normal=#{picNormal} and pic_small=#{picSmall}" +
            "where id=#{id}")
    int updateUserInfo(TbUser tbUser);

    @Select("select * from tb_user where username=#{username}")
    List<User> searchUser(@Param("username") String username);

    @Select("select * from tb_friend where userid=#{userid}")
    TbFriend findFriend(Long userid);

    @Insert("insert into tb_friend_req(from_userid, to_userid, message,status)" +
            " VALUES(#{userId},#{friendId}, #{message}, 0) ")
    int createFriendReq(@Param("userId") Long userId, @Param("friendId") Long friendId, @Param("message") String message);

    @Select("select * from tb_friend_req where to_userid = #{userId}")
    List<TbFriendReq> findFriendReq(@Param("userId") Long userId);

    @Update("update tb_friend_req set status=1 where from_userid=#{userId} and to_userid=#{friendId}")
    int agreeFriendReq(@Param("userId") Long userId, @Param("friendId") Long friendId);

    @Delete("delete from tb_friend_req where from_userid=#{param1} and to_userid=#{param2}")
    int rejectFriendReq(Long userId, Long friendId);

    @Insert("insert into tb_friend(userid, friends_id, comments) VALUES(#{userid}, #{friendsId}, #{comments}) ")
    int addFriendList(TbFriend friend);

    @Select("select tu.* from tb_friend as tf join tb_user tu on tf.friends_id=tu.id where tf.userid=#{userid}")
    List<User> findSelfFriend(Long userid);

    @Select("select * from tb_friend_req where from_userid=#{userId} and to_userid=#{friendId}")
    TbFriendReq tbFriendReqExsit(@Param("userId") Long userId, @Param("friendId") Long friendId);

    @Delete("delete from tb_friend where userid=#{param1} and friendsid=#{param2}")
    int removeFriend(Long userId, Long friendId);
}
