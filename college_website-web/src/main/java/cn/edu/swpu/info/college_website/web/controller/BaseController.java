package cn.edu.swpu.info.college_website.web.controller;


import cn.edu.swpu.info.college_website.domain.exception.AppException;
import cn.edu.swpu.info.college_website.web.CustomDateEditor;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;


public class BaseController extends VelocitySupport {

    public final static String SUCCESS = "success";
    public final static String ERROR = "error";
    public final static Integer USER_MAX_NUM = 100;

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    public static String encodeStr(String tmpStr) {
        if (StringUtils.isNotBlank(tmpStr)) {
            try {
                return new String(tmpStr.getBytes("ISO-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        } else {
            return "";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
    }

    /**
     * 获取请求对象
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        return this.getRequest();
        //return ServletContextUtil.getContext().getRequest();
    }

    /**
     * 获取响应对象
     *
     * @return
     */
    protected HttpServletResponse getResponse() {
        //return ServletContextUtil.getContext().getResponse();
        return this.response;
    }

    /**
     * 跳转
     *
     * @param location
     */
    protected void sendRedirect(String location) {
        try {
            HttpServletResponse response = getResponse();
            response.sendRedirect(location);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    /**
     * 输出XML对象
     *
     * @param model
     */
    protected ModelAndView toXML(Object model) {
        //return write(ObjectUtils.object2xml(model), "text/xml");
        return null;
    }

    /**
     * 返回结果可指定模板
     *
     * @param layout :为Null则不包含布局模板
     * @param view
     * @param model
     * @return
     */
    protected ModelAndView toResult(String layout, String view, Object model) {
        ModelAndView mv = null;
        mv = toVM(layout, view, model);
        return mv;
    }

    /**
     * 返回结果,默认模板
     *
     * @param view
     * @param model
     * @return
     */
    protected ModelAndView toResult(String view, Object model) {
        if (model == null) {
            model = new Result();     // 当传入参数model为null时，创建一个新的Result对象;
        }
        return toResult(VELOCITY_DETAULT_LAYOUT, view, model);
    }

    /**
     * 返回结果,默认模板
     *
     * @param view
     * @return
     */
    protected ModelAndView toVM(String view) {
        Result result = new Result();
        return toVM(VELOCITY_DETAULT_LAYOUT, view, result);
    }

    /**
     * 返回结果,默认模板
     *
     * @param view
     * @param model
     * @return
     */
    protected ModelAndView toVM(String view, Object model) {
        if (model == null) {
            model = new Result();     // 当传入参数model为null时，创建一个新的Result对象;
        }
        // 向页面注入基础工具对象;
        return toVM(VELOCITY_DETAULT_LAYOUT, view, model);
    }

    /**
     * 返回错误页面(带框架);
     *
     * @param errorMsg
     * @return
     */
    protected ModelAndView toError(String errorMsg, boolean skipLayout) {
        Result result = new Result();
        result.addDefaultModel("errorMsg", errorMsg);
        // 向页面注入基础工具对象;
        if (skipLayout) {
            return toVM(null, "vm2.0/common/error", result);
        }
        return toVM(VELOCITY_DETAULT_LAYOUT, "vm2.0/common/error", result);
    }

    /**
     * 返回结果,跳过模板
     *
     * @param view
     * @param model
     * @return
     */
    protected ModelAndView toResultSkipLayout(String view, Object model) {
        if (model == null) {
            model = new Result();     // 当传入参数model为null时，创建一个新的Result对象;
        }
        // 向页面注入基础工具对象;
        return toResult(null, view, model);
    }

    /**
     * 直接将 ajax 输入到页面
     */
    protected void writeToPage(HttpServletResponse response, String str) {
        try {
            if (response != null) {// 在junit测试的时候会不显示.
                response.setHeader("Pragma", "No-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
                response.setContentType("text/plain;charset=UTF-8");
                PrintWriter writer = null;
                writer = response.getWriter();
                writer.write(str);
            }
        } catch (IOException e) {
        } finally {
        }

    }

    /* (non-Javadoc)
     * @see com.jd.smart_fridge_ops.web.controller.VelocitySupport#getDefaultContext()
     */
    @Override
    protected Map<String, Object> getDefaultContext() {
        // TODO Auto-generated method stub
        return null;
    }
}
