/*
 * Copyright (c) 2016 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.dao.impl;


import cn.edu.swpu.info.college_website.dao.OpsAdminDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.OpsAdmin;
import org.springframework.stereotype.Repository;

/**
 * OpsAdminDao 实现类
 * @author J-ONE
 * @since 2016-09-26
 */
@Repository("opsAdminDao")
public class OpsAdminDaoImpl extends BaseDaoImpl<OpsAdmin,Long> implements OpsAdminDao {
	private final static String NAMESPACE = "com.jd.smart_fridge_ops.dao.OpsAdminDao.";
	
	//返回本DAO命名空间,并添加statement
	public String getNameSpace(String statement) {
		return NAMESPACE + statement;
	}
		
}