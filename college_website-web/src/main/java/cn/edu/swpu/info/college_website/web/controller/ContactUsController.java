package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@Permission(resourceKey = "contactUs")
@RequestMapping(value = "/contactUs", method = {RequestMethod.GET, RequestMethod.POST})
public class ContactUsController {

    @RequestMapping(value = "",method = {RequestMethod.GET})
    public String main(){
        return "contactUs/contactUs";
    }
}