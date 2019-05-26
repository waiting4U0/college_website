package cn.edu.swpu.info.college_website.service;


import cn.edu.swpu.info.college_website.domain.OpsRole;
import cn.edu.swpu.info.college_website.domain.OpsRoleFunction;

import java.util.List;


public interface OpsRoleService {
    List<OpsRole> getRoleList(OpsRole opsRole);

    Integer countOpsFunction(OpsRole opsRole);

    void saveOpsRole(OpsRole opsRole, List<Long> opsResource);

    void modifyEntryByKey(OpsRole opsRole, List<Long> opsResource);

    int removeEntryByKey(Long id);

    List<Long> getRoleFunctionList(OpsRoleFunction opsRoleFunction);
}
