/*
 * Copyright (c) 2015 www.jd.com All rights reserved.
 * 本软件源代码版权归京东智能集团所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.web.common;

import java.util.Map;

/**
 * <b>描述：</b>针对view 页面hash取值工具类 <br/>
 * @author<b>作者：</b> fengsonghao@jd.com<br/>
 * <b>时间：</b> 2016年1月12日 下午1:23:31 <br/>
 * <b>Copyright (c)</b> 2016京东智能-版权所有<br/>
 */
public class VelocityMapTool {
	
    public VelocityMapTool() {
        super();
      }
	public static String getMapValue(Map<String,String> map,String key){
		
		if(map==null) return "";
		//System.out.println(key+"String=="+map.toString());
		return map.get(key);
	}
	public static String getMapValue(Map<String,String> map,Integer key){
		if(map==null) return "";
		//System.out.println(key+"Integer=="+map.toString());
		return map.get(String.valueOf(key));
	}
	public static String getMapValue(Map<String,String> map,Long key){
		if(map==null) return "";
		//System.out.println(key+"Long=="+map.toString());
		return map.get(String.valueOf(key));
	}
}
