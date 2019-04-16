package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.dao.BannerImgInfoDao;
import cn.edu.swpu.info.college_website.domain.BannerImgInfo;
import cn.edu.swpu.info.college_website.service.BannerImgInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//import java.util.List;
@Service
public class BannerImgInfoServiceImpl implements BannerImgInfoService {

    @Resource
    private BannerImgInfoDao bannerImgInfoDao;



//    @Override
//    public List<BannerImgInfo> getBannerImgInfoList(BannerImgInfo bannerImgInfo) {
//        List<BannerImgInfo> bannerImgInfos = bannerImgInfoDao.selectEntryList(new BannerImgInfo());
//        List<BannerImgInfo> bannerImgInfoList = new ArrayList<BannerImgInfo>();
//        for (BannerImgInfo bannerImgInfo1:bannerImgInfos)
//            if(bannerImgInfo1.getId() !=null){
//                bannerImgInfoList.add(bannerImgInfo1);
//            }
//        return bannerImgInfoList;
//    }

    @Override
    public List<BannerImgInfo> selectEntryList(BannerImgInfo bannerImgInfo) {
        return bannerImgInfoDao.selectEntryList(bannerImgInfo);
    }


}
