package cn.edu.swpu.info.college_website.dao.impl;


import cn.edu.swpu.info.college_website.dao.OpsUserDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.OpsUser;
import org.springframework.stereotype.Repository;

/**
 * OpsUserDao 实现类
 */
@Repository("opsUserDao")
public class OpsUserDaoImpl extends BaseDaoImpl<OpsUser, Long> implements OpsUserDao {
    private final static String NAMESPACE = "cn.edu.swpu.info.college_website.dao.OpsUserDao.";

    //返回本DAO命名空间,并添加statement
    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

}