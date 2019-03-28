package cn.edu.swpu.info.college_website.dao.impl;

import cn.edu.swpu.info.college_website.dao.UserInfoDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangkun on 2019/3/16.
 */
@Repository
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo,Long> implements UserInfoDao{
    private final static String NAMESPACE = "cn.edu.swpu.info.college_website.dao.UserInfoDao.";
    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

    public List<String> selectProjectUuidList() {
        return this.selectList(getNameSpace("selectProjectUuidList"),null);
    }
}
