/*
 * Copyright (c) 2015 www.jd.com All rights reserved.
 * 本软件源代码版权归京东智能集团所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.web.dto;


import cn.edu.swpu.info.college_website.domain.OpsRole;

import java.util.List;

/**
 * <b>描述：</b> <br/>
 * @author<b>作者：</b> lishuangquan@jd.com<br/>
 * <b>时间：</b>2016/10/8 21:39<br/>
 * <b>Copyright (c)</b> 2015-2016京东智能-版权所有<br/>
 */
public class OpsRoleDto extends OpsRole {
	List<Long> opsResource;

	public List<Long> getOpsResource() {
		return opsResource;
	}

	public void setOpsResource(List<Long> opsResource) {
		this.opsResource = opsResource;
	}
}