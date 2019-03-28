/*
 * Copyright (c) 2016 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.domain;

import cn.edu.swpu.info.college_website.domain.base.BaseDomain;

import java.util.Date;

/**
 * opsAdmin
 * @author J-ONE
 * @since 2016-09-26
 */
public class OpsAdmin extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private String erpAccount;
	private String email;
	private String name;
	private Integer rootLevel;
	private Integer operationLevel;
	private Date gmtCreate;
	private Date gmtModify;

	public OpsAdmin(){
		//默认无参构造方法
	}

	/**
	 * 获取 erpAccount
	 * @return
	 */
	public String getErpAccount(){
		return erpAccount;
	}
	
	/**
	 * 设置 erpAccount
	 * @param erpAccount
	 */
	public void setErpAccount(String erpAccount){
		this.erpAccount = erpAccount;
	}

	/**
	 * 获取 email
	 * @return
	 */
	public String getEmail(){
		return email;
	}
	
	/**
	 * 设置 email
	 * @param email
	 */
	public void setEmail(String email){
		this.email = email;
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
	 * 获取 rootLevel
	 * @return
	 */
	public Integer getRootLevel(){
		return rootLevel;
	}
	
	/**
	 * 设置 rootLevel
	 * @param rootLevel
	 */
	public void setRootLevel(Integer rootLevel){
		this.rootLevel = rootLevel;
	}

	/**
	 * 获取 operationLevel
	 * @return
	 */
	public Integer getOperationLevel(){
		return operationLevel;
	}
	
	/**
	 * 设置 operationLevel
	 * @param operationLevel
	 */
	public void setOperationLevel(Integer operationLevel){
		this.operationLevel = operationLevel;
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