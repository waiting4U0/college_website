/*
 * Copyright (c) 2017 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.domain;

import cn.edu.swpu.info.college_website.domain.base.BaseDomain;

import java.util.Date;

/**
 * orderLog
 * @author J-ONE
 * @since 2017-09-29
 */
public class OrderLog extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private Integer channelType;
	private String channelName;
	private Long channelId;
	private String pin;
	private Integer orderNumber;
	private Integer paymentType;

	private Date searchStartDate;
	private Date searchEndDate;

	public OrderLog(){
		//默认无参构造方法
	}

	/**
	 * 获取 channelType
	 * @return
	 */
	public Integer getChannelType(){
		return channelType;
	}
	
	/**
	 * 设置 channelType
	 * @param channelType
	 */
	public void setChannelType(Integer channelType){
		this.channelType = channelType;
	}

	/**
	 * 获取 channelId
	 * @return
	 */
	public Long getChannelId(){
		return channelId;
	}
	
	/**
	 * 设置 channelId
	 * @param channelId
	 */
	public void setChannelId(Long channelId){
		this.channelId = channelId;
	}

	/**
	 * 获取 pin
	 * @return
	 */
	public String getPin(){
		return pin;
	}
	
	/**
	 * 设置 pin
	 * @param pin
	 */
	public void setPin(String pin){
		this.pin = pin;
	}

	/**
	 * 获取 orderNumber
	 * @return
	 */
	public Integer getOrderNumber(){
		return orderNumber;
	}
	
	/**
	 * 设置 orderNumber
	 * @param orderNumber
	 */
	public void setOrderNumber(Integer orderNumber){
		this.orderNumber = orderNumber;
	}

	/**
	 * 获取 paymentType
	 * @return
	 */
	public Integer getPaymentType(){
		return paymentType;
	}
	
	/**
	 * 设置 paymentType
	 * @param paymentType
	 */
	public void setPaymentType(Integer paymentType){
		this.paymentType = paymentType;
	}

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Date getSearchStartDate() {
        return searchStartDate;
    }

    public void setSearchStartDate(Date searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public Date getSearchEndDate() {
        return searchEndDate;
    }

    public void setSearchEndDate(Date searchEndDate) {
        this.searchEndDate = searchEndDate;
    }
}