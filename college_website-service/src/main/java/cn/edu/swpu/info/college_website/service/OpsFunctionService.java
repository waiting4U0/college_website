package cn.edu.swpu.info.college_website.service;


import cn.edu.swpu.info.college_website.domain.OpsFunction;

import java.util.List;


public interface OpsFunctionService {

	Integer countOpsFunction(OpsFunction opsFunction);

	List<OpsFunction> getOpsFunctionSortList(OpsFunction opsFunction);

	List<OpsFunction> getParentList();

	Long insertEntryCreateId(OpsFunction opsFunction);

	void modifyEntryByKey(OpsFunction opsFunction);

	int removeEntryByKey(Long id);

	List<OpsFunction> selectEntryList(OpsFunction opsFunction);
}
