/*
 * Copyright (c) 2015 www.jd.com All rights reserved.
 * 本软件源代码版权归京东智能集团所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.web.aspect;


import cn.edu.swpu.info.college_website.domain.OpsFunction;
import cn.edu.swpu.info.college_website.service.PermissionService;
import cn.edu.swpu.info.college_website.web.common.Permission;
import org.apache.commons.lang.StringUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * <b>描述：</b> <br/>
 * @author<b>作者：</b> lishuangquan@jd.com<br/>
 * <b>时间：</b>2016/9/27 10:28<br/>
 * <b>Copyright (c)</b> 2015-2016京东智能-版权所有<br/>
 */
@Aspect
public class PermissionAspect {
	public static final Logger LOG = LoggerFactory.getLogger(PermissionAspect.class);

	@Resource
	PermissionService permissionService;

//	@Around(value = "execution(public * com.jd.smart_fridge_ops.web..*.*(..))")
	@Around(value = "@within(cn.edu.swpu.info.college_website.web.common.Permission)")
	public Object check(ProceedingJoinPoint joinPoint) {
//		String userPin = LoginContext.getLoginContext().getPin();
//		if (StringUtils.isEmpty(userPin)) {
//			return "common/noPerm";
//		}
		Permission permission = joinPoint.getTarget().getClass().getAnnotation(Permission.class);
//		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//		Method method = signature.getMethod();
//		Permission permission = method.getAnnotation(Permission.class);

		try {
			if (permission == null) {
				return joinPoint.proceed();
			}
			String resourceKey = permission.resourceKey();
			LOG.info("权限检查，resourceKey:" + resourceKey);
			if (StringUtils.isEmpty(resourceKey)) {
				return "common/noPerm";
			}
//			List<OpsFunction> functionData = permissionService.getOpsFunctionsByPin(userPin);
//			if (CollectionUtils.isEmpty(functionData)) {
//				return "common/noPerm";
//			}
//			if (checkPermission(functionData, resourceKey)) {
//				return joinPoint.proceed();
//			}
		} catch (Throwable throwable) {
			LOG.error("权限检查异常", throwable);
			return "common/noPerm";
		}
		return "common/noPerm";
	}

	private boolean checkPermission(List<OpsFunction> functionData, String resourceKey) {
		for (OpsFunction opsFunction : functionData) {
			if (resourceKey.equals(opsFunction.getResourceKey())) {
				return true;
			}
		}

		return false;
	}

}