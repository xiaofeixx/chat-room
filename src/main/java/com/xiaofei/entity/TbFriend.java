package com.xiaofei.entity;

import java.util.Date;

public class TbFriend {
    private Long id;

    private Long userid;

    private Long friendsId;

    private String comments;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(Long friendsId) {
        this.friendsId = friendsId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}