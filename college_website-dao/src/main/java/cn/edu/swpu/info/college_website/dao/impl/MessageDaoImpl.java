package cn.edu.swpu.info.college_website.dao.impl;

import cn.edu.swpu.info.college_website.dao.base.BaseDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import cn.edu.swpu.info.college_website.domain.Message;
import org.springframework.stereotype.Repository;
@Repository("MessageDao")
public class MessageDaoImpl extends BaseDaoImpl<Message,Long>  implements BaseDao<Message, Long> {
    private final static String NAMESPACE = "cn.edu.swpu.info.college_website.dao.MessageDao.";
    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

}
