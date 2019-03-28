package cn.edu.swpu.info.college_website.web.dto;


import cn.edu.swpu.info.college_website.domain.OpsRole;

/**
 * 
 * <b>描述：</b> <br/>
 * @author<b>作者：</b> weijunlong@jd.com <br/>
 * <b>时间：</b> 2017年5月12日 下午7:17:31 <br/>
 * <b>Copyright (c)</b> 2017京东智能-版权所有<br/>
 */
public class OpsRoleCheckDto extends OpsRole {
	private Integer checked;

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}
}
