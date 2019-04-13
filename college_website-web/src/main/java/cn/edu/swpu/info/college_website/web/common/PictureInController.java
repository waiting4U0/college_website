package cn.edu.swpu.info.college_website.web.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author YuanWang
 */
@Controller
@RequestMapping("/pictureIn")
public class PictureInController {

    private Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(value = "")
    @ResponseBody
    public String createFolw(HttpServletRequest request,
                             HttpServletResponse response, Model model) {
        // response.setContentType("image/*");
        FileInputStream fis = null;
        OutputStream os = null;
        String subpath = request.getParameter("subpath");
        Properties prop = null;
        String value = null;
        try {
            // 通过Spring中的PropertiesLoaderUtils工具类进行获取
            prop = PropertiesLoaderUtils.loadAllProperties("conf/pictureIn.properties");
            // 根据关键字查询相应的值
            value = prop.getProperty("pictureRootPath");
            fis = new FileInputStream(value +"/"+ subpath);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}