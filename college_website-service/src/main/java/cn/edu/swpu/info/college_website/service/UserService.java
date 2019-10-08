package cn.edu.swpu.info.college_website.service;

import cn.edu.swpu.info.college_website.domain.User;
import cn.edu.swpu.info.college_website.domain.common.Page;

import java.util.List;


public interface UserService {
    Page<User> selectPage(User condition, Page<User> page);
    /**
     * 增删改,单个记录
     */

//    long createUserInfo(User userInfo);

    long updateUserInfo(User userInfo);

    User getUserInfoByKey(User user);

    boolean removeByCode(String code);

    List<User> selectUserInfoList(User userInfo);

    //    public User selectUserByUsernameAndPassword(User userInfo);

    String checkUser(User userInfo);//检查数据库是否已经存在此用户

    boolean addStudent(User studentInfo);
    /**
     * 获取所有学生列表
     *
     */
    List<User> getAllStudentList(User studentInfo);
    /**
     * 获取所有教师列表
     *
     */
    List<User> getAllTeacherList(User studentInfo);
}
