/*
 * Copyright (c) 2015 www.jd.com All rights reserved.
 * 本软件源代码版权归京东智能集团所有,未经许可不得任意复制与传播.
 */
package cn.edu.swpu.info.college_website.web.common;

import com.alibaba.fastjson.JSONObject;
//import com.jd.smart_fridge_ops.common.tools.HttpHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <b>描述：</b> 统一身份认证 <br/>
 * @author<b>作者：</b> lishuangquan@jd.com<br/>
 * <b>时间：</b>2016/6/23 16:31<br/>
 * <b>Copyright (c)</b> 2015-2016京东智能-版权所有<br/>
 */
@Component("uimUtil")
public class UimUtil {

	private static final Logger LOG = LoggerFactory.getLogger(UimUtil.class);

	/* uim访问地址 */
	//@Value("${uim.url}")
	private String url;

	/* uim应用key */
	//@Value("${uim.appKey}")
	private String appKey;

	/* uim 版本 */
	//@Value("${uim.version}")
	private String version;

	/* uim token */
	//@Value("${uim.token}")
	private String token;

	/* uim 资源码 */
	//@Value("${uim.systemResCode}")
	private String systemResCode;

	public boolean checkUserAuth(String pin, String systemResCode) {

		//HttpHelper httpHelper = new HttpHelper();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = sf.format(new Date());

		String random = new Random().nextInt() + "";
		String sign = generate(appKey, token, timestamp, random);

		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("app_key", appKey);
		paramsMap.put("token", token);
		paramsMap.put("method", "uim.auth.res.checkUserAuth");
		paramsMap.put("v", version);
		paramsMap.put("timestamp", timestamp);
		paramsMap.put("format", "json");
		paramsMap.put("sign", sign);
		paramsMap.put("random", random);

		paramsMap.put("reqId", pin);
		paramsMap.put("resCode", systemResCode);

		try {
			//			HttpResponse httpResponse = httpHelper.doGet(url, paramsMap);
			//			HttpResponse httpResponse = httpHelper.doPost(url, paramsMap, paramsMap.toString());
			String result = doPost(url, paramsMap);
			if (StringUtils.isEmpty(result)) {
				LOG.error("uim请求返回空, pin:" + pin);
				return false;
			}
			LOG.info("uim验证返回结果，result: " + result);
			JSONObject jsonObject = JSONObject.parseObject(result);
			JSONObject responseObject = jsonObject.getJSONObject("authTicket.get.response");
			if (responseObject == null) {
				LOG.info("uim验证不通过。response为null,pin=" + pin);
				return false;
			}
			JSONObject authTicket = responseObject.getJSONObject("authTicket");
			if (authTicket == null) {
				LOG.info("uim验证不通过。authTicket is null,,pin=" + pin);
				return false;
			}


			String code = authTicket.getString("code");
			if ("200".equals(code)) {
				return true;
			} else {
				String msg = authTicket.getString("msg");
				LOG.info("uim验证不通过。code={}, msg={}.", new Object[]{code, msg});
				return false;
			}
		} catch (Exception e) {
			LOG.error("UIM用户权限检查异常。" + e.getMessage(), e);
		}
		return false;
	}

	public final static String CHARSET = "UTF-8";
	private final static String BLANK = "";

	public String doPost(String url, Map<String, String> params) {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost httpPost;
		try {
			//设置请求参数
			List<NameValuePair> nvps = null;
			if (params != null) {
				Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
				nvps = new ArrayList<NameValuePair>();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = it.next();
					NameValuePair nvp = new BasicNameValuePair(entry.getKey(), entry.getValue());
					nvps.add(nvp);
				}
			}

			httpPost = new HttpPost(url);
			if (null != nvps) {
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, CHARSET));
			}

			CloseableHttpResponse response = httpClient.execute(httpPost);
			byte[] bytes;
			try {
				HttpEntity entity = response.getEntity();
				bytes = EntityUtils.toByteArray(entity);
				if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
					String result = new String(bytes, CHARSET);
					LOG.error("HttpClient post request error :" + url + "  !reason:" + result);
					return BLANK;
				}
			} finally {
				response.close();
			}
			String result = new String(bytes, CHARSET);
			if (null != result && result.length() > 0) {
				return result;
			} else {
				return BLANK;
			}
		} catch (IOException e) {
			LOG.error("HttpClient post request error :" + url, e);
		} catch (Exception e) {
			LOG.error("HttpClient post request error :" + url, e);
		} finally {
			try {
				httpClient.close();
			} catch (Exception e) {
				LOG.error("HttpClient post request error :" + url, e);
			}
		}
		return BLANK;
	}


	/*
	   * 生成一个签名
	   *
	   * @param appKey    分发给业务系统的标识(相当于erp账号)
	   * @param token     appKey密码
	   * @param timestamp 时间戳
	   * @param random    随机数
	   * @return
	   */
	private static String generate(String appKey, String token, String timestamp, String random) {
		//将需要签名的参数进行字典排序
		String[] arrTmp = {appKey, token, timestamp, random};
		Arrays.sort(arrTmp);
		//将进行签名的排序后的参数直接拼接成一个字符串进行MD5加密
		StringBuilder sb = new StringBuilder();
		for (String str : arrTmp) {
			sb.append(str);
		}
		return md5(sb.toString());

	}

	/*
     * 使用MD5加密
     *
     * @param data
     * @return
    */
	private static String md5(String data) {
		StringBuilder result = new StringBuilder();
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			if (null == data || "".equals(data)) {
				return "";
			}
			// 将传入字符串转换为字符数组
			char[] chars = data.toCharArray();
			byte[] bytes = new byte[chars.length];
			// 将字符数组转化为字节数组
			for (int i = 0; i < chars.length; i++) {
				bytes[i] = (byte) chars[i];
			}
			// 使用指定的字节更新摘要，并获得密文
			byte[] md5Bytes = digest.digest(bytes);
			// 把密文转换成十六进制的字符串形式
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (16 > val) {
					result.append("0");
				}
				result.append(Integer.toHexString(val));
			}
		} catch (NoSuchAlgorithmException ex) {
			throw new InternalError("init MessageDigest error:" + ex.getMessage());
		}
		return result.toString();
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSystemResCode() {
		return systemResCode;
	}

	public void setSystemResCode(String systemResCode) {
		this.systemResCode = systemResCode;
	}
}