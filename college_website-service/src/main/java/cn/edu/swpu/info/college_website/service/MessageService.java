package cn.edu.swpu.info.college_website.service;

import cn.edu.swpu.info.college_website.domain.Message;

import java.text.ParseException;
import java.util.List;

public interface MessageService {
    List<Message> selectMessagelist(Message message);
    Integer countMssage(Message message);
    List<Message> selectIndexMessagelist() throws ParseException;
    List<Message> getMessagelist();

    Integer addOne(Message message);

    String getMessageNameList();
}
