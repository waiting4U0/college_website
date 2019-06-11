package cn.edu.swpu.info.college_website.common;

import java.io.Serializable;

/**
 * @description: 运行异常
 * @author:源.Gao
 * @createtime: 2019-06-10 10:03
 **/

public class RException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -601494750574444665L;

    private String msg;
    private int code = 500;

    public RException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public RException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public RException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
