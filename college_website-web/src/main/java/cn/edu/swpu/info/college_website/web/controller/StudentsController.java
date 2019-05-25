package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Permission(resourceKey = "students")
@RequestMapping(value = "/students", method = {RequestMethod.GET, RequestMethod.POST})
public class StudentsController {

    @RequestMapping(value = "",method = {RequestMethod.GET})
    public String main(){
        return "students/students";
    }

    @RequestMapping(value = "/news",method = {RequestMethod.GET})
    public String evaluation(){
        return "students/news";
    }

    @RequestMapping(value = "/generalize",method = {RequestMethod.GET})
    public String generalize(){
        return "students/generalize";
    }

    @RequestMapping(value="/rules",method={RequestMethod.GET})
    public String training(){
        return "students/rules";
    }
    @RequestMapping(value = "/login", method = { RequestMethod.POST })
    public String login() {
        return "students/studentsLogin";
    }
//    @Resource
//    private StudentInfoService studentInfoService;
//    @RequestMapping(value = "/studentsmanager",method = {RequestMethod.POST})
//    public String studentsmanager(){
//
////        StudentInfo studentInfo = new StudentInfo();
////        studentInfo.setStudentName("李家");
////        studentInfo.setTelephoneNumber("1890790794");
////        studentInfo.setStudentId("201731771245");
////        studentInfo.setSex("男");
////        studentInfo.setEmail("14528625@qq.com");
////        //System.out.println(studentInfo.toString());
////        studentInfoService.addStudent(studentInfo);
//        return "students/addStudent";
//    }
//    @RequestMapping(value = "/addStudent",method = {RequestMethod.POST})
//    public String addStudent(StudentInfo studentInfo){
//        System.out.println(studentInfo.toString());
//        studentInfoService.addStudent(studentInfo);
//
//        return "students/succ";
//    }
//    @Resource
////    private StudentInfoService studentInfoService;
////    @RequestMapping("/studentslogincheck")
////    public String login(UserInfo userInfo){
//////        System.out.println(LoginName);
//////        System.out.println(password);
////        System.out.println(userInfo.toString());
////        //List<UserInfo> userInfoList = userInfoService.selectUserInfoList(new UserInfo());
////        if(studentInfoService.check(userInfo)){
////            return "main/loginsucceed";
////        }
////        else
////            return "main/loginfailed";
////    }
}