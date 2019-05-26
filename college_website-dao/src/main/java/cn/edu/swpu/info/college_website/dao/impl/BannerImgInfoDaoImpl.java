package cn.edu.swpu.info.college_website.dao.impl;

import cn.edu.swpu.info.college_website.dao.BannerImgInfoDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.BannerImgInfo;
import org.springframework.stereotype.Repository;

@Repository("BannerImgInfoDao")
public class BannerImgInfoDaoImpl extends BaseDaoImpl<BannerImgInfo, Long> implements BannerImgInfoDao {
    private final static String NAMESPACE = "cn.edu.swpu.info.college_website.dao.BannerImgInfoDao.";

    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

//不用
//    public List<String> selectProjectUuidList() {
//        return this.selectList(getNameSpace("selectProjectUuidList"),null);
//    }
}
