package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Permission(resourceKey = "education")
@RequestMapping(value = "/education", method = {RequestMethod.GET, RequestMethod.POST})
public class EducationController {

    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String main() {
        return "education/education";
    }

    @RequestMapping(value = "/postgraduate", method = {RequestMethod.GET})
    public String postgraduate() {
        return "education/postgraduate";
    }

    @RequestMapping(value = "/resource", method = {RequestMethod.GET})
    public String resource() {
        return "education/resource";
    }

    @RequestMapping(value = "/training", method = {RequestMethod.GET})
    public String training() {
        return "education/training";
    }
}