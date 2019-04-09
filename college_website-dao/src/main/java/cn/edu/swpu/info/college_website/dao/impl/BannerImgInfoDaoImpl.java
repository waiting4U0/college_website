package cn.edu.swpu.info.college_website.dao.impl;

import cn.edu.swpu.info.college_website.dao.BannerImgInfoDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.BannerImgInfo;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BannerImgInfoDaoImpl extends BaseDaoImpl<BannerImgInfo,Long> implements BannerImgInfoDao {
    private final static String NAMESPACE = "cn.edu.swpu.info.college_website.dao.BannerImgInfoDao";
    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }
    public List<String> selectProjectUuidList() {
        return this.selectList(getNameSpace("selectProjectUuidList"),null);
    }
}
