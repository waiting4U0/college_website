package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.dao.MessageDao;
import cn.edu.swpu.info.college_website.domain.Message;
import cn.edu.swpu.info.college_website.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;
    @Override
    public List<Message> selectMessagelist(Message message) {
        return messageDao.selectEntryList(message);
    }

    @Override
    public Integer countMssage(Message message) {
        return messageDao.selectEntryListCount(message);
    }
}
