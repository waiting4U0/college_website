package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Permission(resourceKey = "teacherIntroduce")
@RequestMapping(value = "/teacherIntroduce", method = {RequestMethod.GET, RequestMethod.POST})
public class TeacherIntroduceController {

    @RequestMapping(value = "",method = {RequestMethod.GET})
    public String main(){
        return "teacherIntroduce/teacherIntroduce";
    }

    @RequestMapping(value = "/academicExchange",method = {RequestMethod.GET})
    public String exchange(){
        return "teacherIntroduce/academicExchange";
    }

    @RequestMapping(value = "/teachers",method = {RequestMethod.GET})
    public String teachers(){
        return "teacherIntroduce/teachers";
    }

}