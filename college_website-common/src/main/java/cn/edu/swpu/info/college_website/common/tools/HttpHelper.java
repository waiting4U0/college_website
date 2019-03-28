package cn.edu.swpu.info.college_website.common.tools;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class HttpHelper implements HttpRequester {
    private static final Logger LOG = LoggerFactory.getLogger(HttpHelper.class);
    
	private String defaultContentEncoding;
	

	public HttpHelper() {
		this.defaultContentEncoding = "UTF-8";
	}
	
	/**
	 * 默认的响应字符集
	 */
	public String getDefaultContentEncoding() {
		return this.defaultContentEncoding;
	}

	/**
	 * 设置默认的响应字符集
	 */
	public void setDefaultContentEncoding(String defaultContentEncoding) {
		this.defaultContentEncoding = defaultContentEncoding;
	}

    @Override
    public HttpResponse doPost(String url, Map<String, String> headers, String requestBody) throws IOException {
        return doPost(url, headers, requestBody, DEFAULT_CONNECTION_TIMEOUT, DEFAULT_SO_TIMEOUT);
    }

    @Override
    public HttpResponse doPost(String url, Map<String, String> headers, String requestBody, int connTimeout, int soTimeout) throws IOException {
        PostMethod method = new PostMethod(url);
        setHeaders(method, headers);
        RequestEntity entity = new ByteArrayRequestEntity(requestBody.getBytes());
        method.setRequestEntity(entity);
        method.addRequestHeader("Content-Type","application/json;charset=UTF-8");
        return doService(method, connTimeout, soTimeout);
    }

    @Override
    public HttpResponse doGet(String url, Map<String, String> headers) throws IOException {
        return doGet(url, headers, DEFAULT_CONNECTION_TIMEOUT, DEFAULT_SO_TIMEOUT);
    }

    @Override
    public HttpResponse doGet(String url, Map<String, String> headers, int connTimeout, int soTimeout) throws IOException {
        GetMethod method = new GetMethod(url);
        setHeaders(method, headers);

        return doService(method, connTimeout, soTimeout);
    }

    private HttpResponse doService(HttpMethod method, int connTimeout, int soTimeout) throws IOException {
        HttpClient client = new HttpClient();
        HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams();
        managerParams.setConnectionTimeout(connTimeout);
        managerParams.setSoTimeout(soTimeout);
        HttpResponse response = new HttpResponse();

        response.code = client.executeMethod(method);
        response.responseBody = method.getResponseBodyAsString();
        LOG.info("http response code:" + response.code);
        LOG.info("http response responseBody:" + response.responseBody);
        Header[] headers = method.getResponseHeaders();
        if (headers != null && headers.length > 0) {
            response.responseHeaders = new HashMap<String, String>();
            for (Header header : headers) {
                response.responseHeaders.put(header.getName(), header.getValue());
            }
        }

        return response;
    }

    private void setHeaders(HttpMethod method, Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            Set<Map.Entry<String, String>> entrySet = headers.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                method.addRequestHeader(entry.getKey(), entry.getValue());
            }
        }
    }

	@Override
	public HttpResponse sendGet(String urlString) throws IOException {
		return this.send(urlString, "GET", null, null);
	}
	
	/**
	 * 发送HTTP请求
	 * 
	 * @param urlString
	 * @return 响映对象
	 * @throws IOException
	 */
	private HttpResponse send(String urlString, String method, Map<String, String> parameters, Map<String, String> propertys)
			throws IOException {
		HttpURLConnection urlConnection = null;

		if ("GET".equalsIgnoreCase(method) && parameters != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : parameters.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append(parameters.get(key));
				i++;
			}
			urlString += param;
		}
		URL url = new URL(urlString);
		urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestMethod(method);
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);

		if (propertys != null)
			for (String key : propertys.keySet()) {
				urlConnection.addRequestProperty(key, propertys.get(key));
			}

		if ("POST".equalsIgnoreCase(method) && parameters != null) {
			StringBuffer param = new StringBuffer();
			for (String key : parameters.keySet()) {
				param.append("&");
				param.append(key).append("=").append(parameters.get(key));
			}
			urlConnection.getOutputStream().write(param.toString().getBytes());
			urlConnection.getOutputStream().flush();
			urlConnection.getOutputStream().close();
		}

		return this.makeContent(urlString, urlConnection);
	}

	/**
	 * 得到响应对象
	 *
	 * @param urlConnection
	 * @return 响应对象
	 * @throws IOException
	 */
	private HttpResponse makeContent(String urlString, HttpURLConnection urlConnection) throws IOException {
		HttpResponse httpResponser = new HttpResponse();
		try {
			InputStream in = urlConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
			httpResponser.contentCollection = new Vector<String>();
			StringBuffer temp = new StringBuffer();
			String line = bufferedReader.readLine();
			while (line != null) {
				httpResponser.contentCollection.add(line);
				temp.append(line).append("\r\n");
				line = bufferedReader.readLine();
			}
			bufferedReader.close();

			String ecod = urlConnection.getContentEncoding();
			if (ecod == null)
				ecod = this.defaultContentEncoding;

			httpResponser.urlString = urlString;

			httpResponser.defaultPort = urlConnection.getURL().getDefaultPort();
			httpResponser.file = urlConnection.getURL().getFile();
			httpResponser.host = urlConnection.getURL().getHost();
			httpResponser.path = urlConnection.getURL().getPath();
			httpResponser.port = urlConnection.getURL().getPort();
			httpResponser.protocol = urlConnection.getURL().getProtocol();
			httpResponser.query = urlConnection.getURL().getQuery();
			httpResponser.ref = urlConnection.getURL().getRef();
			httpResponser.userInfo = urlConnection.getURL().getUserInfo();

			httpResponser.content = new String(temp.toString().getBytes(), ecod);
			httpResponser.contentEncoding = ecod;
			httpResponser.code = urlConnection.getResponseCode();
			httpResponser.message = urlConnection.getResponseMessage();
			httpResponser.contentType = urlConnection.getContentType();
			httpResponser.method = urlConnection.getRequestMethod();
			httpResponser.connectTimeout = urlConnection.getConnectTimeout();
			httpResponser.readTimeout = urlConnection.getReadTimeout();

			return httpResponser;
		} catch (IOException e) {
			throw e;
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}
	}

}
