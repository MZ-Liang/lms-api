package com.lms.api.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import com.lms.api.model.HttpResult;

/**
 * HttpClient服务工具类
 * 
 * @author Ming
 * @date 2018年12月3日
 */
public class HttpClientUtil {
	/** HttpClient连接池 */
	private CloseableHttpClient httpClient;
	/** 连接配置信息 */
	private RequestConfig requestConfig;

	/**
	 * 构造器
	 * 
	 * @param httpClient
	 * @param requestConfig
	 */
	public HttpClientUtil(CloseableHttpClient httpClient, RequestConfig requestConfig) {
		super();
		this.httpClient = httpClient;
		this.requestConfig = requestConfig;
	}

	/**
	 * post请求
	 * 
	 * @param requestUrl    请求路径
	 * @param authorization 身份信息
	 * @param body          body请求参数（json）
	 * @return
	 * @throws Exception
	 */
	public HttpResult doPost(String requestUrl, String authorization, String body) throws Exception {
		// 声明httpPost请求
		HttpPost httpPost = new HttpPost(requestUrl);

		// 配置，授权
		configureHttpRequest(httpPost, authorization);

		// 添加json参数
		if (StringUtils.isNoneBlank(body)) {
			StringEntity params = new StringEntity(body, StandardCharsets.UTF_8);
			httpPost.setEntity(params);
		}

		return executeRequest(httpPost);
	}

	/**
	 * put请求
	 * 
	 * @param requestUrl    请求路径
	 * @param authorization 身份信息
	 * @param body          body请求参数（json）
	 * @return
	 * @throws Exception
	 */
	public HttpResult doPut(String requestUrl, String authorization, String body) throws Exception {
		// 声明httpPut请求
		HttpPut httpPut = new HttpPut(requestUrl);

		// 配置，授权
		configureHttpRequest(httpPut, authorization);

		// 添加json参数
		if (StringUtils.isNoneBlank(body)) {
			StringEntity params = new StringEntity(body, StandardCharsets.UTF_8);
			httpPut.setEntity(params);
		}

		return executeRequest(httpPut);
	}

	/**
	 * get请求
	 * 
	 * @param requestUrl    请求路径
	 * @param authorization 身份信息
	 * @param map           请求参数集
	 * @return
	 * @throws Exception
	 */
	public HttpResult doGet(String requestUrl, String authorization, Map<String, Object> map) throws Exception {
		// 组装参数
		assembleParams(requestUrl,map);

		// 声明 http get 请求
		HttpGet httpGet = new HttpGet(requestUrl);
		// 配置，授权
		configureHttpRequest(httpGet, authorization);

		return executeRequest(httpGet);
	}

	/**
	 * delete请求
	 * @param requestUrl    请求路径
	 * @param authorization 身份信息
	 * @param map           请求参数集
	 * @return 
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public HttpResult doDelete(String requestUrl, String authorization, Map<String, Object> map)
			throws ClientProtocolException, IOException, URISyntaxException {
		// 组装参数
		assembleParams(requestUrl,map);
		
		// 声明httpPut请求
		HttpDelete httpDelete = new HttpDelete(requestUrl);

		// 配置，授权
		configureHttpRequest(httpDelete, authorization);

		return executeRequest(httpDelete);
	}
	
	/**
	 * 组装url地址参数，拼接到requesUrl参数里
	 * @param requestUrl 地址
	 * @param map 参数集合
	 * @throws URISyntaxException
	 */
	private void assembleParams(String requestUrl, Map<String, Object> map) throws URISyntaxException {
		URIBuilder uriBuilder = null;

		// 组装参数
		if (!CollectionUtils.isEmpty(map)) {
			uriBuilder = new URIBuilder(requestUrl);
			// 遍历map,拼接请求参数
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
			}
		}
	}

	/**
	 * 配置请求信息，并添加授权
	 * 
	 * @param httpRequest   请求
	 * @param authorization 身份信息
	 */
	private void configureHttpRequest(HttpRequestBase httpRequest, String authorization) {
		// 加入配置信息
		httpRequest.setConfig(requestConfig);
		httpRequest.setHeader("content-type", "application/json;charset=UTF-8");

		// 身份信息编码
		authorization = Base64.getEncoder().encodeToString((authorization).getBytes(StandardCharsets.UTF_8));
		// 添加身份授权
		httpRequest.setHeader("Authorization", "Basic " + authorization);
	}

	/**
	 * 执行请求并响应
	 * 
	 * @param httpRequest 请求
	 * @return 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private HttpResult executeRequest(HttpRequestBase httpRequest) throws ClientProtocolException, IOException {
		// 发起请求
		CloseableHttpResponse response = this.httpClient.execute(httpRequest);
		// 响应字符串
		String entityString = null;
		if (null!=response.getEntity()) {
			entityString=EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
		}
		// 接收响应
		HttpResult result = new HttpResult(response.getStatusLine().getStatusCode(),entityString);
		// Unconditionally close a response.
		HttpClientUtils.closeQuietly(response);
		return result;
	}

}
