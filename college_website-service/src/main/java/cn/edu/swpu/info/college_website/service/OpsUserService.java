package cn.edu.swpu.info.college_website.service;

import cn.edu.swpu.info.college_website.domain.OpsUser;

import java.util.List;


public interface OpsUserService {

	Long insertEntryCreateId(OpsUser opsUser);


	void modifyEntryByKey(OpsUser opsUser, List<Long> opsResource);

	int removeEntryByKey(Long id);

	OpsUser getOpsUserByKey(Long id);

	List<OpsUser> getOpsUserList(OpsUser condition);
	

	void saveOpsUser(OpsUser opsUser, List<Long> opsResource);
	

	List<Long> getRoleIdsByCondition(OpsUser opsUser);
}
