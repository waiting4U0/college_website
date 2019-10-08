package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.common.tools.Md5Utils;
import cn.edu.swpu.info.college_website.dao.UserDao;
import cn.edu.swpu.info.college_website.domain.User;
import cn.edu.swpu.info.college_website.domain.common.Page;
import cn.edu.swpu.info.college_website.domain.common.State;
import cn.edu.swpu.info.college_website.domain.exception.AppException;
import cn.edu.swpu.info.college_website.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
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

//
//    @Transactional(rollbackFor = Exception.class)
//    public long createUserInfo(User UserInfo) {
//        return userDao.insertEntry(UserInfo);
//    }

    public long updateUserInfo(User UserInfo) {
        return userDao.updateByKey(UserInfo);
    }

    public User getUserInfoByKey(User user ) {
        return userDao.selectOneByKey(user);
    }

    @Override
    public boolean removeByCode(String code) {

        long i =userDao.removeOneByCode(code);
        if(i==1){
            System.out.println("删除成功");
            return true;
        }
        return false;
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

    @Override
    public String checkUser(User webuser) {
        User dbUser = userDao.selectUserByKey(webuser);//查找用户信息
        try {
            State msg = State.illegalArgument();
            if(dbUser != null){
                if(webuser.getPassword().equals(dbUser.getPassword())){
                    msg= State.success();
                }else {
                    msg= State.systemError();
                    msg.setErrorMsg("密码错误");
                }
            }else {
                msg= State.systemError();
                msg.setErrorMsg("用户不存在");
            }
            return JSONObject.toJSONString(msg);
        } catch (Exception e) {
            State msg = State.systemError();
            return JSONObject.toJSONString(msg);
        }
    }
    @Override
    public boolean addStudent(User webUser) {
        User dbUser = userDao.selectUserByKey(webUser);
        User webUsercopy = webUser;
        if(dbUser == null){
            webUsercopy.setPassword(Md5Utils.Md5(webUser.getPassword()));
            if(userDao.insertEntryCreateId(webUsercopy)==1){
                return true;
            }
        }
        return false;
    }
    @Override
        public List<User> getAllStudentList(User studentInfo) {
        List<User> users = userDao.selectEntryList(studentInfo);
        List<User> studentList = new ArrayList<>();
        String role = "学生";
        for (User user :users) {
            if(user.getRole().equals(role)){
                //System.out.println(user.toString());
                studentList.add(user);

            }
        }
        if(studentList.size()==0) {
            return null;
        } else
            return studentList;
    }

    @Override
    public List<User> getAllTeacherList(User teacherInfo) {
        List<User> users = userDao.selectEntryList(teacherInfo);
        List<User> teacherList = new ArrayList<>();
        String role1 = "教师";
        String role2 = "院长";
        String role3 = "管理员";
        for (User user : users
             ) {
            if(user.getRole().equals(role1) ||user.getRole().equals(role2) ||user.getRole().equals(role3)){
                teacherList.add(user);
            }
        }
        return teacherList;
    }

}
