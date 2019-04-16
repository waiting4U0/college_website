package cn.edu.swpu.info.college_website.service;

import cn.edu.swpu.info.college_website.domain.TeacherInfo;

import java.util.List;

public interface TeacherInfoService {
    List<TeacherInfo> selectTeacherInfolist(TeacherInfo teacherInfo);
    Integer countTeacherInfo(TeacherInfo teacherInfo);
}
