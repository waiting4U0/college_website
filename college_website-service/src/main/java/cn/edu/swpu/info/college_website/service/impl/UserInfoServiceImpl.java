package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.dao.UserInfoDao;
import cn.edu.swpu.info.college_website.domain.UserInfo;
import cn.edu.swpu.info.college_website.domain.common.Page;
import cn.edu.swpu.info.college_website.domain.exception.AppException;
import cn.edu.swpu.info.college_website.service.UserInfoService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Resource
    private UserInfoDao userInfoDao;

    public Page<UserInfo> selectPage(UserInfo condition, Page<UserInfo> page) {
        try {
            Class<?> clz = condition.getClass();
            clz.getMethod("setStartIndex", Integer.class).invoke(condition, page.getStartIndex());
            clz.getMethod("setEndIndex", Integer.class).invoke(condition, page.getEndIndex());
        } catch (Exception e) {
            throw new AppException("设置分页参数失败", e);
        }
        Integer size = userInfoDao.selectEntryListCount(condition);
        if (size == null || size <= 0){
            return page;
        }
        page.setTotalCount(size);
        page.setResult(userInfoDao.selectEntryList(condition));
        LOGGER.info("pageResult={}", JSON.toJSONString(page));
        return page;
    }


    @Transactional(rollbackFor = Exception.class)
    public long createUserInfo(UserInfo UserInfo) { return userInfoDao.insertEntry(UserInfo);
    }


    public long updateUserInfo(UserInfo UserInfo) {
        return userInfoDao.updateByKey(UserInfo);
    }


    public UserInfo getUserInfoById(Long id) {
        return userInfoDao.selectEntry(id);
    }


    public int remove(UserInfo condition) {
        return userInfoDao.deleteByKey(condition);
    }

    @Override
    public List<UserInfo> selectUserInfoList(UserInfo userInfo) {
        return userInfoDao.selectEntryList(userInfo);
    }
}
