package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.dao.MessageDao;
import cn.edu.swpu.info.college_website.domain.Message;
import cn.edu.swpu.info.college_website.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

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

    @Override
    public List<Message> selectIndexMessagelist() throws ParseException {
        List<Message> messagesData = messageDao.selectEntryList(new Message());
		//选取新闻前三条，学工通知前四条，党团快讯前四条信息组成加载首页时的信息
		List<Message> messages = new LinkedList<>();
		int a=0,b=0,c=0;
		for (Message x : messagesData
			 ) {
			if(x.getMessageType()== 2 && a<3){
				messages.add(x);
				a++;
			}
			if(x.getMessageType()== 3 && b<4){
				messages.add(x);
				b++;
			}
			if(x.getMessageType()== 1 && c<4){
				messages.add(x);
				c++;
			}
		}
        return messages;
    }
}
