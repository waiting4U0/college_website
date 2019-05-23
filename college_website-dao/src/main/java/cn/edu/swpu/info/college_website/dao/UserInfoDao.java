package cn.edu.swpu.info.college_website.dao;

import cn.edu.swpu.info.college_website.dao.base.BaseDao;
import cn.edu.swpu.info.college_website.domain.UserInfo;

import java.util.List;

/**
 * DeviceDao 接口
 */
public interface UserInfoDao extends BaseDao<UserInfo,Long> {
	//自定义扩展
	public List<String> selectProjectUuidList();
//	public UserInfo login(UserInfo userInfo);
	//UserInfo selectUserByUsernameAndPassword(UserInfo userInfo);

}