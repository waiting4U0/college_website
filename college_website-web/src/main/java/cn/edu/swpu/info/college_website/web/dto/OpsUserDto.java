package cn.edu.swpu.info.college_website.web.dto;


import cn.edu.swpu.info.college_website.domain.OpsUser;

import java.util.List;

/**
 * 
 * <b>描述：</b> <br/>
 * @author<b>作者：</b> weijunlong@jd.com <br/>
 * <b>时间：</b> 2017年5月12日 下午3:16:00 <br/>
 * <b>Copyright (c)</b> 2017京东智能-版权所有<br/>
 */
public class OpsUserDto extends OpsUser {
	
	List<Long> opsResource;

	public List<Long> getOpsResource() {
		return opsResource;
	}

	public void setOpsResource(List<Long> opsResource) {
		this.opsResource = opsResource;
	}

}
