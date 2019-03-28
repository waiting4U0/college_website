/*
 * Copyright (c) 2015 www.jd.com All rights reserved.
 * 本软件源代码版权归京东智能集团所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.web.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <b>描述：</b> <br/>
 * @author<b>作者：</b> lishuangquan@jd.com<br/>
 * <b>时间：</b>2016/12/14 11:27<br/>
 * <b>Copyright (c)</b> 2015-2016京东智能-版权所有<br/>
 */
public class DateSearchUtil {

	private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	/***
	 * 获取各维度分析数据的查询时间条件为空时，默认往前31天数据
	 * @return 31天前Str date
	 */
	public static String getStartDateString() {
		GregorianCalendar calendar =new GregorianCalendar();
		Date endTime=new Date();
		calendar.setTime(endTime);
		calendar.add(Calendar.DATE, -31);

		return sf.format(calendar.getTime());
	}

	public static String getEndDateString() {
		//查询昨天为止的数据
		GregorianCalendar calendar =new GregorianCalendar();
		Date endTime=new Date();
		calendar.setTime(endTime);
		calendar.add(Calendar.DATE, -1);
		return sf.format(calendar.getTime());
	}


}