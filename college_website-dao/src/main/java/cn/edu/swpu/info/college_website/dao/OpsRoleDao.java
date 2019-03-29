
package cn.edu.swpu.info.college_website.dao;



import cn.edu.swpu.info.college_website.dao.base.BaseDao;
import cn.edu.swpu.info.college_website.domain.OpsRole;

import java.util.List;

/**
 * OpsRoleDao 接口
 */
public interface OpsRoleDao extends BaseDao<OpsRole,Long> {
	//自定义扩展

	/**
	 * 根据用户id获取用户角色信息
	 * @param userId
	 * @return
	 */
	public List<OpsRole> selectSelectiveRolesByUser(long userId);
	
}