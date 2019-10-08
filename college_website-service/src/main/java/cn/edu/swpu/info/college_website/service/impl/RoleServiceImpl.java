package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.dao.RoleDao;
import cn.edu.swpu.info.college_website.domain.Role;
import cn.edu.swpu.info.college_website.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author:源.Gao
 * @createtime: 2019-09-29 21:34
 **/
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;
    @Override
    public List<Role> getRoleList(Role role) {
        return roleDao.selectEntryList(new Role());
    }

    @Override
    public Integer countOpsFunction(Role role) {
        return null;
    }

    @Override
    public void saveRole(Role role, List<Long> opsResource) {

    }

    @Override
    public void modifyEntryByKey(Role role, List<Long> opsResource) {

    }

    @Override
    public int removeEntryByKey(Long id) {
        return 0;
    }
}
