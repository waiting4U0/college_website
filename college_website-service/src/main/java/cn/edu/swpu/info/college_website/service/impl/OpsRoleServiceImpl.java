/*
 * Copyright (c) 2015 www.jd.com All rights reserved.
 * 本软件源代码版权归京东智能集团所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.dao.OpsRoleDao;
import cn.edu.swpu.info.college_website.dao.OpsRoleFunctionDao;
import cn.edu.swpu.info.college_website.domain.OpsRole;
import cn.edu.swpu.info.college_website.domain.OpsRoleFunction;
import cn.edu.swpu.info.college_website.service.OpsRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>描述：</b> <br/>
 * @author<b>作者：</b> lishuangquan@jd.com<br/>
 * <b>时间：</b>2016/9/30 11:33<br/>
 * <b>Copyright (c)</b> 2015-2016京东智能-版权所有<br/>
 */
@Service
public class OpsRoleServiceImpl implements OpsRoleService {
	@Resource
	OpsRoleDao opsRoleDao;

	@Resource
	OpsRoleFunctionDao opsRoleFunctionDao;
	@Override
	public List<OpsRole> getRoleList(OpsRole opsRole) {

		return opsRoleDao.selectEntryList(opsRole);
	}

	@Override
	public Integer countOpsFunction(OpsRole opsRole) {

		return opsRoleDao.selectEntryListCount(opsRole);
	}

	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	@Override
	public void saveOpsRole(OpsRole opsRole, List<Long> opsResource) {
		Long roleId = opsRoleDao.insertEntryCreateId(opsRole);

		List<OpsRoleFunction> opsRoleFunctionList = new ArrayList<OpsRoleFunction>();
		for (Long resourceId : opsResource) {
			OpsRoleFunction opsRoleFunctionAdd = new OpsRoleFunction();
			opsRoleFunctionAdd.setGmtCreate(opsRole.getGmtCreate());

			opsRoleFunctionAdd.setFunctionId(resourceId);
			opsRoleFunctionAdd.setRoleId(roleId);

			opsRoleFunctionList.add(opsRoleFunctionAdd);
		}
		opsRoleFunctionDao.insertEntry(opsRoleFunctionList.toArray(new OpsRoleFunction[opsRoleFunctionList.size()]));
	}

	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	@Override
	public void modifyEntryByKey(OpsRole opsRole, List<Long> opsResource) {

		opsRoleDao.updateByKey(opsRole);

		OpsRoleFunction opsRoleFunction = new OpsRoleFunction();
		opsRoleFunction.setRoleId(opsRole.getId());
		opsRoleFunctionDao.deleteByKey(opsRoleFunction);

		List<OpsRoleFunction> opsRoleFunctionList = new ArrayList<OpsRoleFunction>();
		for (Long resourceId : opsResource) {
			OpsRoleFunction opsRoleFunctionModified = new OpsRoleFunction();
			opsRoleFunctionModified.setGmtModify(opsRole.getGmtModify());
			opsRoleFunctionModified.setGmtCreate(opsRole.getGmtCreate());

			opsRoleFunctionModified.setFunctionId(resourceId);
			opsRoleFunctionModified.setRoleId(opsRole.getId());

			opsRoleFunctionList.add(opsRoleFunctionModified);
		}

		opsRoleFunctionDao.insertEntry(opsRoleFunctionList.toArray(new OpsRoleFunction[opsRoleFunctionList.size()]));

	}

	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	@Override
	public int removeEntryByKey(Long id) {
		int count = opsRoleDao.deleteByKey(id);
		OpsRoleFunction opsRoleFunction = new OpsRoleFunction();
		opsRoleFunction.setRoleId(id);
		opsRoleFunctionDao.deleteByKey(opsRoleFunction);
		return count;
	}

	@Override
	public List<Long> getRoleFunctionList(OpsRoleFunction opsRoleFunction) {
		List<OpsRoleFunction> opsRoleFunctions = opsRoleFunctionDao.selectEntryList(opsRoleFunction);
		List<Long> functionList = new ArrayList<Long>();
		for (OpsRoleFunction roleFunction : opsRoleFunctions) {
			functionList.add(roleFunction.getFunctionId());
		}
		return functionList;
	}
}