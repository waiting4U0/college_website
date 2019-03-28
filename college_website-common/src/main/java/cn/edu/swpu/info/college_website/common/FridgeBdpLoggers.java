/*
 * Copyright (c) 2015 www.jd.com All rights reserved.
 * 本软件源代码版权归京东智能集团所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <b>描述：</b> 定义大数据的日志类 <br/>
 * @author<b>作者：</b> lichangchun<br/>
 * <b>时间：</b> 2016年1月27日 上午11:04:28 <br/>
 * <b>Copyright (c)</b> 2016京东智能-版权所有<br/>
 */
public enum FridgeBdpLoggers {

	FRIDGE_DEV_CONTROL("FRIDGE_DEV_CONTROL_LOGGER"),//1设备激活//2设备绑定//3设备解绑//4设备设备初始化设置
    ;

	/** Logger 名称 */
	private final String logName;

	FridgeBdpLoggers(String logName) {
		this.logName = logName;
	}

	public String getLogName() {
		return logName;
	}

	/**
	 * 获得相应的Logger
	 *
	 *@author lichangchun  
	 *@return - LogName对应的Logger
	 */
	public Logger getLogger() {
		return LoggerFactory.getLogger(getLogName());
	}
}
