/*
 * Copyright (c) 2015 www.jd.com All rights reserved.
 * 本软件源代码版权归京东智能集团所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.web.common;

import java.util.List;

/**
 * <b>描述：</b>针对view 页面hash取值工具类 <br/>
 * @author<b>作者：</b> fengsonghao@jd.com<br/>
 * <b>时间：</b> 2016年1月12日 下午1:23:31 <br/>
 * <b>Copyright (c)</b> 2016京东智能-版权所有<br/>
 */
public class VelocityListTool {
	
    public VelocityListTool() {
        super();
      }
	public static String getListValue(List<String> list,Integer index){
		
		if(list==null) return "";
		//System.out.println(key+"String=="+map.toString());
		return list.get(index);
	}
	public static boolean listContainsValue(List<String> list,String value){
		
		if(list==null) return false;
		//System.out.println(key+"String=="+map.toString());
		return list.contains(value);
	}
	public static boolean listContainsValue(List<String> list,Long value){
		
		if(list==null) return false;
		//System.out.println(key+"String=="+map.toString());
		return list.contains(String.valueOf(value));
	}
	public static boolean listContainsValue(List<String> list,Integer value){
		
		if(list==null) return false;
		//System.out.println(key+"String=="+map.toString());
		return list.contains(String.valueOf(value));
	}
}
