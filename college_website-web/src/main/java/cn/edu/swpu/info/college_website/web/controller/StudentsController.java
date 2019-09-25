package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.domain.User;
import cn.edu.swpu.info.college_website.domain.common.State;
import cn.edu.swpu.info.college_website.service.UserService;
import cn.edu.swpu.info.college_website.web.common.Permission;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Controller
@Permission(resourceKey = "students")
@RequestMapping(value = "/students", method = {RequestMethod.GET, RequestMethod.POST})
public class StudentsController {

    @Resource
    private UserService  userService;
    @Resource
    private DefaultKaptcha defaultKaptcha;

    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String main() {
        return "students/students";
    }

    @RequestMapping(value = "/news", method = {RequestMethod.GET})
    public String evaluation() {
        return "students/news";
    }

    @RequestMapping(value = "/generalize", method = {RequestMethod.GET})
    public String generalize() {
        return "students/generalize";
    }

    @RequestMapping(value = "/rules", method = {RequestMethod.GET})
    public String training() {
        return "students/rules";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String login() {
        return "students/studentsLogin";
    }

    @RequestMapping(value = "/view", method = {RequestMethod.POST})
    public String view() {
        return "students/studentView";
    }

    @RequestMapping(value = "/verify", method = {RequestMethod.POST})
    @ResponseBody
    public String loginVerify(HttpServletRequest httpServletRequest, Model view) {

        String name=httpServletRequest.getParameter("username");
        String password=httpServletRequest.getParameter("password");
        String tryCode = httpServletRequest.getParameter("verificationCode");
        String rightCode = (String) httpServletRequest.getSession().getAttribute("rightCode");
        System.out.println("\n"+"name:"+name+"\n");
        System.out.println("password:"+password+"\n");
        System.out.println("verificationCode:"+tryCode+"    *:"+rightCode+"\n");

        if (!tryCode.equals(rightCode)) {
            State msg= State.systemError();
            msg.setErrorMsg("验证码错误！");
            return JSONObject.toJSONString(msg);
        } else {
            User webuser=new User();
            webuser.setCode(name);
            webuser.setPassword(password);
            return userService.checkUser(webuser);
        }

    }

    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("rightCode", createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}