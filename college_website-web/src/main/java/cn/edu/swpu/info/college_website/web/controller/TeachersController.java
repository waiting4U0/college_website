package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@Permission(resourceKey = "teachers")
@RequestMapping(value = "/teachers", method = {RequestMethod.GET, RequestMethod.POST})
public class TeachersController {

    @RequestMapping(value = "",method = {RequestMethod.GET})
    public String main(){
        return "teachers/teachers";
    }

    @RequestMapping(value = "/teacher",method = {RequestMethod.GET})
    public String teacher(){
        return "teachers/teacher";
    }

    @RequestMapping(value = "/academic",method = {RequestMethod.GET})
    public String notice(){
        return "teachers/academic";
    }

}