package com.xiaofei.entity.pojo;

/**
 * @author xiaofei
 * @Classname Result
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 * 封装返回给客户端的数据
 */
public class Result {

    private boolean success;

    private String message;

    private Object result;

    public Result(boolean success, String message, Object result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public Result(boolean success, Object result) {
        this.success = success;
        this.result = result;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
