package cn.edu.swpu.info.college_website.web.common;

import java.util.Map;

public class VelocityMapTool {

    public VelocityMapTool() {
        super();
    }

    public static String getMapValue(Map<String, String> map, String key) {

        if (map == null) return "";
        //System.out.println(key+"String=="+map.toString());
        return map.get(key);
    }

    public static String getMapValue(Map<String, String> map, Integer key) {
        if (map == null) return "";
        //System.out.println(key+"Integer=="+map.toString());
        return map.get(String.valueOf(key));
    }

    public static String getMapValue(Map<String, String> map, Long key) {
        if (map == null) return "";
        //System.out.println(key+"Long=="+map.toString());
        return map.get(String.valueOf(key));
    }
}
