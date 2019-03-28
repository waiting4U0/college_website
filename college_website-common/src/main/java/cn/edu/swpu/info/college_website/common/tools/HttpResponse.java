package cn.edu.swpu.info.college_website.common.tools;

import java.util.Map;
import java.util.Vector;

public class HttpResponse {

	public int code;
	public String responseBody;
	public Map<String, String> responseHeaders;
	String urlString;

	int defaultPort;

	String file;

	String host;

	String path;

	int port;

	String protocol;

	String query;

	String ref;

	String userInfo;

	String contentEncoding;

	String content;

	String contentType;

	String message;

	String method;

	int connectTimeout;

	int readTimeout;

	Vector<String> contentCollection;

	public int getCode() {
		return code;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public Map<String, String> getResponseHeaders() {
		return responseHeaders;
	}

	public String getUrlString() {
		return urlString;
	}

	public int getDefaultPort() {
		return defaultPort;
	}

	public String getFile() {
		return file;
	}

	public String getHost() {
		return host;
	}

	public String getPath() {
		return path;
	}

	public int getPort() {
		return port;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getQuery() {
		return query;
	}

	public String getRef() {
		return ref;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public String getContentEncoding() {
		return contentEncoding;
	}

	public String getContent() {
		return content;
	}

	public String getContentType() {
		return contentType;
	}

	public String getMessage() {
		return message;
	}

	public String getMethod() {
		return method;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public Vector<String> getContentCollection() {
		return contentCollection;
	}

}