package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@Permission(resourceKey = "teachersCenter")
@RequestMapping(value = "/teachersCenter", method = {RequestMethod.GET, RequestMethod.POST})
public class TeachersCenterController {

    @RequestMapping(value = "",method = {RequestMethod.GET})
    public String main(){
        return "teachersCenter/teachersCenter";
    }

    @RequestMapping(value = "/experimentalAcceptance",method = {RequestMethod.GET})
    public String acceptance(){
        return "teachersCenter/experimentalAcceptance";
    }

    @RequestMapping(value = "/releaseNotice",method = {RequestMethod.GET})
    public String notice(){
        return "teachersCenter/releaseNotice";
    }

    @RequestMapping(value = "/studentManagement",method = {RequestMethod.GET})
    public String management(){
        return "teachersCenter/studentManagement";
    }

}