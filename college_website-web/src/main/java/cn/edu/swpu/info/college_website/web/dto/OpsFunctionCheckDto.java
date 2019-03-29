
package cn.edu.swpu.info.college_website.web.dto;


import cn.edu.swpu.info.college_website.domain.OpsFunction;


public class OpsFunctionCheckDto extends OpsFunction {
	private Integer checked;

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}
}