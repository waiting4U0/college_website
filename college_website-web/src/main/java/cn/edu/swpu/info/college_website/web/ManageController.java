package cn.edu.swpu.info.college_website.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;


@Controller
@RequestMapping(value = "/manage", method = { RequestMethod.GET, RequestMethod.POST })
public class ManageController {
        @RequestMapping(value = "/main", method = { RequestMethod.GET })
        public String index(HttpServletRequest request, HttpServletResponse response, Model view) throws ParseException {
            return "manage/main";
        }

}
