package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.domain.Message;
import cn.edu.swpu.info.college_website.service.MessageService;
import cn.edu.swpu.info.college_website.web.IndexController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("message")
public class MessageController {
    public static Logger LOG = LoggerFactory.getLogger(IndexController.class);
    @Resource
    private MessageService messageService;
    private  String msg = "信息发布失败";
    @RequestMapping("/addOneMessge")
    public String messageManager(Message message){
        System.out.println(message);
        if(messageService.addOne(message)==1){
            return "manager/useraddsucc";
        }
        return "manager/useraddfailed";
    }
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public void index(HttpServletRequest request, HttpServletResponse response, Model view) {
        List<Message> messagesData = messageService.selectMessagelist(new Message());
//		//加载用户资源
//		view.addAttribute("erp", pin);
        view.addAttribute("messages", messagesData);
        //return "main/main";
    }

}

