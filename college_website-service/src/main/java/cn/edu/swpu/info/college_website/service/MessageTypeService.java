package cn.edu.swpu.info.college_website.service;

import cn.edu.swpu.info.college_website.domain.MessageType;

import java.util.List;

public interface MessageTypeService {
    List<MessageType> selectMessageTypelist(MessageType messageType);
}
