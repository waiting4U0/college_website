package cn.edu.swpu.info.college_website.common.tools;

import cn.edu.swpu.info.college_website.domain.User;

/**
 * @description: 验证密码
 * @author:源.Gao
 * @createtime: 2019-06-17 15:49
 **/

public class PasswordVerify {
    public static boolean PasswordVerify(User webUser, User dbUser){
        return isRightPassword(webUser,dbUser);
    }
    public static boolean isRightPassword(User webUser, User dbUser) {
        String webpassword = Md5Utils.Md5(webUser.getPassword());
        if(webpassword.equals(dbUser.getPassword())){
            return true;
        }
        return false;
    }
}
