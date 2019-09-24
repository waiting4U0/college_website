package cn.edu.swpu.info.college_website.domain.common;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class State implements Serializable {
    public static final int SYSTEM_ERROR = -1;
    public static final int ILLEGAL_ARGUMENT = -2;
    private int code = 200;
    private Object data;
    private String errorMsg = "success";

    public State() {
    }

    public State(int code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public static State create(int code, String result) {
        return new State(code, result);
    }

    public static State success() {
        return success("success");
    }

    public static State success(String msg) {
        return create(200, msg);
    }

    public static State systemError() {
        return create(-1, "系统异常");
    }

    public static State illegalArgument() {
        return create(-2, "参数错误");
    }

    public static State illegalArgument(String message) {
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
