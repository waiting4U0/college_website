
package cn.edu.swpu.info.college_website.web.dto;


import cn.edu.swpu.info.college_website.domain.OpsRole;

import java.util.List;


public class OpsRoleDto extends OpsRole {
	List<Long> opsResource;

	public List<Long> getOpsResource() {
		return opsResource;
	}

	public void setOpsResource(List<Long> opsResource) {
		this.opsResource = opsResource;
	}
}