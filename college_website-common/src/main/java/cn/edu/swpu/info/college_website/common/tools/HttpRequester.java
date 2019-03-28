package cn.edu.swpu.info.college_website.common.tools;

import java.io.IOException;
import java.util.Map;

public interface HttpRequester {
	final static int DEFAULT_CONNECTION_TIMEOUT = 30000;
	final static int DEFAULT_SO_TIMEOUT = 10000;

	HttpResponse doPost(String url, Map<String, String> headers, String requestBody) throws IOException;

	HttpResponse doGet(String url, Map<String, String> headers) throws IOException;

	HttpResponse doPost(String url, Map<String, String> headers, String requestBody, int connTimeout, int soTimeout) throws IOException;

	HttpResponse doGet(String url, Map<String, String> headers, int connTimeout, int soTimeout) throws IOException;
	
	HttpResponse sendGet(String urlString) throws IOException ;
}