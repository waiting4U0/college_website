package cn.edu.swpu.info.college_website.service;


import cn.edu.swpu.info.college_website.domain.OpsRole;
import cn.edu.swpu.info.college_website.domain.OpsRoleFunction;

import java.util.List;

/**
 * <b>描述：</b> <br/>
 * @author<b>作者：</b> lishuangquan@jd.com<br/>
 * <b>时间：</b>2016/9/30 11:31<br/>
 * <b>Copyright (c)</b> 2015-2016京东智能-版权所有<br/>
 */
public interface OpsRoleService {
	List<OpsRole> getRoleList(OpsRole opsRole);

	Integer countOpsFunction(OpsRole opsRole);

	void saveOpsRole(OpsRole opsRole, List<Long> opsResource);

	void modifyEntryByKey(OpsRole opsRole, List<Long> opsResource);

	int removeEntryByKey(Long id);

	List<Long> getRoleFunctionList(OpsRoleFunction opsRoleFunction);
}
