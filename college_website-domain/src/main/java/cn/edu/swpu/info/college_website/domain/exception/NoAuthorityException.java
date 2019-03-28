/*
 * Copyright (c) 2015 www.jd.com All rights reserved.
 * 本软件源代码版权归京东智能集团所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.domain.exception;

/**
 * <b>描述：</b> <br/>
 * @author<b> 作者：</b> cdshenjian@jd.com<br/>
 * <b>时间：</b>2015/12/1 16:48<br/>
 * <b>Copyright (c)</b> 2015-2015京东智能-版权所有<br/>
 */
public class NoAuthorityException extends RuntimeException {
	public NoAuthorityException(String message) {
		super(message);
	}
}