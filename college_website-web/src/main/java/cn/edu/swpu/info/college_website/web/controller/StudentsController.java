package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Permission(resourceKey = "students")
@RequestMapping(value = "/students", method = {RequestMethod.GET, RequestMethod.POST})
public class StudentsController {

    @RequestMapping(value = "",method = {RequestMethod.GET})
    public String main(){
        return "students/students";
    }

    @RequestMapping(value = "/evaluation",method = {RequestMethod.GET})
    public String evaluation(){
        return "students/evaluation";
    }

    @RequestMapping(value = "/generalize",method = {RequestMethod.GET})
    public String generalize(){
        return "students/generalize";
    }

    @RequestMapping(value="/training",method={RequestMethod.GET})
    public String training(){
        return "students/training";
    }
    @RequestMapping(value = "/login", method = { RequestMethod.POST })
    public String login(HttpServletRequest request, HttpServletResponse response, Model view) {
        return "main/login";
    }
}