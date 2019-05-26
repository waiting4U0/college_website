package cn.edu.swpu.info.college_website.dao;


import cn.edu.swpu.info.college_website.dao.base.BaseDao;
import cn.edu.swpu.info.college_website.domain.OpsRoleFunction;

import java.util.List;

/**
 * OpsRoleFunctionDao 接口
 */
public interface OpsRoleFunctionDao extends BaseDao<OpsRoleFunction, Long> {
    //自定义扩展
    List<String> selectFunctionsByRoleId(Long id);

}