package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.dao.MessageTypeDao;
import cn.edu.swpu.info.college_website.domain.MessageType;
import cn.edu.swpu.info.college_website.service.MessageTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MessageTypeServiceImpl implements MessageTypeService {
    @Resource
    private MessageTypeDao messageTypeDao;
    @Override
    public List<MessageType> selectMessageTypelist(MessageType messageType) {
        return messageTypeDao.selectEntryList(messageType);
    }
}
