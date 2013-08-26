package com.appfactory.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.appfactory.service.request.ServiceRequest;
import com.appfactory.service.response.ServiceResponse;
import com.appfactory.util.App;

public class ServiceCallUtil {
	public static final String baseServiceUrl = "http://" + App.getServerIP();
	public static final String baseHttpUrl = "http://" + App.getServerIP();

	/**
	 * URLs.
	 */
	public static final String getMenuLayout = baseHttpUrl
			+ "/appconfig/menu_page/layout.json";
	public static final String getMenuLayoutData = baseHttpUrl
			+ "/appconfig/menu_page/data";
	public static final String getContactInfo = baseHttpUrl
			+ "/contact_info.json";
	

	static ServiceResponse sendPostRequest(String serviceUrl,
			ServiceRequest serviceRequest, Class<?> responseClass)
			throws Exception {

		ServiceResponse serviceResponse = null;
		HttpClient client = getHttpsClient();
		BasicHttpEntity requestEntity = getRequestEntity(serviceRequest);
		HttpPost post = getHttpPost(serviceUrl, requestEntity);
		BufferedHttpEntity responseEntity;
		HttpResponse response;
		try {
			response = client.execute(post);
			responseEntity = new BufferedHttpEntity(response.getEntity());
			String responseStr = EntityUtils.toString(responseEntity, "UTF-8");
			System.out.println(responseStr);
			serviceResponse = JsonParser.parse(responseStr, responseClass);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return serviceResponse;
	}

	static ServiceResponse sendGetRequest(String serviceUrl,
			ServiceRequest serviceRequest, Class<?> responseClass)
			throws Exception {

		ServiceResponse serviceResponse = null;
		HttpClient client = getHttpsClient();
		BasicHttpEntity requestEntity = getRequestEntity(serviceRequest);
		HttpGet get = getHttpGet(serviceUrl, requestEntity);
		BufferedHttpEntity responseEntity;
		HttpResponse response;
		try {
			response = client.execute(get);
			responseEntity = new BufferedHttpEntity(response.getEntity());
			String responseStr = EntityUtils.toString(responseEntity, "UTF-8");
			System.out.println(responseStr);
			serviceResponse = JsonParser.parse(responseStr, responseClass);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return serviceResponse;
	}

	private static BasicHttpEntity getRequestEntity(ServiceRequest request) {
		BasicHttpEntity requestEntity = new BasicHttpEntity();
		try {
			byte[] requestBytes = null;
			String json = request.getJson();
			requestBytes = json.getBytes("UTF-8");
			requestEntity.setContent(new ByteArrayInputStream(requestBytes));
			requestEntity.setContentType("application/json");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return requestEntity;
	}

	private static HttpGet getHttpGet(String url, BasicHttpEntity entity) {
		HttpGet get = new HttpGet(url);
		BasicHeader pragmaHeader = new BasicHeader("Pragma", "no-cache");
		BasicHeader cacheHeader = new BasicHeader("Cache-control", "no-cache");
		BasicHeader platFormHeader = new BasicHeader("appplatform", "android");
		// post.setEntity(entity);
		get.setHeaders(new Header[] { pragmaHeader, cacheHeader, platFormHeader });
		return get;
	}

	private static HttpPost getHttpPost(String url, BasicHttpEntity entity) {
		HttpPost post = new HttpPost(url);
		BasicHeader pragmaHeader = new BasicHeader("Pragma", "no-cache");
		BasicHeader cacheHeader = new BasicHeader("Cache-control", "no-cache");
		BasicHeader platFormHeader = new BasicHeader("appplatform", "android");
		post.setEntity(entity);
		post.setHeaders(new Header[] { pragmaHeader, cacheHeader,
				platFormHeader });
		return post;
	}

	private static HttpClient getHttpsClient() {
		HttpClient client = SSLSocketFactoryEx.getNewHttpClient();
		client.getParams().setIntParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT,
				60000);
		return client;
	}
}
