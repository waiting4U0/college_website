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
    @Override
    public List<StudentInfo> selectStudentInfolist(StudentInfo studentInfo) {
        return studentInfoDao.selectEntryList(studentInfo);
    }

    @Override
    public Integer countStudentInfo(StudentInfo studentInfo) {
        return studentInfoDao.selectEntryListCount(studentInfo);
    }
}