package com.xiaofei.entity;

import java.util.Date;

public class TbFriendReq {
    private Long id;

    private Long fromUserid;

    private Long toUserid;

    private Date createtime;

    private String message;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromUserid() {
        return fromUserid;
    }

    public void setFromUserid(Long fromUserid) {
        this.fromUserid = fromUserid;
    }

    public Long getToUserid() {
        return toUserid;
    }

    public void setToUserid(Long toUserid) {
        this.toUserid = toUserid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}