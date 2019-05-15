package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@Permission(resourceKey = "party")
@RequestMapping(value = "/party", method = {RequestMethod.GET, RequestMethod.POST})
public class PartyController {

    @RequestMapping(value = "",method = {RequestMethod.GET})
    public String main(){
        return "party/party";
    }

    @RequestMapping(value = "/activity",method = {RequestMethod.GET})
    public String activity(){
        return "party/activity";
    }

    @RequestMapping(value = "/news",method = {RequestMethod.GET})
    public String news(){
        return "party/news";
    }

    @RequestMapping(value = "/structure",method = {RequestMethod.GET})
    public String structure(){
        return "party/structure";
    }

    @RequestMapping(value = "/study",method = {RequestMethod.GET})
    public String study(){
        return "party/study";
    }

}