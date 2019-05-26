package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.dao.StudentInfoDao;
import cn.edu.swpu.info.college_website.domain.StudentInfo;
import cn.edu.swpu.info.college_website.service.StudentInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("StudentInfoService")
public class StudentInfoServiceImpl implements StudentInfoService {
    @Resource
    private StudentInfoDao studentInfoDao;

    //    @Resource
//    private StudentInfoService studentInfoService;
    @Override
    public List<StudentInfo> selectStudentInfolist(StudentInfo studentInfo) {
        return studentInfoDao.selectEntryList(studentInfo);
    }

    @Override
    public Integer countStudentInfo(StudentInfo studentInfo) {
        return studentInfoDao.selectEntryListCount(studentInfo);
    }

    @Override
    public Integer addStudent(StudentInfo studentInfo) {
        return studentInfoDao.insertEntry(studentInfo);
    }

    //登陆验证
    @Override
    public boolean check(StudentInfo studentInfo) {


        //获取学生列表并验证
        List<StudentInfo> studentInfoList = studentInfoDao.selectEntryList(new StudentInfo());
        for (StudentInfo studentInfo3 : studentInfoList
        ) {
            if (studentInfo3.getStudentId() == studentInfo.getStudentId()) {
                return true;
            }

        }

        return false;
    }

//    @Override
//    public boolean studentLogin(String loginName, String password) {
//
//
//            List<StudentInfo> studentInfoList = studentInfoService.selectStudentInfolist(new StudentInfo());
//            boolean flag1 = false;
//            boolean flag2 = false;
//            boolean flag3 = false;
//            String name = "201731771247";
//            String pass = "123456";
//            if (loginName != null  ){
//                flag1 = true;
//            }else {
//                flag1 = false;
//            }
//
//            if (loginName == name) {
//
//                flag2 = true;
//
//            } else {
//                flag2 = false;
//            }
//            if (password == pass) {
//                flag3 = true;
//            } else {
//                flag3 = false;
//            }
//            if(flag1==true ){
//                return  true;
//            }
//
//            return false;
//    }

}
