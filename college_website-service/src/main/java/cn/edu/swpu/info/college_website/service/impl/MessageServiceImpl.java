package cn.edu.swpu.info.college_website.service.impl;

import cn.edu.swpu.info.college_website.dao.MessageDao;
import cn.edu.swpu.info.college_website.domain.Message;
import cn.edu.swpu.info.college_website.domain.common.MessageTips;
import cn.edu.swpu.info.college_website.domain.common.State;
import cn.edu.swpu.info.college_website.service.MessageService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.LinkedList;
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

	@Override
	public List<Message> getMessagelist(){

		List<Message> messagesData = messageDao.selectEntryList(new Message());
		return messagesData;
	}

	@Override
	public String getMessageNameList() {
    	List<Message> messageList = messageDao.selectEntryList(new Message());
    	List<String> nameList = new LinkedList<>();
		for (Message me: messageList
			 ) {
			nameList.add(me.getMessageTitle());
		}
		State msg= State.success();
		if(nameList!=null){
			msg.setData(nameList);
			return JSONObject.toJSONString(msg);
		}else {
			msg.setErrorMsg("获取数据失败");
			return JSONObject.toJSONString(msg);
		}
	}
	@Override
	public String addOne(Message message) {
		//System.out.println(message.getMessageImg());
		MessageTips msg = MessageTips.success();
		try {
			if(message.equals(null)){
				msg.setErrorMsg("添加失败");
				msg.setCode(-1);
			}else if(messageDao.insertEntry(message) == 1){
				msg.setErrorMsg("添加成功");
				msg.setCode(200);
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return JSONObject.toJSONString(msg);
	}

	@Override
	public String changeOne(long id,Integer status) {
		MessageTips msg = MessageTips.success();
		Message message = new Message();
		message.setId(id);
		message.setStatus(status);
		if(messageDao.updateByKey(message) == 0){
			msg.setErrorMsg("修改失败");
			msg.setCode(-1);
		}else if(messageDao.updateByKey(message) == 1){
			msg.setErrorMsg("修改成功");
			msg.setCode(200);
		}
		return JSONObject.toJSONString(msg);
	}
	@Override
	public String removeOne(long id) {
		MessageTips msg = MessageTips.success();
		Message message = new Message();
		message.setId(id);
		//System.out.println(id);
		if (message.getId().equals(null)){
			msg.setErrorMsg("删除失败");
			msg.setCode(-1);
		}else if(messageDao.deleteByKey(message)==1){
			msg.setErrorMsg("删除成功");
			msg.setCode(200);
		}
		return JSONObject.toJSONString(msg);
	}



	@Override
	public List<Message> findOne(Message message) {
		return messageDao.selectEntryList(message);
	}
}
