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
//    private UserService userInfoService;

    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String main(HttpServletRequest request, Model view) {
        try {
            return "overview/overview";
        } catch (Exception e) {
            LOG.error("失败:" + e.getMessage(), e);
            return "overview/overview";
        }
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model view) {
        try {
//            Page<User> page = new Page<User>();
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
//            User search = new User();
//            page = userInfoService.selectPage(search, page);
            //view.addAttribute("erp", PinContext.getPin());
//            view.addAttribute("listPage", page);
            return "overview/list";
        } catch (Exception e) {
            LOG.error("失败:" + e.getMessage(), e);
            return "overview/list";
        }
    }

    @RequestMapping(value = "/collegeLogo", method = {RequestMethod.GET})
    public String lab(HttpServletRequest request, Model view) {
        try {
            return "overview/collegeLogo";
        } catch (Exception e) {
            LOG.error("失败:" + e.getMessage(), e);
            return "overview/collegeLogo";
        }
    }

    @RequestMapping(value = "/collegeLeader", method = {RequestMethod.GET})
    public String leader(HttpServletRequest request, Model view) {
        try {
            return "overview/collegeLeader";
        } catch (Exception e) {
            LOG.error("失败:" + e.getMessage(), e);
            return "overview/collegeLeader";
        }
    }

//    @RequestMapping(value = "save", method = {RequestMethod.POST},produces = "application/json")
//    @ResponseBody
//    public Message save(User userInfo) throws Exception{
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
////                User.setCreateDate(new Date());
////                res = UserService.createUserInfo(User);
////            } else {
////                if(User.getCategoryType() != 0){
////                    User.setRadioType(-1);
////                }else {
////                    User.setCategoryId(-1);
////                }
////                res = UserService.updateUserInfo(User);
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
//            User condition = new User();
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
