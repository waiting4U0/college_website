package cn.edu.swpu.info.college_website.dao.impl;

import cn.edu.swpu.info.college_website.dao.OpsFunctionDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.OpsFunction;
import org.springframework.stereotype.Repository;

/**
 * OpsFunctionDao 实现类
 */
@Repository("opsFunctionDao")
public class OpsFunctionDaoImpl extends BaseDaoImpl<OpsFunction, Long> implements OpsFunctionDao {
    private final static String NAMESPACE = "cn.edu.swpu.info.college_website.dao.OpsFunctionDao.";

    //返回本DAO命名空间,并添加statement
    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

}