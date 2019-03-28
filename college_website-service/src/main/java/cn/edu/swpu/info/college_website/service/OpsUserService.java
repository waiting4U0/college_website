package cn.edu.swpu.info.college_website.service;



import cn.edu.swpu.info.college_website.domain.OpsUser;

import java.util.List;

/**
 * <b>描述：</b> <br/>
 * @author<b>作者：</b> lishuangquan@jd.com<br/>
 * <b>时间：</b>2016/9/29 11:15<br/>
 * <b>Copyright (c)</b> 2015-2016京东智能-版权所有<br/>
 */
public interface OpsUserService {

	Long insertEntryCreateId(OpsUser opsUser);

	/**
	 * 
	 * <b>描述：</b> <br/>
	 * @author<b>创建人：</b> weijunlong@jd.com <br/>
	 * <b>创建时间：</b> 2017年5月12日 下午3:32:18 <br/>
	 * <b>修改人：</b> weijunlong@jd.com <br/>
	 * <b>时间：</b> 2017年5月12日 下午3:32:18 <br/>
	 * <b>修改内容：</b>  <br/>
	 */
	void modifyEntryByKey(OpsUser opsUser, List<Long> opsResource);

	int removeEntryByKey(Long id);

	OpsUser getOpsUserByKey(Long id);

	List<OpsUser> getOpsUserList(OpsUser condition);
	
	/**
	 * 
	 * <b>描述：</b> <br/>
	 * @author<b>创建人：</b> weijunlong@jd.com <br/>
	 * <b>创建时间：</b> 2017年5月12日 下午3:32:15 <br/>
	 * <b>修改人：</b> weijunlong@jd.com <br/>
	 * <b>时间：</b> 2017年5月12日 下午3:32:15 <br/>
	 * <b>修改内容：</b>  <br/>
	 */
	void saveOpsUser(OpsUser opsUser, List<Long> opsResource);
	
	/**
	 * 
	 * <b>描述：</b> <br/>
	 * @author<b>创建人：</b> weijunlong@jd.com <br/>
	 * <b>创建时间：</b> 2017年5月12日 下午8:36:36 <br/>
	 * <b>修改人：</b> weijunlong@jd.com <br/>
	 * <b>时间：</b> 2017年5月12日 下午8:36:36 <br/>
	 * <b>修改内容：</b>  <br/>
	 */
	List<Long> getRoleIdsByCondition(OpsUser opsUser);
}
