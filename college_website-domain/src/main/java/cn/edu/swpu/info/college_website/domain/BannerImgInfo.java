package cn.edu.swpu.info.college_website.domain;

import cn.edu.swpu.info.college_website.domain.base.BaseDomain;

public class BannerImgInfo extends BaseDomain {
    private static final long serialVersionUID = 1L;
    private String bannerNameType;
    private String url;
    private String describeInfo;


    public BannerImgInfo(){

    }


    public String getBannerNameType() {
        return bannerNameType;
    }

    public void setBannerNameType(String bannerNameType) {
        this.bannerNameType = bannerNameType;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getDescribeInfo() {
        return describeInfo;
    }

    public void setDescribeInfo(String describeInfo) {
        this.describeInfo = describeInfo;
    }


}
