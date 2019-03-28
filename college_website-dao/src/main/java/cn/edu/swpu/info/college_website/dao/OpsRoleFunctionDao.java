/*
 * Copyright (c) 2016 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.dao;



import cn.edu.swpu.info.college_website.dao.base.BaseDao;
import cn.edu.swpu.info.college_website.domain.OpsRoleFunction;

import java.util.List;

/**
 * OpsRoleFunctionDao 接口
 * @author J-ONE
 * @since 2016-09-26
 */
public interface OpsRoleFunctionDao extends BaseDao<OpsRoleFunction,Long> {
	//自定义扩展
	List<String> selectFunctionsByRoleId(Long id);

}