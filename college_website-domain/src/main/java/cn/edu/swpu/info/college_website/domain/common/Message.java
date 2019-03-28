package cn.edu.swpu.info.college_website.domain.common;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class Message implements Serializable {
    private int code = 200;
    private Object data;
    private String errorMsg = "success";
    public static final int SYSTEM_ERROR = -1;
    public static final int ILLEGAL_ARGUMENT = -2;

    public Message() {
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Message(int code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public void setData(Object data) {
        this.data = JSON.toJSON(data);
    }

    public static Message create(int code, String result) {
        return new Message(code, result);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return this.data;
    }

    public static Message success() {
        return success("success");
    }

    public static Message success(String msg) {
        return create(200, msg);
    }

    public static Message systemError() {
        return create(-1, "系统异常");
    }

    public static Message illegalArgument() {
        return create(-2, "参数错误");
    }

    public static Message illegalArgument(String message) {
        return create(-2, "参数错误：" + message);
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
