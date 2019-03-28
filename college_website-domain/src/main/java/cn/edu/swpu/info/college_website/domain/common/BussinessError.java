/*
 * Copyright (c) 2015 www.jd.com All rights reserved.
 * 本软件源代码版权归京东智能集团所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.domain.common;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>描述：</b> <br/>
 * @author<b> 作者：</b> cdshenjian@jd.com<br/>
 * <b>时间：</b>2016/5/23 20:37<br/>
 * <b>Copyright (c)</b> 2015-2016京东智能-版权所有<br/>
 */
public class BussinessError {
	// 系统异常
	public static final int SYSTEM_ERROR = -1;
	// 参数非法
	public static final int ILLEGAL_ARGUMENT = -2;
	//获取结果为空
	public static final int NO_DATA_FOUND = 1003;
	// 设备相关异常
	public static final int NO_FEED_ID_FOUND = 2001;
	public static final int DEVICE_AUTH_ERROR = 2002;
	public static final int DEVICE_ACTIVATE_ERROR = 2003;
	public static final int ILLEGAL_DEVICE = 2004;
	public static final int ACTIVATED_DEVICE = 2005;
	public static final int MAX_GOODS_COUNT = 2007;
	public static final int MAX_GOODS_ALARM_COUNT = 2008;
	public static final int DEVICE_BIND_FAIL = 2009;
	public static final int DEVICE_UNBIND_FAIL = 2010;
	// 食物管理相关
	public static final int DUPLICATE_ALARM = 3001;
	// 第三方相关
	public static final int FAULT_REPORTING_ERROR = 4000;
	// 注册消息推送客户端失败
	public static final int AUDIENCE_REGISTER_ERROR = 9001;

	public static Map<Integer, String> errors = new HashMap();
	static {
		errors.put(NO_FEED_ID_FOUND, "用户无权限操作设备");
		errors.put(DEVICE_AUTH_ERROR, "设备认证失败");
		errors.put(AUDIENCE_REGISTER_ERROR, "推送客户端注册失败");
		errors.put(NO_DATA_FOUND, "获取数据为空");
		errors.put(DEVICE_ACTIVATE_ERROR, "设备激活失败，请联系客服");
		errors.put(ILLEGAL_DEVICE, "非法的设备");
		errors.put(ACTIVATED_DEVICE, "设备已激活");
		errors.put(FAULT_REPORTING_ERROR, "故障申报失败");
		errors.put(MAX_GOODS_COUNT, "您的食物数量已达上限，请删除部分后再添加。");
		errors.put(DUPLICATE_ALARM, "该食物的闹钟已存在");
		errors.put(MAX_GOODS_ALARM_COUNT, "您的食物闹钟数量已达上限，请删除一些闹钟后再添加");
		errors.put(DEVICE_BIND_FAIL, "设备绑定失败");
		errors.put(DEVICE_UNBIND_FAIL, "设备解绑失败");
	}

	public static Message newInstance(int code) {
		Message message = new Message();
		message.setCode(code);
		message.setErrorMsg(errors.get(code));
		return message;
	}

}