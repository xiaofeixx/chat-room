package com.xiaofei.service;

import com.xiaofei.entity.TbFriendReq;
import com.xiaofei.entity.TbUser;
import com.xiaofei.entity.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author xiaofei
 * @Classname UserService
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
public interface UserService {

    User login(String username, String password);

    int register(TbUser tbUser) throws IOException;

    int updateUserInfo(TbUser tbUser, MultipartFile avatar) throws IOException;

    List<User> searchUser(String username);

    boolean friendRequest(Long userId, Long friendId, String message);

    List<TbFriendReq> findFriendReq(Long userId);

    boolean agreeFriendReq(Long userId, Long friendId);

    boolean rejectFriendReq(Long userId, Long friendId);

    List<User> findSelfFriend(Long userid);

    boolean removeFriend(Long userId, Long friendId);
}
