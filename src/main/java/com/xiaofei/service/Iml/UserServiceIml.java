package com.xiaofei.service.Iml;

import com.xiaofei.dao.UserMapper;
import com.xiaofei.entity.TbFriend;
import com.xiaofei.entity.TbFriendReq;
import com.xiaofei.entity.TbUser;
import com.xiaofei.entity.pojo.User;
import com.xiaofei.service.UserService;
import com.xiaofei.util.FdfsClient;
import com.xiaofei.util.QRCodeUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author xiaofei
 * @Classname UserServiceIml
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@Service
public class UserServiceIml implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QRCodeUtils qrCodeUtils;
    @Autowired
    private Environment env;
    @Autowired
    private FdfsClient fdfsClient;

    @Override
    public User login(String username, String password) {
        if ("".equals(username) || "".equals(password)) {
            return null;
        }
        return userMapper.login(username, DigestUtils.md5Hex(password));
    }

    @Override
    public int register(TbUser tbUser) throws IOException {
        if (tbUser == null) {
            return 0;
        } else {
            User user = userMapper.userExist(tbUser.getUsername());
            if (user == null) {

                String qrcodeStr = tbUser.getUsername();
                String tmpDir = env.getProperty("hchat");
                String qrcodeFilePath = tmpDir + qrcodeStr + ".png";
                qrCodeUtils.createQRCode(qrcodeFilePath, qrcodeStr);
                // 上传二维码地址
                String uploadFile = fdfsClient.uploadFile(new File(qrcodeFilePath));
                tbUser.setQrcode(uploadFile);
                tbUser.setCreatetime(new Date());
                tbUser.setPassword(DigestUtils.md5Hex(tbUser.getPassword()));
                return userMapper.insertUser(tbUser);
            }
            return -1;
        }
    }

    @Override
    public int updateUserInfo(TbUser tbUser, MultipartFile avatar) throws IOException {

        if (tbUser != null && avatar != null) {
            String uploadFile = fdfsClient.uploadFile(avatar);
            tbUser.setPicNormal(uploadFile);
            String smallAvatar = fdfsClient.upliadAndTumb(avatar);
            tbUser.setPicSmall(smallAvatar);
            return userMapper.updateUserInfo(tbUser);
        }
        return 0;
    }

    @Override
    public List<User> searchUser(String username) {
        return userMapper.searchUser(username);
    }

    @Override
    public boolean friendRequest(Long userId, Long friendId, String message) {

        if (userId.equals(friendId)) {
            return false;
        }
        TbFriend friend = userMapper.findFriend(userId);
        if (friend != null) {
            return false;
        }
        TbFriendReq tbFriendReq = userMapper.tbFriendReqExsit(userId, friendId);
        if (tbFriendReq != null && tbFriendReq.getStatus() == 0) {
            return true;
        }
        int req = userMapper.createFriendReq(userId, friendId, message);
        return req > 0;
    }

    // 查找自己的请求或者和自己相关的
    @Override
    public List<TbFriendReq> findFriendReq(Long userId) {
        return userMapper.findFriendReq(userId);
    }

    @Override
    public boolean agreeFriendReq(Long userId, Long friendId) {
        boolean success = false;
        int flag = userMapper.agreeFriendReq(userId, friendId);
        if (flag > 0) {
            TbFriend friend = new TbFriend();
            friend.setUserid(userId);
            friend.setFriendsId(friendId);
            friend.setComments("我们已经是好友了，快来一起来聊天吧！");
            int i = userMapper.addFriendList(friend);
            if (i == 0) {
                return false;
            }
            TbFriend friend1 = new TbFriend();
            friend1.setUserid(friendId);
            friend1.setFriendsId(userId);
            friend1.setComments("我们已经是好友了，快来一起来聊天吧！");
            i = userMapper.addFriendList(friend1);
            if (i > 0) {
                success = true;
            }
        }
        return success;
    }

    @Override
    public boolean rejectFriendReq(Long userId, Long friendId) {
        TbFriendReq tbFriendReq = userMapper.tbFriendReqExsit(userId, friendId);
        if (tbFriendReq != null) {
            int flag = userMapper.rejectFriendReq(userId, friendId);
            return flag > 0;
        }
        return false;
    }

    @Override
    public List<User> findSelfFriend(Long userid) {
        return userMapper.findSelfFriend(userid);
    }

    @Override
    public boolean removeFriend(Long userId, Long friendId) {
        int flag = userMapper.removeFriend(userId, friendId);
        return flag > 0;
    }
}
