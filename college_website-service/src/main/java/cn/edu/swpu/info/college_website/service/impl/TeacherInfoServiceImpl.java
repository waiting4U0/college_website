package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.dao.TeacherInfoDao;
import cn.edu.swpu.info.college_website.domain.TeacherInfo;
import cn.edu.swpu.info.college_website.service.TeacherInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("TeacherInfoService")
public class TeacherInfoServiceImpl implements TeacherInfoService {
    @Resource
    private TeacherInfoDao teacherInfoDao;
    @Override
    public List<TeacherInfo> selectTeacherInfolist(TeacherInfo teacherInfo) {
        return teacherInfoDao.selectEntryList(teacherInfo);
    }

    @Override
    public Integer countTeacherInfo(TeacherInfo teacherInfo) {
        return teacherInfoDao.selectEntryListCount(teacherInfo);
    }
}
