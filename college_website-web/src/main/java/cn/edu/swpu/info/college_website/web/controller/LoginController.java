package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.domain.User;
import cn.edu.swpu.info.college_website.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    private UserService userService;
    @RequestMapping(value = "/studentslogincheck", method = {RequestMethod.POST})
    public String login1(User user) {
        User user1 = userService.checkUser(user);
        String role = "学生";
        if (user1 != null) {
            if(user1.getRole().equals(role))
            return "manager/loginsucceed";
        } else
            return "manager/loginfailed";
        return "manager/loginfailed";
    }
    @RequestMapping("/teacherslogincheck")
    public String login2(User user) {
        User user1 = userService.checkUser(user);
        String role1 = "管理员";
        String role2 = "院长";
        String role3 = "辅导员";

        if (user1 != null) {
            if(user1.getRole().equals(role1)){
                return "manager/addmessage";//管理员页面
            }
        } else{
            return  "普通教师页面";
        }
        return "manager/loginfailed";
    }
}
