package cn.edu.swpu.info.college_website.web.interceptor;

import cn.edu.swpu.info.college_website.common.PinContext;
import cn.edu.swpu.info.college_website.dao.OpsFunctionDao;
import cn.edu.swpu.info.college_website.dao.OpsRoleDao;
import cn.edu.swpu.info.college_website.dao.OpsRoleFunctionDao;
import cn.edu.swpu.info.college_website.dao.OpsUserDao;
import cn.edu.swpu.info.college_website.domain.OpsFunction;
import cn.edu.swpu.info.college_website.domain.OpsRoleFunction;
import cn.edu.swpu.info.college_website.domain.OpsUser;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import com.jd.common.web.LoginContext;

public class AdminLoginTicketInterceptor implements org.springframework.web.servlet.HandlerInterceptor {
    @Resource
    private OpsUserDao opsUserDao;


    @Resource
    private OpsRoleFunctionDao opsRoleFunctionDao;

    @Resource
    private OpsFunctionDao opsFunctionDao;

    @Resource
    private OpsRoleDao opsRoleDao;

    private final static Log LOG = LogFactory.getLog(AdminLoginTicketInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws IOException {
        try {
			// 从单点登录的上下文中取出pin并赋值原程序的上下文中，以便各功能正常使用;
            String pin = "";//LoginContext.getLoginContext().getPin();
            LOG.info("用户【"+ pin +"】尝试登录");
            PinContext.setPin(pin);
            OpsUser user = new OpsUser();
            user.setErp(PinContext.getPin());
            List<OpsUser> users = opsUserDao.selectEntryList(user);
            if (users == null || users.size() == 0) {
                response.sendRedirect("http://ssa.jd.com/sso/logout");
                return false;
            }



            /*//目前用户仅能拥有一个角色
            Long roleId = users.get(0).getRoleId();*/
            //修改为一个用户多角色，modify by weijunlong@jd.com 2017-05-15
            List<Long> roleIds = new ArrayList<Long>();
            for(OpsUser opsUser : users){
            	roleIds.add(opsUser.getRoleId());
            }
            
            List<OpsRoleFunction> opsRoleFunctionList = getOpsRoleFunctionsByRoleIds(roleIds);
            if (CollectionUtils.isEmpty(opsRoleFunctionList)) {
				LOG.error("角色未赋资源,roleIds=" + roleIds.toString());
                response.sendRedirect("http://ssa.jd.com/sso/logout");
                return false;
            }


            //获取用户所有权限信息
            List<Long> functionIdList = new ArrayList<Long>();
            for (OpsRoleFunction roleFunction : opsRoleFunctionList) {
                functionIdList.add(roleFunction.getFunctionId());
            }
            Long[] functionIds = functionIdList.toArray(new Long[functionIdList.size()]);
            List<OpsFunction> opsFunctionList = opsFunctionDao.selectEntryList(functionIds);
            PinContext.setFunctionIds(opsFunctionList);
            LOG.info("【"+ pin +"】登录后台");
        } catch (Exception e) {
            LOG.error("【拦截器】设置pin和查询赋值用户及角色失败 ", e);
            response.sendRedirect("http://ssa.jd.com/sso/logout");
            return false;
        }
        return true;
    }

    /**
     * 
     * <b>描述：</b> <br/>
     * @author<b>创建人：</b> weijunlong@jd.com <br/>
     * <b>创建时间：</b> 2017年5月15日 上午11:11:43 <br/>
     * <b>修改人：</b> weijunlong@jd.com <br/>
     * <b>时间：</b> 2017年5月15日 上午11:11:43 <br/>
     * <b>修改内容：</b>  <br/>
     */
	private List<OpsRoleFunction> getOpsRoleFunctionsByRoleIds(List<Long> roleIds) {
		List<OpsRoleFunction> opsRoleFunctionList = new ArrayList<OpsRoleFunction>();
		for (Long roleId : roleIds) {
			OpsRoleFunction opsRoleFunction = new OpsRoleFunction();
			opsRoleFunction.setRoleId(roleId);
			List<OpsRoleFunction> list = opsRoleFunctionDao.selectEntryList(opsRoleFunction);
			if (CollectionUtils.isNotEmpty(list)) {
				opsRoleFunctionList.addAll(list);
			}
		}
		return opsRoleFunctionList;

	}

	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //super.afterCompletion(request, response, handler, ex);
        PinContext.remove();
    }
}
