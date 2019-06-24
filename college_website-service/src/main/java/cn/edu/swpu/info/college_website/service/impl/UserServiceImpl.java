package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.common.tools.Md5Utils;
import cn.edu.swpu.info.college_website.common.tools.PasswordVerify;
import cn.edu.swpu.info.college_website.dao.UserDao;
import cn.edu.swpu.info.college_website.domain.User;
import cn.edu.swpu.info.college_website.domain.common.Page;
import cn.edu.swpu.info.college_website.domain.exception.AppException;
import cn.edu.swpu.info.college_website.service.UserService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service("userInfoService")
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
       @Resource
       private UserDao userDao;


    public Page<User> selectPage(User condition, Page<User> page) {
        try {
            Class<?> clz = condition.getClass();
            clz.getMethod("setStartIndex", Integer.class).invoke(condition, page.getStartIndex());
            clz.getMethod("setEndIndex", Integer.class).invoke(condition, page.getEndIndex());
        } catch (Exception e) {
            throw new AppException("设置分页参数失败", e);
        }
        Integer size = userDao.selectEntryListCount(condition);
        if (size == null || size <= 0) {
            return page;
        }
        page.setTotalCount(size);
        page.setResult(userDao.selectEntryList(condition));
        LOGGER.info("pageResult={}", JSON.toJSONString(page));
        return page;
    }


    @Transactional(rollbackFor = Exception.class)
    public long createUserInfo(User UserInfo) {
        return userDao.insertEntry(UserInfo);
    }


    public long updateUserInfo(User UserInfo) {
        return userDao.updateByKey(UserInfo);
    }


    public User getUserInfoById(Long id) {
        return userDao.selectEntry(id);
    }


    public int remove(User condition) {
        return userDao.deleteByKey(condition);
    }

    @Override
    public List<User> selectUserInfoList(User userInfo) {
        return userDao.selectEntryList(userInfo);
    }

    //    @Override
//    public boolean userLogin(User userInfo) {
//        selectUserInfoList(userInfo)
//        return true;
//    }
    public User checkUser(User webuser) {
        User dbUser = userDao.selectUserByKey(webuser);//查找用户信息
        User user = webuser;//接受登录用户信息
        //dataVerifyTool.isNull(userInfo1,"该用户不存在");
        if(dbUser != null){
            //验证密码PasswordVerify.isRightPassword(webuser,dbUser)
            if(PasswordVerify.isRightPassword(webuser,dbUser)){
                return dbUser;
            }else {
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean addStudent(User webUser) {
        User dbUser = checkUser(webUser);
        User webUsercopy = webUser;
        if(dbUser == null){
            webUsercopy.setPassword(Md5Utils.Md5(webUser.getPassword()));
            if(userDao.insertEntryCreateId(webUsercopy)==1){
                return true;
            }
        }
        return false;
    }

}
