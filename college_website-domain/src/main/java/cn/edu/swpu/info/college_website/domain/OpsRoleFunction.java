/*
 * Copyright (c) 2016 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.domain;

import cn.edu.swpu.info.college_website.domain.base.BaseDomain;

import java.util.Date;

/**
 * opsRoleFunction
 * @author J-ONE
 * @since 2016-09-26
 */
public class OpsRoleFunction extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private Long roleId;
	private Long functionId;
	private Date gmtCreate;
	private Date gmtModify;

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