/*
 * Copyright (c) 2016 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.domain;

import cn.edu.swpu.info.college_website.domain.base.BaseDomain;

import java.util.Date;

/**
 * opsUser
 * @author J-ONE
 * @since 2016-09-26
 */
public class OpsUser extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private String erp;
	private String name;
	private Long roleId;
	private String roleName;
	private Date gmtCreate;
	private Date gmtModify;

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

	/**
	 * 获取 gmtCreate
	 * @return
	 */
	public Date getGmtCreate(){
		return gmtCreate;
	}
	
	/**
	 * 设置 gmtCreate
	 * @param gmtCreate
	 */
	public void setGmtCreate(Date gmtCreate){
		this.gmtCreate = gmtCreate;
	}

	/**
	 * 获取 gmtModify
	 * @return
	 */
	public Date getGmtModify(){
		return gmtModify;
	}
	
	/**
	 * 设置 gmtModify
	 * @param gmtModify
	 */
	public void setGmtModify(Date gmtModify){
		this.gmtModify = gmtModify;
	}
}