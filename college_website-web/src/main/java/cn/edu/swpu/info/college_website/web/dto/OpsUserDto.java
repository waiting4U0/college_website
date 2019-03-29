package cn.edu.swpu.info.college_website.web.dto;


import cn.edu.swpu.info.college_website.domain.OpsUser;

import java.util.List;


public class OpsUserDto extends OpsUser {
	
	List<Long> opsResource;

	public List<Long> getOpsResource() {
		return opsResource;
	}

	public void setOpsResource(List<Long> opsResource) {
		this.opsResource = opsResource;
	}

}
