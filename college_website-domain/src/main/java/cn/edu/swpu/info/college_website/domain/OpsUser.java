
package cn.edu.swpu.info.college_website.domain;

import cn.edu.swpu.info.college_website.domain.base.BaseDomain;

import java.util.Date;


public class OpsUser extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private String erp;
	private String name;
	private Long roleId;
	private String roleName;

	public OpsUser(){
		//默认无参构造方法
	}

	/**
	 * 获取 erp
	 * @return
	 */
	public String getErp(){
		return erp;
	}
	
	/**
	 * 设置 erp
	 * @param erp
	 */
	public void setErp(String erp){
		this.erp = erp;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 获取 name
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 设置 name
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
}