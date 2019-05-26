package cn.edu.swpu.info.college_website.domain;

import cn.edu.swpu.info.college_website.domain.base.BaseDomain;

import java.util.Date;


public class OpsFunction extends BaseDomain {
    private static final long serialVersionUID = 1L;
    private String name;
    private String url;
    private String resourceKey;
    private String icon;
    private Long parentId;


    public OpsFunction() {
        //默认无参构造方法
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
     * 获取 url
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置 url
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取 resourceKey
     *
     * @return
     */
    public String getResourceKey() {
        return resourceKey;
    }

    /**
     * 设置 resourceKey
     *
     * @param resourceKey
     */
    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    /**
     * 获取 parentId
     *
     * @return
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置 parentId
     *
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}