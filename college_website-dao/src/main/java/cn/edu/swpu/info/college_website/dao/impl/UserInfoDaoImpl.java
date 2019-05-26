package cn.edu.swpu.info.college_website.dao.impl;

import cn.edu.swpu.info.college_website.dao.UserInfoDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangkun on 2019/3/16.
 */
@Repository("userInfoDao")
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo, Long> implements UserInfoDao {
    static final String DEFAULT_SELECT__KEY = "selectByKey";
    private final static String NAMESPACE = "cn.edu.swpu.info.college_website.dao.UserInfoDao.";

    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

    public List<String> selectProjectUuidList() {
        return this.selectList(getNameSpace("selectProjectUuidList"), null);
    }

//    @Override
//    public UserInfo selectUserByUsernameAndPassword(UserInfo userInfo) {
//        return this.selectUserByUsernameAndPassword(userInfo);
//    }

//    @Override
//    public UserInfo login(UserInfo userInfo) {
//        UserInfo userInfo1 = new UserInfo();
//        userInfo1.setPassword(userInfo.getPassword());
//        userInfo1.setName(userInfo.getPassword());
//        return userInfo1;
//    }
}
