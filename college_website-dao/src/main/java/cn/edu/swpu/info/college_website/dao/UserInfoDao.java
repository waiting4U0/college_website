package cn.edu.swpu.info.college_website.dao;

import java.util.List;

import cn.edu.swpu.info.college_website.dao.base.BaseDao;
import cn.edu.swpu.info.college_website.domain.UserInfo;

/**
 * DeviceDao 接口
 */
public interface UserInfoDao extends BaseDao<UserInfo,Long> {
	//自定义扩展
	public List<String> selectProjectUuidList();

}