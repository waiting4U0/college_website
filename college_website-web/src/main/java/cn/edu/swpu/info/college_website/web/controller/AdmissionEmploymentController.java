package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@Permission(resourceKey = "admissionEmployment")
@RequestMapping(value = "/admissionEmployment", method = {RequestMethod.GET, RequestMethod.POST})
public class AdmissionEmploymentController {

    @RequestMapping(value = "",method = {RequestMethod.GET})
    public String main(){
        return "admissionEmployment/admissionEmployment";
    }

    @RequestMapping(value = "/generalize",method = {RequestMethod.GET})
    public String generalize(){
        return "admissionEmployment/generalize";
    }

    @RequestMapping(value = "/graduateEmployment",method = {RequestMethod.GET})
    public String graduateEmployment(){
        return "admissionEmployment/graduateEmployment";
    }

    @RequestMapping(value = "/innovation",method = {RequestMethod.GET})
    public String innovation(){
        return "admissionEmployment/innovation";
    }

    @RequestMapping(value = "/major",method = {RequestMethod.GET})
    public String major(){
        return "admissionEmployment/major";
    }

    @RequestMapping(value = "/postgraduateAdmission",method = {RequestMethod.GET})
    public String postgraduateAdmission(){
        return "admissionEmployment/postgraduateAdmission";
    }

    @RequestMapping(value = "/undergraduateAdmission",method = {RequestMethod.GET})
    public String undergraduateAdmission(){
        return "admissionEmployment/undergraduateAdmission";
    }

}