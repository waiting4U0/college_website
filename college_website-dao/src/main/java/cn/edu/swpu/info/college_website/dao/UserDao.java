package cn.edu.swpu.info.college_website.dao;

import cn.edu.swpu.info.college_website.dao.base.BaseDao;
import cn.edu.swpu.info.college_website.domain.User;

import java.util.List;

/**
 * DeviceDao 接口
 */
public interface UserDao extends BaseDao<User, Long> {
    //自定义扩展
    public List<String> selectProjectUuidList();

    //public User login(User userInfo);
    //User selectUserByUsernameAndPassword(User userInfo);
    User selectUserByKey(User userInfo);

}