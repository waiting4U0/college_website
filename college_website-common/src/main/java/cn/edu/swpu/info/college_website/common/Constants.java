/*
 * Copyright (c) 2015 www.jd.com All rights reserved.
 * 本软件源代码版权归京东智能集团所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.common;

/**
 * <b>描述：</b> <br/>
 * @author<b>作者：</b> fengsonghao@jd.com<br/>
 * <b>时间：</b> 2015年12月21日 下午4:50:47 <br/>
 * <b>Copyright (c)</b> 2015京东智能-版权所有<br/>
 */
public class Constants {
	/*
	 * 通用列族名
	 */
    public static final String HBASE_COLUMN_FAMILY_NAME = "f";
    /*
	 * 个人中心留言信息表
	 * 【当前时间的秒】=SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(当前时间字符串).getTime()/1000
	 * 【时间戳】计算方式：timestamp = Long.Max - 【当前时间的秒】
	 * key=冰箱feedId+【时间戳】+留言用户ID(根据pin获得)
	 */
    public static final  String HBASE_FRIDGE_PERSONAL_LEAVE_MESSAGE = "fridge_personal_leave_message";
    /*
     * 关怀信息表
     */
    public static final  String HBASE_FRIDGE_PERSONAL_CAREOF_MESSAGE = "fridge_personal_careof_message";
    /*
	 * 图像结果识别表
	 */
    public static final  String HBASE_FRIDGE_PIC_RECOGNITION_RESULT= "fridge_pic_recognition_result";
    /**
     * 登录相关
     */
	public static final String ADMIN_NOT_EXIST = "请联系管理员添加ERP账号才能登陆";
	public static final String ADMIN_ERP_ERROR = "ERP账户输入有误，请重新输入！";
	public static final String ADMIN_ERP_PWD_ERROR = "密码错误，登录失败！";
	public static final String MSG_OK = "ok";
    
    /*
     * 过期提醒
     */
	public static final String EXPIRATION_REMINDER_JOB = "Expiration_Reminder_Job";
	public static final String PUSH_EXPIRATION_REMINDER_JOB = "Push_Expiration_Reminder_Job";
	public static final String DELETE_HAS_EXPIRED_REMINDER_JOB = "Delete_Has_Expired_Reminder_Job";
	
	/*
	 * 久放提醒
	 */
	public static final String PLACED_GOODS_JOB = "Placed_Goods_Job";
	
	//goods_alarm final key will like lsga_142396749947110968
	public static final String GOODS_ALARM_PREFIX = "lsga_";
	
	//recognitionResult final key will like lsrr_142396749947110968l
	public static final String RECOGNITION_RESULT_PREFIX = "lsrr_";
	
	//白天天气信息key
	public static final String SMART_WEATHER = "smart_weather";
	
	//应用升级key
	public static final String APP_WHOLE_UPGRADE = "app_whole_upgrade_";
	
	public static final String APP_PART_UPGRADE = "app_part_upgrade_";
	
	public static final String APP_UPGRADE_PADSN_LIST ="app_upgrade_padsn_list_";
	
	public static final String APP_BLACK_UPGRADE = "app_black_upgrade_";
	
	public static final String APP_BLACK_PADSN_LIST ="app_black_padsn_list_";
	
	
	//系统升级key
	public static final String SYSTEM_UPGRADE = "system_upgrade";
	
	public static final String SYSTEM_UPGRADE_PADSN_LIST = "system_upgrade_padsn_list";
	
}
