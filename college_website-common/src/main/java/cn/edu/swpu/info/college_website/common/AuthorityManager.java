package cn.edu.swpu.info.college_website.common;

/**
 * 
 * <b>描述：</b> 权限验证<br/>
 * @author<b>作者：</b> weijunlong@jd.com <br/>
 * <b>时间：</b> 2016年1月27日 上午9:48:38 <br/>
 * <b>Copyright (c)</b> 2016京东智能-版权所有<br/>
 */
public interface AuthorityManager {

	/**
	 * 
	 * <b>描述：</b> 验证pin、feedId、accessKey是否匹配<br/>
	 * @author<b>创建人：</b> weijunlong@jd.com <br/>
	 * <b>创建时间：</b> 2016年1月27日 上午10:00:24 <br/>
	 * <b>修改人：</b> weijunlong@jd.com <br/>
	 * <b>时间：</b> 2016年1月27日 上午10:00:24 <br/>
	 * <b>修改内容：</b>  <br/>
	 */
	public void authorityValidation(String pin, Long feedId, String accessKey);

}
