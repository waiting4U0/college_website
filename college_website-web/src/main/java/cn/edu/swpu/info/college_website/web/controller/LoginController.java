package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.domain.UserInfo;
import cn.edu.swpu.info.college_website.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("students/login")
public class LoginController {

    //    @Resource
//    private StudentInfoService studentInfoService;
    @Resource
    private UserInfoService userInfoService;

    @RequestMapping("/userlogin")
    public String login(UserInfo userInfo) {
//        System.out.println(LoginName);
//        System.out.println(password);
        System.out.println(userInfo.toString());
        //List<UserInfo> userInfoList = userInfoService.selectUserInfoList(new UserInfo());
        if (userInfoService.check(userInfo)) {
            return "students/loginsucceed";
        } else
            return "students/loginfailed";
    }
}
