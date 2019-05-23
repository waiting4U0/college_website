package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.service.StudentInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("main/login")
public class LoginController {

    @Resource
    private StudentInfoService studentInfoService;
    @RequestMapping("")
    public String login(@RequestParam(value = "loginName",required = true) String LoginName, String password, Model model){
        System.out.println(LoginName);
        System.out.println(password);
        boolean flag =false;
        if(studentInfoService.studentLogin(LoginName,password)==true){
            flag = true;
        }
        if(flag = true){
            return "main/loginsucceed";
        }else {
            return  null;
        }


    }
}
