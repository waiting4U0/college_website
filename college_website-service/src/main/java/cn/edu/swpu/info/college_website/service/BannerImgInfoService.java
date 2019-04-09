package cn.edu.swpu.info.college_website.service;

import cn.edu.swpu.info.college_website.domain.BannerImgInfo;

public interface BannerImgInfoService {
    //Page<BannerImgInfo> selectPage(BannerImgInfo condition, Page<BannerImgInfo> page);

    long createBannerImgInfo(BannerImgInfo bannerImgInfo);

    long updateBannerImgInfo(BannerImgInfo bannerImgInfo);

    BannerImgInfo getBannerImgInfoById(Long id);
    //BannerImgInfo getBannerImgInfoByImgname(String bannerImgInfo);
    int remove(BannerImgInfo condition);
}
