package cn.edu.swpu.info.college_website.domain;


import cn.edu.swpu.info.college_website.domain.base.BaseDomain;

public class MessageType extends BaseDomain {


    private long type;
    private String name;


    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
