package cn.edu.swpu.info.college_website.domain;

import cn.edu.swpu.info.college_website.domain.base.BaseDomain;


public class OpsRole extends BaseDomain {
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;

    public OpsRole() {
        //默认无参构造方法
    }

    /**
     * 获取 name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 description
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置 description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}