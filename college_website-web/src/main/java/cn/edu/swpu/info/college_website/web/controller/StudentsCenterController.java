package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@Permission(resourceKey = "studentsCenter")
@RequestMapping(value = "/studentsCenter", method = {RequestMethod.GET, RequestMethod.POST})
public class StudentsCenterController {

    @RequestMapping(value = "",method = {RequestMethod.GET})
    public String main(){
        return "studentsCenter/studentsCenter";
    }

    @RequestMapping(value = "/extracurricularExperiment",method = {RequestMethod.GET})
    public String experiment(){
        return "studentsCenter/extracurricularExperiment";
    }

    @RequestMapping(value = "/projectTraining",method = {RequestMethod.GET})
    public String training(){
        return "studentsCenter/projectTraining";
    }

    @RequestMapping(value = "/teachingEvaluation",method = {RequestMethod.GET})
    public String evaluation(){
        return "studentsCenter/teachingEvaluation";
    }

    @RequestMapping(value = "/trainingPlatform",method = {RequestMethod.GET})
    public String platform(){
        return "studentsCenter/trainingPlatform";
    }
    @RequestMapping(value="login",method={RequestMethod.GET})
    public String login(){
        return "main/Login";
    }
}