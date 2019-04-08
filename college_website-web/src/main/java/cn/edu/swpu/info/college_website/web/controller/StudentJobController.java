package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Permission(resourceKey = "studentJob")
@RequestMapping(value = "/studentJob", method = {RequestMethod.GET, RequestMethod.POST})
public class StudentJobController {

    @RequestMapping(value = "",method = {RequestMethod.GET})
    public String main(){
        return "studentJob/studentJob";
    }

    @RequestMapping(value = "/rulesRegulations",method = {RequestMethod.GET})
    public String regulations(){
        return "studentJob/rulesRegulations";
    }

    @RequestMapping(value = "/studentUnion",method = {RequestMethod.GET})
    public String union(){
        return "studentJob/studentUnion";
    }

    @RequestMapping(value = "studyWorkerNotice",method = {RequestMethod.GET})
    public String notice(){
        return "studentJob/studyWorkerNotice";
    }
}
