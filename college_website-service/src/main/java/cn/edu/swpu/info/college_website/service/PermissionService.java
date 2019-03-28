package cn.edu.swpu.info.college_website.service;


import cn.edu.swpu.info.college_website.domain.OpsFunction;
import cn.edu.swpu.info.college_website.domain.OpsUser;

import java.util.List;

/**
 * <b>描述：</b> <br/>
 * @author<b>作者：</b> lishuangquan@jd.com<br/>
 * <b>时间：</b>2016/9/27 15:23<br/>
 * <b>Copyright (c)</b> 2015-2016京东智能-版权所有<br/>
 */
public interface PermissionService {

	/***
	 * 根据用户pin获取权限信息
	 * @param pin 查询条件
	 * @return 权限集合
	 */
	List<OpsFunction> getOpsFunctionsByPin(String pin);

	/**
	 * 统计opsUser数量
	 * @param opsUser 查询条件
	 * @return 总数
	 */
	Integer countOpsUser(OpsUser opsUser);

	/**
	 * 分页获取opsUser集合
	 * @param opsUser 查询条件
	 * @return 总数
	 */
	List<OpsUser> getOpsUserList(OpsUser opsUser);
}
