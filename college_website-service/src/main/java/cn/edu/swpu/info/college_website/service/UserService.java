package cn.edu.swpu.info.college_website.service;

import cn.edu.swpu.info.college_website.domain.User;
import cn.edu.swpu.info.college_website.domain.common.Page;

import java.util.List;


public interface UserService {
    Page<User> selectPage(User condition, Page<User> page);

    long createUserInfo(User userInfo);

    long updateUserInfo(User userInfo);

    User getUserInfoById(Long id);

    int remove(User condition);

    List<User> selectUserInfoList(User userInfo);

    //    public User selectUserByUsernameAndPassword(User userInfo);

    User checkUser(User userInfo);//检查数据库是否已经存在此用户

    boolean addStudent(User studentInfo);
}
