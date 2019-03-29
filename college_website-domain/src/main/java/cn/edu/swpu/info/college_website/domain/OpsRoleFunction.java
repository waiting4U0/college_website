
package cn.edu.swpu.info.college_website.domain;

import cn.edu.swpu.info.college_website.domain.base.BaseDomain;

import java.util.Date;


public class OpsRoleFunction extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private Long roleId;
	private Long functionId;

	public OpsRoleFunction(){
		//默认无参构造方法
	}

	/**
	 * 获取 roleId
	 * @return
	 */
	public Long getRoleId(){
		return roleId;
	}
	
	/**
	 * 设置 roleId
	 * @param roleId
	 */
	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}

	/**
	 * 获取 functionId
	 * @return
	 */
	public Long getFunctionId(){
		return functionId;
	}
	
	/**
	 * 设置 functionId
	 * @param functionId
	 */
	public void setFunctionId(Long functionId){
		this.functionId = functionId;
	}

}