package cn.edu.swpu.info.college_website.dao.impl;

import cn.edu.swpu.info.college_website.dao.StudentInfoDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.StudentInfo;
import org.springframework.stereotype.Repository;

@Repository("StudentInfoDao")
public class StudentInfoDaoImpl extends BaseDaoImpl<StudentInfo,Long> implements StudentInfoDao {
    private final static String NAMESPACE = "cn.edu.swpu.info.college_website.dao.StudentInfoDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }
}
