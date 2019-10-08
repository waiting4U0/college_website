package cn.edu.swpu.info.college_website.dao;


import cn.edu.swpu.info.college_website.dao.base.BaseDao;
import cn.edu.swpu.info.college_website.domain.Role;

import java.util.List;

/**
 * OpsRoleDao 接口
 */
public interface RoleDao extends BaseDao<Role, Long> {
    //自定义扩展

    /**
     * 根据用户id获取用户角色信息
     *
     * @param userId
     * @return
     */

    public List<Role> selectSelectiveRolesByUser(long userId);

    /**
     * 获取角色列表
     * @return
     */

}