package cn.edu.swpu.info.college_website.web;

import cn.edu.swpu.info.college_website.domain.*;
import cn.edu.swpu.info.college_website.domain.common.MessageTips;
import cn.edu.swpu.info.college_website.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/manage", method = { RequestMethod.GET, RequestMethod.POST })
public class ManageController {
    @Resource
    private MessageService messageService;
    @Resource
    private OpsFunctionService opsFunctionService;
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
        @RequestMapping(value = "/main", method = { RequestMethod.GET })
        public String index(HttpServletRequest request, HttpServletResponse response, Model view) throws ParseException {
            return "manage/main";
        }

    //后台管理页面跳转
    @RequestMapping("/login")
    public String loginIndex() {
        return "manage/admin/index/login";
    }
    @RequestMapping(value = "/administerLoginCheck",method = {RequestMethod.POST})
    public String login3(User user, Model view){
        String admin = userService.checkUser(user);
        if(admin == null ){
            view.addAttribute("error","ccccccc");
            return "manage/admin/index/login";
        }else {
            view.addAttribute("user",admin);
            view.addAttribute("Info", MessageTips.success());
            return "manage/admin/index/manageindex";
        }
    }

    //后台管理页面跳转
    @RequestMapping(value = "/welcome", method = {RequestMethod.GET})
    public String welcome() {
        return "manage/admin/index/welcome";
    }

    @RequestMapping(value = "/article-add", method = {RequestMethod.GET})
    public String articleListAdd() {
        return "manage/admin/index/article-add";
    }

    @RequestMapping(value = "/article-list/article-detail", method = {RequestMethod.GET})
    public String articleListDetail() {
        return "manage/admin/index/article-detail";
    }

    @RequestMapping(value = "/article-list", method = {RequestMethod.GET})
    public String articleList(Model view,HttpServletResponse rep) {
        view.addAttribute("messageData", messageService.selectMessagelist(new Message()));
        List<Message> messageList = messageService.selectMessagelist(new Message());
        // List<Customer> cstList = customerService.selectCustomerList();
        //JSON.toJSONString(messageList);
        //前台通过key值获得对应的value值
        JSONObject jobj = new JSONObject();
        //数据状态的字段名称，默认：code
        jobj.put("code",0);
        //成功的状态码，默认：0
        jobj.put("msg", "");
        jobj.put("count",messageList.size());
        jobj.put("data",messageList);
        view.addAttribute("res",jobj.toJSONString());
        return "manage/admin/index/article-list";
    }

    @RequestMapping(value = "/student-list", method = {RequestMethod.GET})
    public String studentsList(Model view) {
        return "manage/admin/index/student-list";
    }
    @RequestMapping(value = "/role-list", method = {RequestMethod.GET})
    public String roleList(Model view) {
        return "manage/admin/index/role-list";
    }
    @RequestMapping(value = "/permission-list", method = {RequestMethod.GET})
    public String permisionList(Model view) {
        return "manage/admin/index/permission-list";
    }
    @RequestMapping(value = "/user-add.vm", method = {RequestMethod.GET})
    public String studentsAdd() {
        return "manage/admin/index/user-add";
    }
    @RequestMapping(value = "/teacher-list", method = {RequestMethod.GET})
    public String teachersList(Model view) {
        //view.addAttribute("teacherData", userService.getAllTeacherList(new User()));
        return "manage/admin/index/teacher-list";
    }
    @RequestMapping(value = "/admin-info", method = {RequestMethod.GET})
    public String adminInfo() {
        return "manage/admin/index/admin-info";
    }
    @RequestMapping(value = "/danye-detail", method = {RequestMethod.POST})
    public String danyeListDetail() {
        return "manage/admin/index/danye-detail";
    }


    @RequestMapping(value = "/email", method = {RequestMethod.GET})
    public String email() {
        return "manage/admin/index/email";
    }

    @RequestMapping(value = "/email/email-write", method = {RequestMethod.GET})
    public String emailWrite() {
        return "manage/admin/index/email-write";
    }

