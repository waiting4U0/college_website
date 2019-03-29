
package cn.edu.swpu.info.college_website.web.common;

import java.util.List;

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
