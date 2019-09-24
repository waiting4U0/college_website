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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
public class IndexController {

	public static Logger LOG = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String index(HttpServletRequest request, HttpServletResponse response, Model view) throws ParseException {
		view.addAttribute("messages", messageService.selectIndexMessagelist());
		List<OpsFunction> functionData = PinContext.getFunctionData();
//		//加载用户资源
//		view.addAttribute("erp", pin);
		view.addAttribute("functions", functionData);
		return "main/main";
	}


	@Resource
	private MessageService messageService;
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String index1(HttpServletRequest request, HttpServletResponse response, Model view) throws ParseException {
		view.addAttribute("messages", messageService.selectIndexMessagelist());
		return "common/index";
	}

	@RequestMapping(value = "/banner", method = { RequestMethod.GET })
	public String banner(HttpServletRequest request, HttpServletResponse response, Model view) {
		return "common/banner";
	}

	@RequestMapping(value = "/imageLinks", method = { RequestMethod.GET })
	public String imageLinks(HttpServletRequest request, HttpServletResponse response, Model view) {
		return "common/imageLinks";
	}

	@RequestMapping(value = "/newslist", method = { RequestMethod.GET })
	public String newslist(HttpServletRequest request, HttpServletResponse response, Model view) throws ParseException {
		view.addAttribute("messages", messageService.selectIndexMessagelist());
		return "main/news/liebiao";
	}

	@RequestMapping(value = "/news/index", method = { RequestMethod.GET })
	public String newsindex(HttpServletRequest request, HttpServletResponse response, Model view) throws ParseException {

		System.out.println(request.getParameter("id"));
		view.addAttribute("id",request.getParameter("id"));
		view.addAttribute("messages", messageService.getMessagelist());
		List<OpsFunction> functionData = PinContext.getFunctionData();
//		//加载用户资源
//		view.addAttribute("erp", pin);
		view.addAttribute("functions", functionData);
		return "news/index";
	}


	@RequestMapping(value = "/news/liebiao", method = { RequestMethod.GET })
	public String newsliebiao(HttpServletRequest request, HttpServletResponse response, Model view) throws ParseException {
		System.out.println(request.getParameter("messageType"));
		view.addAttribute("messageType",request.getParameter("messageType"));
		System.out.println(request.getParameter("page"));
		view.addAttribute("page",request.getParameter("page"));
		view.addAttribute("messages", messageService.getMessagelist());
		List<OpsFunction> functionData = PinContext.getFunctionData();
//		//加载用户资源
//		view.addAttribute("erp", pin);
		view.addAttribute("functions", functionData);
		return "news/liebiao";
	}

	@RequestMapping(value = "/news/search", method = { RequestMethod.GET })
	@ResponseBody
	public String search() throws ParseException {
		return messageService.getMessageNameList();
	}

}
