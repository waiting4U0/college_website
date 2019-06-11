package cn.edu.swpu.info.college_website.common.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.edu.swpu.info.college_website.domain.exception.AppException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;


public class HttpClientUtil {
	private final static Log log = LogFactory.getLog(HttpClientUtil.class);
	private static final String timeout = "60000";
	private static final int MIN_ERROR_REQUEST_SPEND_TIME = 200;

	/**
	 * 发送多参数的post请求
	 * 
	 * @param url
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static String sendHttpRequestByParams(String url,
			NameValuePair[] data) throws IOException {
		return sendHttpRequestByParams(url, data, false, 30000);
	}

	/**
	 * 发送多参数的post请求
	 * 
	 * @param url
	 * @param data
	 * @return
	 * @throws IOException
	 * @noEncode 中文参数是否encode过 true否 false 是
	 */
	public static String sendHttpRequestByParams(String url,
                                                 NameValuePair[] data, boolean noEncode) throws IOException {
		return sendHttpRequestByParams(url, data, noEncode, 30000);
	}

	/**
	 * 发送多参数的post请求
	 * 
	 * @param url
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static String sendHttpRequestByParams(String url,
                                                 NameValuePair[] data, boolean noEncode, int timeoutTime)
			throws IOException {
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		log.info(url);
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Connection", "close");
		// 如果中文参数没有 urlEncode 则需要设置头信息
		if (noEncode) {
			postMethod.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded;charset=utf-8");
		}
		postMethod.addParameters(data);

		// 使用系统提供的默认的恢复策略
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 定义超时
		httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(timeoutTime);
		httpClient.getHttpConnectionManager().getParams()
				.setSoTimeout(timeoutTime);
		// 定义一个输入流
		InputStream ins = null;
		// 定义文件流
		BufferedReader br = null;
		try {

			// 执行postMethod
			long timeBeforeExecute = System.currentTimeMillis();
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				log.error("method failure: " + postMethod.getStatusLine()
						+ ",url-----------" + url + "params:" + toJson(data));
			}

			long timeAfterExecute = System.currentTimeMillis();
			long executeSpendTime = timeAfterExecute - timeBeforeExecute;
			if (executeSpendTime > MIN_ERROR_REQUEST_SPEND_TIME) {
				log.warn("请求url：" + url + " 花费了太长时间：" + executeSpendTime
						+ "ms!");
			}
			// 使用getResponseBodyAsStream读取页面内容，这个方法对于目标地址中有大量数据需要传输是最佳的。
			ins = postMethod.getResponseBodyAsStream();
			String charset = postMethod.getResponseCharSet();
			if (charset.toUpperCase().equals("ISO-8859-1")) {
				charset = "utf-8";
			}
			// 按服务器编码字符集构建文件流，这里的CHARSET要根据实际情况设置
			br = new BufferedReader(new InputStreamReader(ins,
					postMethod.getResponseCharSet()));
			StringBuffer sbf = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sbf.append(line);
			}
			String result = new String(sbf.toString().getBytes(
					postMethod.getResponseCharSet()), charset);
			return result;
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题 \
			log.error("ulr------" + url + toJson(data));
			log.error("please check your http url address！", e);
			throw e;
		} catch (IOException e) {
			log.error("ulr------" + url + toJson(data));
			log.error("network exception is happening", e);
			throw e;
		} catch (Exception e) {
			log.error("ulr------" + url + toJson(data));
			log.error("Exception  is happening", e);
		} finally {
			// 关闭流，释放连接
			try {
				if (br != null) {
					br.close();
				}
				if (ins != null) {
					ins.close();
				}
				if (postMethod != null) {
					try {
						postMethod.releaseConnection();
						httpClient.getHttpConnectionManager()
								.closeIdleConnections(0);
					} catch (Exception e) {
						log.error("ulr------" + url + toJson(data));
						log.error("close http connetion failure", e);
					}
				}
			} catch (IOException e) {
				log.error("ulr------" + url + toJson(data));
				log.error("stream connection close failure", e);
			} catch (Exception e) {
				log.error("ulr------" + url + toJson(data));
				log.error("Exception", e);
			}
		}
		return null;
	}

	/**
	 * 将NameValuePair数组转换为json格式
	 * 
	 * @param data
	 *            NameValuePair 数组
	 * @return
	 */
	private static String toJson(NameValuePair[] data) {

		StringBuffer buffer = new StringBuffer("{");
		if (data != null) {
			for (NameValuePair nvp : data) {
				buffer.append(nvp.getName());
				buffer.append(":");
				buffer.append(nvp.getValue());
				buffer.append(",");
			}
		}
		buffer.append("}");

		return buffer.toString();
	}

	public static String sendHttpRequest(String url) {
		return sendHttpRequest(url, null);
	}

	public static String sendHttpRequest(String url, String param) {
		return sendHttpRequest(url, param, Integer.parseInt(timeout));
	}

