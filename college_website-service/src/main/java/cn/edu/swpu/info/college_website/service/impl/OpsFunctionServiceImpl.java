/*
 * Copyright (c) 2015 www.jd.com All rights reserved.
 * 本软件源代码版权归京东智能集团所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.dao.OpsFunctionDao;
import cn.edu.swpu.info.college_website.dao.OpsRoleFunctionDao;
import cn.edu.swpu.info.college_website.domain.OpsFunction;
import cn.edu.swpu.info.college_website.domain.OpsRoleFunction;
import cn.edu.swpu.info.college_website.service.OpsFunctionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>描述：</b> <br/>
 * @author<b>作者：</b> lishuangquan@jd.com<br/>
 * <b>时间：</b>2016/10/8 13:59<br/>
 * <b>Copyright (c)</b> 2015-2016京东智能-版权所有<br/>
 */
@Service
public class OpsFunctionServiceImpl implements OpsFunctionService {

	@Resource
	OpsFunctionDao opsFunctionDao;

	@Resource
	OpsRoleFunctionDao opsRoleFunctionDao;

	@Override
	public Integer countOpsFunction(OpsFunction opsFunction) {

		return opsFunctionDao.selectEntryListCount(opsFunction);
	}

	@Override
	public List<OpsFunction> getOpsFunctionSortList(OpsFunction opsFunction) {
		List<OpsFunction> opsFunctions = opsFunctionDao.selectEntryList(new OpsFunction());
 		List<OpsFunction> opsFunctionList = new ArrayList<OpsFunction>();
		for (OpsFunction functionFather : opsFunctions) {
			if (functionFather.getParentId() != null) {
				continue;
			}
			opsFunctionList.add(functionFather);
			for (OpsFunction opsFunctionChild : opsFunctions) {
				if (functionFather.getId().equals(opsFunctionChild.getParentId())) {
					opsFunctionList.add(opsFunctionChild);
				}
			}
		}
		Integer startIndex = opsFunction.getStartIndex();
		Integer endIndex = opsFunction.getEndIndex();
		if (startIndex == null && endIndex == null) {
			return opsFunctionList;
		}
		if (opsFunctionList.size() <= startIndex) {
			return new ArrayList<OpsFunction>();
		} else if (opsFunctionList.size() <= endIndex) {
			return opsFunctionList.subList(startIndex, opsFunctionList.size());
		} else {
			return opsFunctionList.subList(startIndex, endIndex);
		}
	}

	@Override
	public List<OpsFunction> getParentList() {
		List<OpsFunction> opsFunctions = opsFunctionDao.selectEntryList(new OpsFunction());
		List<OpsFunction> functionList = new ArrayList<OpsFunction>();
		for (OpsFunction opsFunction : opsFunctions) {
			if (opsFunction.getParentId() == null) {
				functionList.add(opsFunction);
			}
		}
		return functionList;
	}

	@Override
	public Long insertEntryCreateId(OpsFunction opsFunction) {

		return opsFunctionDao.insertEntryCreateId(opsFunction);
	}

	@Override
	public void modifyEntryByKey(OpsFunction opsFunction) {
		opsFunctionDao.updateByKey(opsFunction);
	}

	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	@Override
	public int removeEntryByKey(Long id) {
		OpsRoleFunction opsRoleFunction = new OpsRoleFunction();
		opsRoleFunction.setFunctionId(id);
		opsRoleFunctionDao.deleteByKey(opsRoleFunction);
		return opsFunctionDao.deleteByKey(id);
	}

	@Override
	public List<OpsFunction> selectEntryList(OpsFunction opsFunction) {
		return opsFunctionDao.selectEntryList(opsFunction);
	}

}