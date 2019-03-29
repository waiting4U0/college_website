
package cn.edu.swpu.info.college_website.dao.impl;

import cn.edu.swpu.info.college_website.dao.OpsRoleFunctionDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.OpsRoleFunction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OpsRoleFunctionDao 实现类
 */
@Repository("opsRoleFunctionDao")
public class OpsRoleFunctionDaoImpl extends BaseDaoImpl<OpsRoleFunction,Long> implements OpsRoleFunctionDao {
	private final static String NAMESPACE = "cn.edu.swpu.info.college_website.dao.OpsRoleFunctionDao.";
	
	//返回本DAO命名空间,并添加statement
	public String getNameSpace(String statement) {
		return NAMESPACE + statement;
	}

	@Override
	public List<String> selectFunctionsByRoleId(Long id) {
		return null;
	}
}