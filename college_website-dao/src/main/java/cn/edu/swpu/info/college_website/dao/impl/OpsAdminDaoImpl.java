
package cn.edu.swpu.info.college_website.dao.impl;


import cn.edu.swpu.info.college_website.dao.OpsAdminDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.OpsAdmin;
import org.springframework.stereotype.Repository;

/**
 * OpsAdminDao 实现类
 */
@Repository("opsAdminDao")
public class OpsAdminDaoImpl extends BaseDaoImpl<OpsAdmin,Long> implements OpsAdminDao {
	private final static String NAMESPACE = "cn.edu.swpu.info.college_website.dao.OpsAdminDao.";
	
	//返回本DAO命名空间,并添加statement
	public String getNameSpace(String statement) {
		return NAMESPACE + statement;
	}
		
}