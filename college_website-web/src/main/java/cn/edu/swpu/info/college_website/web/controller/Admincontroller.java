package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.domain.User;
import cn.edu.swpu.info.college_website.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class Admincontroller {
    @Resource
    private UserService userService;
    @RequestMapping("/adduser")
    public String add(User user){
        userService.addStudent(user);
        return "manager/showuser";
    }
    @RequestMapping("/updateuser")
    public String update(User user){
        userService.updateUserInfo(user);
        return "manager/showuser";
    }

    @RequestMapping("/showuser")
    public String show(HttpServletRequest request, HttpServletResponse response, Model view){
        List<User> userListdata = userService.selectUserInfoList(new User());
        view.addAttribute("users",userListdata);
        return "manager/showuser";
    }

}
