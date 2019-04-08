package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@Permission(resourceKey = "employment")
@RequestMapping(value = "/employment", method = {RequestMethod.GET, RequestMethod.POST})
public class EmploymentController {

    @RequestMapping(value = "",method = {RequestMethod.GET})
    public String main(){
        return "employment/employment";
    }

    @RequestMapping(value = "/careerGuidance",method = {RequestMethod.GET})
    public String guidance(){
        return "employment/careerGuidance";
    }

    @RequestMapping(value = "/innovationEntrepreneurship",method = {RequestMethod.GET})
    public String entrepreneurship(){
        return "employment/innovationEntrepreneurship";
    }

}