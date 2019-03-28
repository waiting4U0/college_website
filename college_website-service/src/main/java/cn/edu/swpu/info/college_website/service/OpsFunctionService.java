package cn.edu.swpu.info.college_website.service;


import cn.edu.swpu.info.college_website.domain.OpsFunction;

import java.util.List;

/**
 * <b>描述：</b> <br/>
 * @author<b>作者：</b> lishuangquan@jd.com<br/>
 * <b>时间：</b>2016/10/8 13:56<br/>
 * <b>Copyright (c)</b> 2015-2016京东智能-版权所有<br/>
 */
public interface OpsFunctionService {

	Integer countOpsFunction(OpsFunction opsFunction);

	List<OpsFunction> getOpsFunctionSortList(OpsFunction opsFunction);

	List<OpsFunction> getParentList();

	Long insertEntryCreateId(OpsFunction opsFunction);

	void modifyEntryByKey(OpsFunction opsFunction);

	int removeEntryByKey(Long id);

	List<OpsFunction> selectEntryList(OpsFunction opsFunction);
}
