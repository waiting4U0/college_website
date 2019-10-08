package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.domain.Message;
import cn.edu.swpu.info.college_website.service.MessageService;
import cn.edu.swpu.info.college_website.web.IndexController;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("message")
public class MessageController {
    public static Logger LOG = LoggerFactory.getLogger(IndexController.class);
    @Resource
    private MessageService messageService;

    @RequestMapping(value = "", method = {RequestMethod.GET})
    public void index(HttpServletRequest request, HttpServletResponse response, Model view) {
        List<Message> messagesData = messageService.selectMessagelist(new Message());
//		//加载用户资源
//		view.addAttribute("erp", pin);
        view.addAttribute("messages", messagesData);
        //return "main/main";
    }

    /**
     * 保存新闻
     *
     * @param message
     * @return
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public String messageSave(@RequestBody Message message) {
        System.out.println(message);
        // messageInfo.setMessageTitle();
//        String2Object object = new String2Object();
//        Message messageInfo = object.toObject(message);
        return messageService.addOne(message);
    }
    /**
     * 修改新闻审核状态
     *
     * @param status
     * @param id
     * @return
     */
    @RequestMapping(value = "/modify", method = {RequestMethod.GET})
    public String messageModify(@RequestParam String id, @RequestParam String status) {
        return messageService.changeOne(Long.parseLong(id),Integer.parseInt(status));
    }
    /**
     * 查找新闻记录
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/Query", method = {RequestMethod.GET})
    public Object messageQuery() {

        List<Message> messageList = messageService.selectMessagelist(new Message());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", messageList);
        //resultMap.put("code", 0);
        // resultMap.put("msg", "");
        resultMap.put("count", messageList.size());// 将其转换为JSON数据，并压入值栈返回
        return JSON.toJSON(resultMap);
    }
    /**
     * 删除新闻记录
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/remove", method = {RequestMethod.POST})
    @ResponseBody
    public Object messageRemove(@RequestParam String id,Message message) {
        //如果删除失败，返回  “删除失败”
        //long mid = id.
        // System.out.println("\n"+id+"\n");
        return messageService.removeOne(Long.parseLong(id));
    }


}