	public static String sendHttpRequest(String url, String param,
			int timeoutTime) {
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		log.info(url);
		PostMethod getMethod = new PostMethod(url);
		getMethod.setRequestHeader("Connection", "close");
		if (StringUtils.isNotEmpty(param)) {
			getMethod.addParameter("param", param);
		}

		if (StringUtils.isNotEmpty(param)) {
			getMethod.addParameter("key", "666a8EN3oIijHY+KjS+2mg==");
		}
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 定义五秒钟的超时
		httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(timeoutTime);
		httpClient.getHttpConnectionManager().getParams()
				.setSoTimeout(timeoutTime);
		// getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,
		// 5000);
		// 定义一个输入流
		InputStream ins = null;
		// 定义文件流
		BufferedReader br = null;
		try {

			long timeBeforeExecute = System.currentTimeMillis();
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				log.error("method failure: " + getMethod.getStatusLine()
						+ ",url:-----------" + url + ",params:" + param);
				log.error("method failure: " + getMethod.getStatusLine()
						+ "-----------" + url + ",params:" + param);
			}
			long timeAfterExecute = System.currentTimeMillis();
			long executeSpendTime = timeAfterExecute - timeBeforeExecute;
			if (executeSpendTime > MIN_ERROR_REQUEST_SPEND_TIME) {
				log.error("请求url： " + url + " 花费了太长时间：" + executeSpendTime
						+ "ms");
			}
			// 使用getResponseBodyAsStream读取页面内容，这个方法对于目标地址中有大量数据需要传输是最佳的。
			// Thread.sleep(2000l);//测试需要，线程暂停10s
			ins = getMethod.getResponseBodyAsStream();
			String charset = getMethod.getResponseCharSet();
			if (charset.toUpperCase().equals("ISO-8859-1")) {
				charset = "utf-8";
			}

			// 按服务器编码字符集构建文件流，这里的CHARSET要根据实际情况设置
			br = new BufferedReader(new InputStreamReader(ins,
					getMethod.getResponseCharSet()));
			StringBuffer sbf = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sbf.append(line);
			}
			String result = new String(sbf.toString().getBytes(
					getMethod.getResponseCharSet()), charset);
			return result;
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题 \
			log.error("ulr------" + url + param);
			log.error("please check your http url address！", e);
		} catch (IOException e) {
			log.error("ulr------" + url + param);
			log.error("network exception is happening", e);
		} catch (Exception e) {
			log.error("ulr------" + url + param);
			log.error("Exception  is happening", e);
		} finally {
			// 关闭流，释放连接
			try {
				if (br != null) {
					br.close();
				}
				if (ins != null) {
					ins.close();
				}
				if (getMethod != null) {
					try {
						getMethod.releaseConnection();
						httpClient.getHttpConnectionManager()
								.closeIdleConnections(0);
					} catch (Exception e) {
						log.error("ulr------" + url + param);
						log.error("close http connetion failure", e);
					}
				}
			} catch (IOException e) {
				log.error("ulr------" + url + param);
				log.error("stream connection close failure", e);
			} catch (Exception e) {
				log.error("ulr------" + url + param);
				log.error("Exception", e);
			}
		}
		return null;
	}

	private static final String APPLICATION_JSON = "application/json";

	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
	
	private static final String CONTENT_ENCODING = "utf-8";
	/**
	 * 发送HTTPPost中的JSON:
	 * 
	 * @param url
	 * @param json
	 * @throws Exception
	 */
	public static String sendHttpRequestWithJSON(String url, JSON json, Map<String, String> headerMap) {
		if(json==null) json=new JSONObject();
		//log.info("HttpClientUtil.httpPostWithJSON:---ulr,json---" + url + json.toJSONString());
		if(url!=null&&!"".equalsIgnoreCase(url))
			url=url.trim();
		else {
			log.error("HttpClientUtil.httpPostWithJSON:---ulr=null");
			return "";
		}
		DefaultHttpClient httpClient=null;
		HttpPost httpPost=null	;
		try {	
			httpClient = getHttpClient();
			httpPost = new HttpPost(url);
			httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
	        StringEntity se = new StringEntity(json.toJSONString(),CONTENT_ENCODING);//解决中文乱码问题
	        se.setContentType(CONTENT_TYPE_TEXT_JSON);
	        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING,CONTENT_ENCODING));
	        
			httpPost.setEntity(se);
			if (headerMap != null) {
				for (String key : headerMap.keySet()) {
					httpPost.setHeader(key, headerMap.get(key));
				}
			}
			HttpResponse result= httpClient.execute(httpPost);
			if (result.getStatusLine().getStatusCode() != 200) {
				log.info("HttpClientUtil.httpPostWithJSON:---HttpResponse--StatusCode:"+result.getStatusLine().getStatusCode());
				return "";
			}
			String resData = EntityUtils.toString(result.getEntity());
			//log.info("HttpClientUtil.httpPostWithJSON:---HttpResponse--result--:"+resData);
			//log.info("HttpClientUtil.httpPostWithJSON:---StatusCode--:"+result.getStatusLine().getStatusCode());
			return resData;
		}catch(Exception e){
			log.error("HttpClientUtil.httpPostWithJSON:---ulr---json---" + url + json.toJSONString());
			log.error("HttpClientUtil.httpPostWithJSON:httpClient.execute failure", e);
			throw new AppException("网络异常!",e);//抛出异常，外部处理
		} finally {
			if(httpPost!=null)
				httpPost.abort();
			
		}
		



	}
	public static String getWithJSONResponse(String url, Map<String, String> headerMap) {
		log.info("HttpClientUtil.getWithJSONResponse:---ulr" + url );
		if(url!=null&&!"".equalsIgnoreCase(url))
			url=url.trim();
		else {
			log.error("HttpClientUtil.getWithJSONResponse:---ulr=null");
			return "";
		}
		DefaultHttpClient httpClient=null;
		HttpGet httpGet=null	;
		try {	
			httpClient = getHttpClient();
			httpGet = new HttpGet(url);
			httpGet.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

			if (headerMap != null) {
				for (String key : headerMap.keySet()) {
					httpGet.setHeader(key, headerMap.get(key));
				}
			}
			HttpResponse result= httpClient.execute(httpGet);
			if (result.getStatusLine().getStatusCode() != 200) {
				log.info("HttpClientUtil.getWithJSONResponse:---HttpResponse--StatusCode:"+result.getStatusLine().getStatusCode());
				return "";
			}
			String resData = EntityUtils.toString(result.getEntity());
			log.info("HttpClientUtil.getWithJSONResponse:---HttpResponse--result--:"+resData);
			log.info("HttpClientUtil.getWithJSONResponse:---StatusCode--:"+result.getStatusLine().getStatusCode());
			return resData;
		}catch(Exception e){
			log.error("HttpClientUtil.getWithJSONResponse:---ulr---json---" + url );
			log.error("HttpClientUtil.getWithJSONResponse:httpClient.execute failure", e);
			throw new AppException("网络异常!",e);//抛出异常，外部处理
		} finally {
			if(httpGet!=null)
				httpGet.abort();
			
		}
		



	}
	//加入http链接管理2015.4.27
	@SuppressWarnings("unused")
	private static BasicHttpParams params = new BasicHttpParams();
	
	private static PoolingClientConnectionManager conMgr = new PoolingClientConnectionManager();
	/**
	 * 最大连接数
	 */
	public final static int MAX_TOTAL_CONNECTIONS = 20;
	/**
	 * 获取连接的最大等待时间
	 */
	public final static int WAIT_TIMEOUT = 60000;
	/**
	 * 每个路由最大连接数
	 */
	public final static int CONNECTION_TIMEOUT = 2 * 1000;
	/**
	 * 连接超时时间
	 */
	public final static int SO_TIMEOUT = 2 * 1000;//设置请求超时2秒钟 根据业务调整
	/**
	 * 读取超时时间
	 */
	public final static int CONN_MANAGER_TIMEOUT = 500; //该值就是连接不够用的时候等待超时时间，一定要设置，而且不能太大 ()		 


	static {
		
		//设置连接超时时间		
		//设置等待数据超时时间2秒钟 根据业务调整
		//定义了当从ClientConnectionManager中检索ManagedClientConnection实例时使用的毫秒级的超时时间		
		//这个参数期望得到一个java.lang.Long类型的值。如果这个参数没有被设置，默认等于CONNECTION_TIMEOUT，因此一定要设置
		params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);
		params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);
		params.setLongParameter(ClientPNames.CONN_MANAGER_TIMEOUT, CONN_MANAGER_TIMEOUT);
		//在提交请求之前 测试连接是否可用
		params.setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, true);
		conMgr.setMaxTotal(MAX_TOTAL_CONNECTIONS); //设置整个连接池最大连接数 根据自己的场景决定
		//是路由的默认最大连接（该值默认为2），限制数量实际使用DefaultMaxPerRoute并非MaxTotal。
		//设置过小无法支持大并发(ConnectionPoolTimeoutException: Timeout waiting for connection from pool)，路由是对maxTotal的细分。
		conMgr.setDefaultMaxPerRoute(conMgr.getMaxTotal());//（目前只有一个路由，因此让他等于最大值）		 
		//另外设置http client的重试次数，默认是3次；当前是禁用掉（如果项目量不到，这个默认即可）		
	}

	public static DefaultHttpClient getHttpClient() {
		
		return new DefaultHttpClient(conMgr);
	}
	/**
	 * 容器关闭时候则关闭连接池
	 */
	public static void httpClientConnPoolShutdown(){
		log.info("---HttpClientUtil httpClientConnPoolShutdown---");
		try{
		if(null!=conMgr)
		  conMgr.shutdown();
		}catch(Exception e){
		  log.error("HttpClientUtil httpClientConnPoolShutdown close fail:", e);
		}
	}


}
