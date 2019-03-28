/*
 * Copyright (c) 2015 www.jd.com All rights reserved.
 * 本软件源代码版权归京东智能集团所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.web.interceptor;

//import com.jd.common.web.LoginContext;

import cn.edu.swpu.info.college_website.web.common.UimUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <b>描述：</b> 用户权限拦截器 <br/>
 * @author<b>作者：</b> lishuangquan@jd.com<br/>
 * <b>时间：</b>2016/6/24 16:36<br/>
 * <b>Copyright (c)</b> 2015-2016京东智能-版权所有<br/>
 */
public class UserAuthInterceptor implements HandlerInterceptor {

	private final static Logger LOG = LoggerFactory.getLogger(UserAuthInterceptor.class);

	@Resource
	UimUtil uimUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {


//		LoginContext loginContext = LoginContext.getLoginContext();
//		String erp = loginContext.getPin();				//erp账号

		boolean hasAuth = uimUtil.checkUserAuth("", uimUtil.getSystemResCode());
		/**?
		if (hasAuth) {
			return true;
		} else {

			response.sendRedirect("http://ssa.jd.com/sso/logout");

			return false;
		}
		**/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

	}

}