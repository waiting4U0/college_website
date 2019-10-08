package cn.edu.swpu.info.college_website.domain.common;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class MessageTips implements Serializable {
    public static final int SYSTEM_ERROR = -1;
    public static final int ILLEGAL_ARGUMENT = -2;
    private int code = 200;
    private Object data;
    private String errorMsg = "success";

    public MessageTips() {
    }

    public MessageTips(int code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public static MessageTips create(int code, String result) {
        return new MessageTips(code, result);
    }

    public static MessageTips success() {
        return success("success");
    }

    public static MessageTips success(String msg) {
        return create(200, msg);
    }

    public static MessageTips systemError() {
        return create(-1, "系统异常");
    }

    public static MessageTips illegalArgument() {
        return create(-2, "参数错误");
    }

    public static MessageTips illegalArgument(String message) {
        return create(-2, "参数错误：" + message);
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
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

    public void setData(Object data) {
        this.data = JSON.toJSON(data);
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
