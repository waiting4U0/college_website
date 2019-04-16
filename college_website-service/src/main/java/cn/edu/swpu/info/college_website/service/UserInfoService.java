package cn.edu.swpu.info.college_website.service;

import cn.edu.swpu.info.college_website.domain.UserInfo;
import cn.edu.swpu.info.college_website.domain.common.Page;

import java.util.List;


public interface UserInfoService {
    Page<UserInfo> selectPage(UserInfo condition, Page<UserInfo> page);

    long createUserInfo(UserInfo userInfo);

    long updateUserInfo(UserInfo userInfo);

    UserInfo getUserInfoById(Long id);
    int remove(UserInfo condition);
    List<UserInfo> selectUserInfoList(UserInfo userInfo);
}
