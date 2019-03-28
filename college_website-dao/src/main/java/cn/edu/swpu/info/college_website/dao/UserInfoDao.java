package cn.edu.swpu.info.college_website.dao;/*
 * Copyright (c) 2016 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */

import java.util.List;

import cn.edu.swpu.info.college_website.dao.base.BaseDao;
import cn.edu.swpu.info.college_website.domain.UserInfo;

/**
 * DeviceDao 接口
 * @author J-ONE
 * @since 2016-11-03
 */
public interface UserInfoDao extends BaseDao<UserInfo,Long> {
	//自定义扩展
	public List<String> selectProjectUuidList();

}