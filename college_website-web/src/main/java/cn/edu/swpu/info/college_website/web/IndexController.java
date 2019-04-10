package cn.edu.swpu.info.college_website.web;

import cn.edu.swpu.info.college_website.common.PinContext;
import cn.edu.swpu.info.college_website.domain.OpsFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
public class IndexController {

	public static Logger LOG = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String index(HttpServletRequest request, HttpServletResponse response, Model view) {
		List<OpsFunction> functionData = PinContext.getFunctionData();
//		//加载用户资源
//		view.addAttribute("erp", pin);
		view.addAttribute("functions", functionData);
		return "main/main";
	}
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String index1(HttpServletRequest request, HttpServletResponse response, Model view) {
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
}
