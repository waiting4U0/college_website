package cn.edu.swpu.info.college_website.dao.impl;

import cn.edu.swpu.info.college_website.dao.UserDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangkun on 2019/3/16.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    private final static String NAMESPACE = "cn.edu.swpu.info.college_website.dao.UserDao.";

    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

    public List<String> selectProjectUuidList() {
        return this.selectList(getNameSpace("selectProjectUuidList"), null);
    }

    @Override
    public User selectUserByKey(User userInfo) {
        return this.selectOneByKey(userInfo);
    }



    @Override
    public long removeOneByCode(String code) {

        long i = (long) this.delete(getNameSpace("removeOneByCode"),code);
        return i;
    }




    public List<User> selectAllUsers(){ return this.selectEntryList(new User());}
}