    @RequestMapping(value = "/system", method = {RequestMethod.GET})
    public String system() {
        return "manage/admin/index/system";
    }

    /**得到权限列表
     *
     * @param view
     * @return

    @RequestMapping(value = "/getPermissionList", method = {RequestMethod.GET})
    public Object getPermissionList(Model view) {
        List<Permissions> permissionsList = permissionService.getPermissionList(new Permissions());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", permissionsList);
        resultMap.put("code", 0);
        resultMap.put("msg", "");
        resultMap.put("count", permissionsList.size());// 将其转换为JSON数据，并压入值栈返回
        return JSON.toJSON(resultMap);
        // return null;
    }
     */
    @RequestMapping(value = "/getRoleList", method = {RequestMethod.GET})
    public Object getRoleList(Model view) {
        List<Role> rolesList = roleService.getRoleList(new Role());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", rolesList);
        resultMap.put("code", 0);
        resultMap.put("msg", "");
        resultMap.put("count", rolesList.size());// 将其转换为JSON数据，并压入值栈返回
        return JSON.toJSON(resultMap);
        // return null;
    }

    @RequestMapping(value = "/getStudentList", method = {RequestMethod.GET})
    public Object studentQuery() {
        List<User> userList = userService.getAllStudentList(new User());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", userList);
        resultMap.put("code", 0);
        resultMap.put("msg", "");
        resultMap.put("count", userList.size());// 将其转换为JSON数据，并压入值栈返回
        return JSON.toJSON(resultMap);
    }
    @RequestMapping(value = "/getTeacherList", method = {RequestMethod.GET})
    public Object teacherQuery() {
        List<User> teacherList =  userService.getAllTeacherList(new User());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", teacherList);
        resultMap.put("code", 0);
        resultMap.put("msg", "");
        resultMap.put("count", teacherList.size());// 将其转换为JSON数据，并压入值栈返回
        return JSON.toJSON(resultMap);
        //return JSON.toJSON(layUiData.getLayData(teacherList));
    }





    @RequestMapping(value = "/danye-list", method = {RequestMethod.GET})
    public String danyeList(Model view) {
        return "manage/admin/index/danye-list";
    }
    @RequestMapping(value = "/getWebPageList", method = {RequestMethod.GET})
    public Object getpageList() {
        List<OpsFunction> opsFunctionList =  opsFunctionService.selectEntryList(new OpsFunction());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", opsFunctionList);
        resultMap.put("code", 0);
        resultMap.put("msg", "");
        resultMap.put("count", opsFunctionList.size());// 将其转换为JSON数据，并压入值栈返回
        return JSON.toJSON(resultMap);
    }


    @RequestMapping(value = "/logincheck", method = {RequestMethod.POST})
    public String index2(HttpServletRequest request, HttpServletResponse response, User user) {
        System.out.println(user.getName());
        return "manage/admin/index/index";
    }

    @RequestMapping(value = "/main/message", method = {RequestMethod.GET})
    public String message(HttpServletRequest request, HttpServletResponse response, Model view) throws ParseException {
        return "manage/messageadd";
    }

    @RequestMapping(value = "/main/test", method = {RequestMethod.GET})
    public String test(HttpServletRequest request, HttpServletResponse response, Model view) throws ParseException {
        view.addAttribute("teacherList", userService.getAllTeacherList(new User()));
        return "manage/teacherlist";
    }
    @RequestMapping(value = "/main/student", method = {RequestMethod.GET})
    public String studentlist(HttpServletRequest request, HttpServletResponse response, Model view) throws ParseException {
        view.addAttribute("studentList", userService.getAllStudentList(new User()));
        return "manage/studentlist";
    }

    @RequestMapping(value = "/main/messageList", method = {RequestMethod.GET})
    public String messageList(HttpServletRequest request, HttpServletResponse response, Model view) throws ParseException {
        view.addAttribute("messageeList", messageService.selectIndexMessagelist());
        return "manage/messagelist";
    }











}
