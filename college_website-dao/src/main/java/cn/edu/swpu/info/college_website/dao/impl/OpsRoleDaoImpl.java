/*
 * Copyright (c) 2016 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.dao.impl;

import cn.edu.swpu.info.college_website.dao.OpsRoleDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.OpsRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OpsRoleDao 实现类
 * @author J-ONE
 * @since 2016-09-26
 */
@Repository("opsRoleDao")
public class OpsRoleDaoImpl extends BaseDaoImpl<OpsRole,Long> implements OpsRoleDao {
	private final static String NAMESPACE = "com.jd.smart_fridge_ops.dao.OpsRoleDao.";
	
	//返回本DAO命名空间,并添加statement
	public String getNameSpace(String statement) {
		return NAMESPACE + statement;
	}

	@Override
	public List<OpsRole> selectSelectiveRolesByUser(long userId) {
		return getSqlTemplate(false, true).selectList(NAMESPACE + "selectSelectiveRolesByUser", userId);
	}
		
}