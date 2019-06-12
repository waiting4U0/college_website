package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.service.UserService;
import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@Permission(resourceKey = "students")
@RequestMapping(value = "/students", method = {RequestMethod.GET, RequestMethod.POST})
public class StudentsController {

    @Resource
    private UserService  userService;

    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String main() {
        return "students/students";
    }

    @RequestMapping(value = "/news", method = {RequestMethod.GET})
    public String evaluation() {
        return "students/news";
    }

    @RequestMapping(value = "/generalize", method = {RequestMethod.GET})
    public String generalize() {
        return "students/generalize";
    }

    @RequestMapping(value = "/rules", method = {RequestMethod.GET})
    public String training() {
        return "students/rules";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String login() {
        return "manager/studentslogin";
    }
}