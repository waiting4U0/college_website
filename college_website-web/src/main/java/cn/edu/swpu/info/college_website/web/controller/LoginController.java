package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.domain.User;
import cn.edu.swpu.info.college_website.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

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
                return "manage/managemain";//管理员页面
            }
        } else{
            return  "manager/loginfailed";
        }
        return "manager/loginfailed";
    }
    @RequestMapping(value = "/main/test", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, HttpServletResponse response, Model view) throws ParseException {
        return "manage/teacherlist";
    }

    @RequestMapping(value = "/main/message", method = { RequestMethod.GET })
    public String message(HttpServletRequest request, HttpServletResponse response, Model view) throws ParseException {
        return "manage/messagemanage";
    }
    @RequestMapping(value = "/main/student", method = { RequestMethod.GET })
    public String student(HttpServletRequest request, HttpServletResponse response, Model view) throws ParseException {
        return "manage/studentlist";
    }
}
