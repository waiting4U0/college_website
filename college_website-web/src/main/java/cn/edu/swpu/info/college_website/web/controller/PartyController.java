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

    @RequestMapping(value = "/ideologicalPolitics",method = {RequestMethod.GET})
    public String politics(){
        return "party/ideologicalPolitics";
    }

    @RequestMapping(value = "/membership",method = {RequestMethod.GET})
    public String membership(){
        return "party/membership";
    }

    @RequestMapping(value = "/organizationalStructure",method = {RequestMethod.GET})
    public String structure(){
        return "party/organizationalStructure";
    }

    @RequestMapping(value = "/partyNews",method = {RequestMethod.GET})
    public String news(){
        return "party/partyNews";
    }

}