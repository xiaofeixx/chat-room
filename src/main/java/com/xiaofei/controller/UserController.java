package com.xiaofei.controller;

import com.xiaofei.entity.TbFriendReq;
import com.xiaofei.entity.TbUser;
import com.xiaofei.entity.pojo.Result;
import com.xiaofei.entity.pojo.User;
import com.xiaofei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author xiaofei
 * @Classname UserController
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param tbUser
     * @return
     */
    @PostMapping("/login")
    public Result login (@RequestBody TbUser tbUser) {

        User login = userService.login(tbUser.getUsername(), tbUser.getPassword());
        if (login == null) {
            return new Result(false,"用户名或密码错误！！！");
        }
        return new Result(true, login);
    }

    /***
     * * @param tbUser
     *@return {@link Result}
     *@throws
     *@description 注册功能
     */
    @PostMapping("/register")
    public Result register(@RequestBody TbUser tbUser) throws IOException {

       int flag =  userService.register(tbUser);
        switch (flag) {
            case 0:
                return new Result(false,"数据出现异常！！！");

            case -1:
                return new Result(false, "用户已经存在");

            default:
                return new Result(true, "注册成功");
        }
    }

    /***
     * * @param tbUser
     * @param avatar
     *@return {@link Result}
     *@throws
     *@description 更新用户信息
     */
    @PostMapping("/update")
    public Result updateUserInfo(@RequestBody TbUser tbUser, MultipartFile avatar) throws IOException {

        int flag  = userService.updateUserInfo(tbUser,avatar);

        if (flag == 0) {
            return new Result(false, "更新失败");
        }
        return new Result(true, "更新成功");
    }

    @RequestMapping("/test")
    public Result test(){
        return new Result(true,"测试成功");
    }

    /***
     * * @param username
     *@return {@link Result}
     *@throws
     *@description 查找用户
     */
    @GetMapping("/searchUser")
    public Result searchUser(String username) {
       List<User> users = userService.searchUser(username);
       return new Result(true,"查找成功",users);
    }

    @GetMapping("/friendRequest")
    public Result friendRequest(Long userId, Long friendId, String message) {
        boolean flag = userService.friendRequest(userId, friendId, message);
        if (flag) {
            return new Result(true,"请求好友成功");
        } else {
            return new Result(false, "请求好友失败,不能重复添加或者添加自己为好友");
        }
    }

    /***
     * * @param userId
     *@return {@link Result}
     *@throws
     *@description 查找请求记录
     */
    @GetMapping("/findFriendReq")
    public Result findFriendReq (Long userId) {
        List<TbFriendReq> reqs = userService.findFriendReq(userId);
        return new Result(true,"请求成功", reqs);
    }

    /***
     * * @param userId
     * @param friendId
     *@return {@link Result}
     *@throws
     *@description 同意好友请求
     */
    @GetMapping("agreeFriendReq")
    public Result agreeFriendReq(Long userId, Long friendId){

       boolean flag = userService.agreeFriendReq(userId, friendId);
       if (flag) {
           return new Result(true, "添加成功");
       }
       return new Result(false, "添加出现异常，请重试");
    }

    /***
     * * @param userId
     * @param friendId
     *@return {@link Result}
     *@throws
     *@description 拒绝好友请求
     */
    @GetMapping("/rejectFriendReq")
    public Result rejectFriendReq(Long userId, Long friendId){
       boolean flag = userService.rejectFriendReq(userId, friendId);
        if (flag) {
            return new Result(true, "已拒绝");
        }
        return new Result(false, "拒绝出现异常，请重试");
    }

    /***
     * * @param userid
     *@return {@link Result}
     *@throws
     *@description 查找已添加好友
     */
    @GetMapping("/findSelfFriend")
    public Result findSelfFriend(Long userid) {
      List<User> list = userService.findSelfFriend(userid);
      return new Result(true, "查找成功", list);
    }

    /***
     * * @param friendId
     * @param userId
     *@return {@link Result}
     *@throws
     *@description 删除好友
     */
    @GetMapping("/removeFriend")
    public Result removeFriend(Long friendId, Long userId) {
       boolean flag = userService.removeFriend(userId,friendId);
       if (flag) {
           return new Result(true, "删除成功");
       } else {
           return new Result(false, "删除失败");
       }
    }
}
