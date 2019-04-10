package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.dao.BannerImgInfoDao;
import cn.edu.swpu.info.college_website.domain.BannerImgInfo;
import cn.edu.swpu.info.college_website.service.BannerImgInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service("bannerImgInfoService")
public class BannerImgInfoServiceImpl implements BannerImgInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    private  BannerImgInfoDao  bannerImgInfoDao;

    public long createBannerImgInfo(BannerImgInfo bannerImgInfo){ return 0; }
    public long updateBannerImgInfo(BannerImgInfo bannerImgInfo){ return 0;}
    public BannerImgInfo getBannerImgInfoById(Long id){ return null;}
    public int remove(BannerImgInfo condition){ return 0; }
}
