package cn.edu.swpu.info.college_website.dao.impl;

import cn.edu.swpu.info.college_website.dao.OpsRoleDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.OpsRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OpsRoleDao 实现类
 */
@Repository("opsRoleDao")
public class OpsRoleDaoImpl extends BaseDaoImpl<OpsRole, Long> implements OpsRoleDao {
    private final static String NAMESPACE = "cn.edu.swpu.info.college_website.dao.OpsRoleDao.";

    //返回本DAO命名空间,并添加statement
    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

    @Override
    public List<OpsRole> selectSelectiveRolesByUser(long userId) {
        return getSqlTemplate(false, true).selectList(NAMESPACE + "selectSelectiveRolesByUser", userId);
    }

}