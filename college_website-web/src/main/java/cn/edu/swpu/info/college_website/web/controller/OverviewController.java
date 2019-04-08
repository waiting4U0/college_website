package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.web.common.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
@Permission(resourceKey = "overview")
@RequestMapping(value = "/overview", method = {RequestMethod.GET, RequestMethod.POST})
public class OverviewController {
    private static final Logger LOG = LoggerFactory.getLogger(OverviewController.class);
//    @Resource
//    private UserInfoService userInfoService;

    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String main(HttpServletRequest request, Model view){
        try {
            return "overview/overview";
        }catch (Exception e){
            LOG.error("失败:" + e.getMessage(), e);
            return "overview/overview";
        }
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model view){
        try {
//            Page<UserInfo> page = new Page<UserInfo>();
//            int startIndex = 1;
//            int perPageSize = 20;
//            String startIndexStr = request.getParameter("startIndex");
//            String pageSize = request.getParameter("pageSize");
//
//            if (StringUtils.isNotBlank(startIndexStr)) {
//                startIndex = Integer.parseInt(startIndexStr);
//            }
//            if (StringUtils.isNotBlank(pageSize)) {
//                perPageSize = Integer.parseInt(pageSize);
//            }
//            page.setCurrentPage(startIndex);
//            page.setPageSize(perPageSize);
//
//            UserInfo search = new UserInfo();
//            page = userInfoService.selectPage(search, page);
            //view.addAttribute("erp", PinContext.getPin());
//            view.addAttribute("listPage", page);
            return "overview/list";
        }catch (Exception e){
            LOG.error("失败:" + e.getMessage(), e);
            return "overview/list";
        }
    }

    @RequestMapping(value = "/collegeLab", method = {RequestMethod.GET})
    public String lab(HttpServletRequest request, Model view){
        try {
            return "overview/collegeLab";
        }catch (Exception e){
            LOG.error("失败:" + e.getMessage(), e);
            return "overview/collegeLab";
        }
    }

    @RequestMapping(value = "/collegeLeader", method = {RequestMethod.GET})
    public String leader(HttpServletRequest request, Model view){
        try {
            return "overview/collegeLeader";
        }catch (Exception e){
            LOG.error("失败:" + e.getMessage(), e);
            return "overview/collegeLeader";
        }
    }

    @RequestMapping(value = "/institutionalSetup", method = {RequestMethod.GET})
    public String setup(HttpServletRequest request, Model view){
        try {
            return "overview/institutionalSetup";
        }catch (Exception e){
            LOG.error("失败:" + e.getMessage(), e);
            return "overview/institutionalSetup";
        }
    }

//    @RequestMapping(value = "save", method = {RequestMethod.POST},produces = "application/json")
//    @ResponseBody
//    public Message save(UserInfo userInfo) throws Exception{
//        Message msg;
//        try {
//            Long id = userInfo.getId();
//            long res = 1;
////            if (id == null || id == 0) {
////                if(userInfo.getCategoryType() != 0){
////                    userInfo.setRadioType(-1);
////                }else {
////                    userInfo.setCategoryId(-1);
////                }
////
////                UserInfo.setCreateDate(new Date());
////                res = UserInfoService.createUserInfo(UserInfo);
////            } else {
////                if(UserInfo.getCategoryType() != 0){
////                    UserInfo.setRadioType(-1);
////                }else {
////                    UserInfo.setCategoryId(-1);
////                }
////                res = UserInfoService.updateUserInfo(UserInfo);
////            }
//            msg = res > 0 ? Message.success() : Message.systemError();
//        } catch (IllegalArgumentException e) {
//            LOG.error("保存失败: " + e.getMessage(), e);
//            msg = Message.illegalArgument(e.getMessage());
//        } catch (Exception e) {
//            LOG.error("保存失败: " + e.getMessage(), e);
//            msg = Message.systemError();
//        }
//        return msg;
//    }
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ResponseBody
//    public Message del(@PathVariable Long id) throws Exception {
//        Message msg;
//        try {
//            UserInfo condition = new UserInfo();
//            condition.setId(id);
//            int res = userInfoService.remove(condition);
//            msg = res > 0 ? Message.success() : Message.systemError();
//        } catch (Exception e) {
//            LOG.error("失败:" + e.getMessage(), e);
//            msg = Message.systemError();
//        }
//        return msg;
//    }
}
