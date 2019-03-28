package cn.edu.swpu.info.college_website.domain;

import cn.edu.swpu.info.college_website.domain.base.BaseDomain;

/**
 * Created by wangkun on 2019/3/16.
 */
public class UserInfo extends BaseDomain{
    private static final long serialVersionUID = 1L;

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
