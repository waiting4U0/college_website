package cn.edu.swpu.info.college_website.service;


import cn.edu.swpu.info.college_website.domain.Role;

import java.util.List;


public interface RoleService {
    List<Role> getRoleList(Role role);

    Integer countOpsFunction(Role role);

    void saveRole(Role role, List<Long> opsResource);

    void modifyEntryByKey(Role role, List<Long> opsResource);

    int removeEntryByKey(Long id);

   // List<Long> getRolePermisionList(RolePermision RolePermision);
}
