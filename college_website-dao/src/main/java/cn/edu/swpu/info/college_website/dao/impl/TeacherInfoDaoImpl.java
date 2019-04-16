package cn.edu.swpu.info.college_website.dao.impl;

import cn.edu.swpu.info.college_website.dao.base.BaseDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.TeacherInfo;
import org.springframework.stereotype.Repository;


@Repository("TeacherInfoDao")

public class TeacherInfoDaoImpl extends BaseDaoImpl<TeacherInfo,Long> implements BaseDao<TeacherInfo, Long> {

    private final static String NAMESPACE = "cn.edu.swpu.info.college_website.dao.TeacherInfoDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }
}
