
package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.dao.OpsFunctionDao;
import cn.edu.swpu.info.college_website.dao.OpsRoleDao;
import cn.edu.swpu.info.college_website.dao.OpsRoleFunctionDao;
import cn.edu.swpu.info.college_website.dao.OpsUserDao;
import cn.edu.swpu.info.college_website.domain.OpsFunction;
import cn.edu.swpu.info.college_website.domain.OpsRole;
import cn.edu.swpu.info.college_website.domain.OpsRoleFunction;
import cn.edu.swpu.info.college_website.domain.OpsUser;
import cn.edu.swpu.info.college_website.service.PermissionService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Resource
	OpsUserDao opsUserDao;

	@Resource
	OpsRoleFunctionDao opsRoleFunctionDao;

	@Resource
	OpsFunctionDao opsFunctionDao;

	@Resource
	OpsRoleDao opsRoleDao;


	@Override
	public List<OpsFunction> getOpsFunctionsByPin(String pin) {
		OpsUser opsUser = new OpsUser();
		opsUser.setErp(pin);
		List<OpsUser> opsUsers = opsUserDao.selectEntryList(opsUser);
		if (CollectionUtils.isEmpty(opsUsers)) {
			return null;
		}
		
		List<OpsRoleFunction> opsRoleFunctionList = new ArrayList<OpsRoleFunction>();
		
        for (OpsUser ou : opsUsers) {
            OpsRoleFunction opsRoleFunction = new OpsRoleFunction();
            opsRoleFunction.setRoleId(ou.getRoleId());
            List<OpsRoleFunction> list = opsRoleFunctionDao
                    .selectEntryList(opsRoleFunction);
            opsRoleFunctionList.addAll(list);
        }

		if (CollectionUtils.isEmpty(opsRoleFunctionList)) {
			return null;
		}
		List<Long> functionIdList = new ArrayList<Long>();
		for (OpsRoleFunction roleFunction : opsRoleFunctionList) {
			functionIdList.add(roleFunction.getFunctionId());
		}
		Long[] functionIds = functionIdList.toArray(new Long[functionIdList.size()]);
		List<OpsFunction> opsFunctionList = opsFunctionDao.selectEntryList(functionIds);

		return opsFunctionList;
	}

	@Override
	public Integer countOpsUser(OpsUser opsUser) {

		return opsUserDao.selectEntryListCount(opsUser);
	}

	@Override
	public List<OpsUser> getOpsUserList(OpsUser opsUser) {
		List<OpsUser> opsUsers = opsUserDao.selectEntryList(opsUser);
		
		List<OpsUser> opsUserList = new ArrayList<OpsUser>();
		String temporaryErp = "";
		OpsUser temporaryUser = null;
		for (OpsUser user : opsUsers) {
			String erp = user.getErp();
			if (erp.equals(temporaryErp)) {
				String roleName = temporaryUser.getRoleName();
				Long roleId = user.getRoleId();
				OpsRole opsRole = opsRoleDao.selectEntry(roleId);
				temporaryUser.setRoleName(roleName + "," + opsRole.getName());
			} else {
				Long roleId = user.getRoleId();
				OpsRole opsRole = opsRoleDao.selectEntry(roleId);
				user.setRoleName(opsRole.getName());
				opsUserList.add(user);
				temporaryErp = erp;
				temporaryUser = user;
			}
		}
		
		return opsUserList;
	}
}