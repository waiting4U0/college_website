/*
 * Copyright (c) 2015 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.web;



import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * 首页，登录，注销处理
 * @author J-ONE
 * @since 2015-11-16
 */
@Controller
@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
public class IndexController {

	public static Logger LOG = LoggerFactory.getLogger(IndexController.class);



	/**
	 * 
	 * <b>描述：</b> 处理只输入域名时候的跳转<br/>
	 * @author<b>创建人：</b> fengsonghao@jd.com<br/>
	 * <b>创建时间：</b> 2016年1月15日 上午11:00:27 <br/>
	 * <b>修改人：</b> Administrator<br/>
	 * <b>时间：</b> 2016年1月15日 上午11:00:27 <br/>
	 * <b>修改内容：</b>  <br/>
	 *@param request
	 *@param view
	 *@return
	 */
	@RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST })
	public String index1(HttpServletRequest request, Model view) {
//		LoginContext loginContext = LoginContext.getLoginContext();
//		String pin = loginContext.getPin();
		String pin ="123";
		if (StringUtils.isNotEmpty(pin)) {
			//已经登录
			return "redirect:/main";
		}
		//跳转到登录页面
		return "main/main";
	}

	/**
	 * 
	 * <b>描述：</b> 处理只输入域名+/index时候的跳转 <br/>
	 * @author<b>创建人：</b> fengsonghao@jd.com<br/>
	 * <b>创建时间：</b> 2016年1月15日 上午11:02:17 <br/>
	 * <b>修改人：</b> Administrator<br/>
	 * <b>时间：</b> 2016年1月15日 上午11:02:17 <br/>
	 * <b>修改内容：</b>  <br/>
	 *@param request
	 *@param view
	 *@return
	 */
	@RequestMapping(value = "index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index2(HttpServletRequest request, Model view) {
		view.addAttribute("contextPath", request.getContextPath());

		return "redirect:/"
				+ "";
	}

	/**
	 *
	 * <b>描述：</b>注销 <br/>
	 * @author<b>创建人：</b> fengsonghao@jd.com<br/>
	 * <b>创建时间：</b> 2016年1月15日 上午11:05:20 <br/>
	 * <b>修改人：</b> Administrator<br/>
	 * <b>时间：</b> 2016年1月15日 上午11:05:20 <br/>
	 * <b>修改内容：</b>  <br/>
	 *@param response
	 *@param view
	 *@return
	 */
//	@RequestMapping(value = "main/logout", method = { RequestMethod.GET, RequestMethod.POST })
//	public String logout(HttpServletResponse response, Model view) {
//		cookieUtils.deleteCookie(response, WebConstants.COOKIENAME);
//		return "main/login";
//	}

	/**
	 * 
	 * <b>描述：</b>进入主界面 <br/>
	 * @author<b>创建人：</b> fengsonghao@jd.com<br/>
	 * <b>创建时间：</b> 2016年1月15日 上午11:06:15 <br/>
	 * <b>修改人：</b> Administrator<br/>
	 * <b>时间：</b> 2016年1月15日 上午11:06:15 <br/>
	 * <b>修改内容：</b>  <br/>
	 *@param request
	 *@param response
	 *@param view
	 *@return
	 */
	@RequestMapping(value = "main", method = { RequestMethod.GET })
	public String index(HttpServletRequest request, HttpServletResponse response, Model view) {

//		String pin = PinContext.getPin();
//		List<OpsFunction> functionData = PinContext.getFunctionData();
//		//加载用户资源
//		view.addAttribute("erp", pin);
//		view.addAttribute("functions", functionData);
		return "main/main";
	}

	/**
	 * 
	 * <b>描述：</b>  主界面的首页面<br/>
	 * @author<b>创建人：</b> fengsonghao@jd.com<br/>
	 * <b>创建时间：</b> 2016年1月15日 上午11:06:33 <br/>
	 * <b>修改人：</b> Administrator<br/>
	 * <b>时间：</b> 2016年1月15日 上午11:06:33 <br/>
	 * <b>修改内容：</b>  <br/>
	 *@param request
	 *@param response
	 *@param view
	 *@return
	 */
	@RequestMapping(value = "main/firstPage", method = { RequestMethod.GET, RequestMethod.POST })
	//TODO 此处用权限拦截来控制
	public String firstPage(HttpServletRequest request, HttpServletResponse response, Model view) {
//		LoginContext loginContext = LoginContext.getLoginContext();
//		String pin = loginContext.getPin();
		String pin ="123";
		if (StringUtils.isBlank(pin)) {
			return "redirect:/";
		}
		view.addAttribute("erp", pin);
		return "main/main";
	}

}
