package cn.edu.swpu.info.college_website.service;

import cn.edu.swpu.info.college_website.domain.BannerImgInfo;

import java.util.List;

public interface BannerImgInfoService {

    List<BannerImgInfo> getBannerImgInfoList(BannerImgInfo bannerImgInfo);


    List<BannerImgInfo> selectEntryList(BannerImgInfo bannerImgInfo);
}
