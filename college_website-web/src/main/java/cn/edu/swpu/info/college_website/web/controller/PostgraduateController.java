package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Permission(resourceKey = "postgraduate")
@RequestMapping(value = "/postgraduate", method = {RequestMethod.GET, RequestMethod.POST})
public class PostgraduateController {

    @RequestMapping(value = "",method = {RequestMethod.GET})
    public String main(){
        return "postgraduate/postgraduate";
    }

    @RequestMapping(value = "/guidancePostgraduate",method = {RequestMethod.GET})
    public String postgraduate(){
        return "postgraduate/guidancePostgraduate";
    }

    @RequestMapping(value = "/undergraduateTraining",method = {RequestMethod.GET})
    public String training(){
        return "postgraduate/undergraduateTraining";
    }

}