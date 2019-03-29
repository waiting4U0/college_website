
package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.dao.OpsRoleDao;
import cn.edu.swpu.info.college_website.dao.OpsUserDao;
import cn.edu.swpu.info.college_website.domain.OpsRole;
import cn.edu.swpu.info.college_website.domain.OpsUser;
import cn.edu.swpu.info.college_website.service.OpsUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
public class OpsUserServiceImpl implements OpsUserService {

	@Resource
	OpsUserDao opsUserDao;

	@Resource
	OpsRoleDao opsRoleDao;

	@Override
	public Long insertEntryCreateId(OpsUser opsUser) {

		return opsUserDao.insertEntryCreateId(opsUser);
	}

	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	@Override
	public void modifyEntryByKey(OpsUser opsUser, List<Long> opsResource) {
		//先删除
		OpsUser delete = new OpsUser();
		delete.setErp(opsUser.getErp());
		opsUserDao.deleteByKey(delete);
		//重新写入
		for (Long roleId : opsResource) {
			OpsUser condition = new OpsUser();
			condition.setErp(opsUser.getErp());
			condition.setName(opsUser.getName());
			condition.setCreateDate(new Date());
			OpsRole opsRole = opsRoleDao.selectEntry(roleId);
			condition.setRoleName(opsRole.getName());
			condition.setRoleId(roleId);
			this.insertEntryCreateId(condition);
		}
	}

	@Override
	public int removeEntryByKey(Long id) {
		OpsUser opsUser = opsUserDao.selectEntry(id);
		OpsUser condition = new OpsUser();
		condition.setErp(opsUser.getErp());
		return opsUserDao.deleteByKey(condition);
	}

	@Override
	public OpsUser getOpsUserByKey(Long id) {
		return opsUserDao.selectEntry(id);
	}

	@Override
	public List<OpsUser> getOpsUserList(OpsUser condition) {
		return opsUserDao.selectEntryList(condition);
	}

	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	@Override
	public void saveOpsUser(OpsUser opsUser, List<Long> opsResource) {
		for (Long roleId : opsResource) {
			OpsUser condition = new OpsUser();
			condition.setErp(opsUser.getErp());
			condition.setName(opsUser.getName());
			condition.setCreateDate(opsUser.getCreateDate());
			OpsRole opsRole = opsRoleDao.selectEntry(roleId);
			condition.setRoleName(opsRole.getName());
			condition.setRoleId(roleId);
			this.insertEntryCreateId(condition);
		}
	}

	@Override
	public List<Long> getRoleIdsByCondition(OpsUser opsUser) {
		OpsUser condition = new OpsUser();
		condition.setErp(opsUser.getErp());
		List<OpsUser> opsUserList = opsUserDao.selectEntryList(condition);
		List<Long> roleIdList = new ArrayList<Long>();
		if (CollectionUtils.isEmpty(opsUserList)) {
			return Collections.emptyList();
		} else {
			for (OpsUser o : opsUserList) {
				roleIdList.add(o.getRoleId());
			}
		}

		return roleIdList;
	}

}