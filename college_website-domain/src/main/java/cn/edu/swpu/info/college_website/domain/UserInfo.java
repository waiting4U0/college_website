package cn.edu.swpu.info.college_website.domain;

import cn.edu.swpu.info.college_website.domain.base.BaseDomain;


public class UserInfo extends BaseDomain {
    private static final long serialVersionUID = 1L;
    public UserInfo(String name, String password){
        this.name = name;
        this.password = password;

    }
    private String name;
    private String password;

    public UserInfo() {

    }

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
