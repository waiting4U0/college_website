package cn.edu.swpu.info.college_website.service;

import cn.edu.swpu.info.college_website.domain.StudentInfo;

import java.util.List;

public interface StudentInfoService {
    List<StudentInfo> selectStudentInfolist(StudentInfo studentInfo);
    Integer countStudentInfo(StudentInfo studentInfo);
    //boolean studentLogin(String loginName,String password);


}
