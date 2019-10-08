package cn.edu.swpu.info.college_website.dao.impl;

import cn.edu.swpu.info.college_website.dao.RoleDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OpsRoleDao 实现类
 */
@Repository("RoleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {
    private final static String NAMESPACE = "cn.edu.swpu.info.college_website.dao.RoleDao.";

    //返回本DAO命名空间,并添加statement
    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

    @Override
    public List<Role> selectSelectiveRolesByUser(long userId) {
        return getSqlTemplate(false, true).selectList(NAMESPACE + "selectSelectiveRolesByUser", userId);
    }


}