/*
 * Copyright (c) 2016 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.domain;

import cn.edu.swpu.info.college_website.domain.base.BaseDomain;

import java.util.Date;

/**
 * opsRole
 * @author J-ONE
 * @since 2016-09-26
 */
public class OpsRole extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private Date gmtCreate;
	private Date gmtModify;

	public OpsRole(){
		//默认无参构造方法
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
	 * 获取 description
	 * @return
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * 设置 description
	 * @param description
	 */
	public void setDescription(String description){
		this.description = description;
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