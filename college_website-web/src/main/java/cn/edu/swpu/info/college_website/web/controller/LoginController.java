package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.domain.UserInfo;
import cn.edu.swpu.info.college_website.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("main/login")
public class LoginController {
    @Resource
    private UserInfoService userInfoService;
    @RequestMapping("")
    public String login(@RequestParam(value = "loginName",required = true) String LoginName, String password, Model model){
        List<UserInfo> userInfoList = userInfoService.selectUserInfoList(new UserInfo());
        System.out.println(LoginName);
        System.out.println(password);
        String name = "201731771247";
        String pass = "123456";
//        UserInfo userInfo = new UserInfo(LoginName,password);
//        for (UserInfo userInfo2 : userInfoList){
//            System.out.println(userInfo.getName());
//        }
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
//        if (userInfo != null  ){
//            flag1 = true;
//        }else {
//            flag1 = false;
//        }
//        for (LoginName ==){
//
            if(LoginName == name ){

                flag2 = true;

            }else {
                flag2 = false;
            }
            if(password == pass) {
                flag3 = true;
            }else {
                flag3 = false;
            }
//        }
if(flag2 && flag3){
    return "main/loginsucceed";
}

        return null;
    }
}
