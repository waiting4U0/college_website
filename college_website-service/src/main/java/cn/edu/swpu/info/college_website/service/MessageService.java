package cn.edu.swpu.info.college_website.service;

import cn.edu.swpu.info.college_website.domain.Message;

import java.text.ParseException;
import java.util.List;

public interface MessageService {
    List<Message> selectMessagelist(Message message);
    Integer countMssage(Message message);
    List<Message> selectIndexMessagelist() throws ParseException;
    List<Message> getMessagelist();


    //管理员操作，增删改查
    String addOne(Message message);
    String removeOne(long id);
    String changeOne(long id, Integer status);
    List<Message> findOne(Message message);

    String getMessageNameList();
}
