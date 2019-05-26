package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.domain.TeacherInfo;
import cn.edu.swpu.info.college_website.service.TeacherInfoService;
import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Permission(resourceKey = "teachers")
@RequestMapping(value = "/teachers", method = {RequestMethod.GET, RequestMethod.POST})
public class TeachersController {
    @Resource
    private TeacherInfoService teacherInfoService;

    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String main() {
        return "teachers/teachers";
    }

    @RequestMapping(value = "/teacher", method = {RequestMethod.GET})
    public String teacher() {
        return "teachers/teacher";
    }

    @RequestMapping(value = "/academic", method = {RequestMethod.GET})
    public String notice() {
        return "teachers/academic";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String login(HttpServletRequest request, HttpServletResponse response, Model view) {
        return "teachers/teachersLogin";
    }

    @RequestMapping("/teacherslogincheck")
    public String login(TeacherInfo teacherInfo) {
        //System.out.println(LoginName);
        //System.out.println(password);
        //System.out.println(studentInfo.toString());
        //List<UserInfo> userInfoList = userInfoService.selectUserInfoList(new UserInfo());
        if (teacherInfoService.check(teacherInfo)) {
            return "students/loginsucceed";
        } else
            return "students/loginfailed";
    }
}