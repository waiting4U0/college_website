package cn.edu.swpu.info.college_website.dao.impl;

import cn.edu.swpu.info.college_website.dao.base.BaseDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.MessageType;
import org.springframework.stereotype.Repository;

@Repository("MessageTypeDao")
public class MessageTypeDaoImpl extends BaseDaoImpl<MessageType,Long> implements BaseDao<MessageType, Long> {
    private final static String NAMESPACE = "cn.edu.swpu.info.college_website.dao.MessageTypeDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }
}
