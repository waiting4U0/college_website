package cn.edu.swpu.info.college_website.web;

import cn.edu.swpu.info.college_website.common.PinContext;
import cn.edu.swpu.info.college_website.domain.OpsFunction;
import cn.edu.swpu.info.college_website.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;


@Controller
@RequestMapping(value = "/manage", method = { RequestMethod.GET, RequestMethod.POST })
public class ManageController {
        @RequestMapping(value = "/main", method = { RequestMethod.GET })
        public String index(HttpServletRequest request, HttpServletResponse response, Model view) throws ParseException {
            return "manage/main";
        }

}
