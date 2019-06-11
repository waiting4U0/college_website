package cn.edu.swpu.info.college_website.common.tools;

import cn.edu.swpu.info.college_website.common.RException;
import org.apache.commons.lang.StringUtils;

/**
 * @description: 数据验证
 * @author:源.Gao
 * @createtime: 2019-06-10 10:00
 **/

public class dataVerifyTool {
    public static void isBlank(String str, String message){
        if(StringUtils.isBlank(str)){
            throw new RException(message);
        }

    }
    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RException(message);
        }
    }
}
