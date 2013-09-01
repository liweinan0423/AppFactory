package com.appfactory.service;

import com.appfactory.service.request.ContactInfoRequest;
import com.appfactory.service.request.MenuLayoutRequest;
import com.appfactory.service.request.PostCategoryRequest;
import com.appfactory.service.request.PostRequest;
import com.appfactory.service.response.ContactInfoResponse;
import com.appfactory.service.response.MenuLayoutResponse;
import com.appfactory.service.response.PostCategoryResponse;
import com.appfactory.service.response.PostResponse;

public class CommunicateService {

	public static MenuLayoutResponse getMenuLayout() throws Exception {
		MenuLayoutRequest request = new MenuLayoutRequest();
		MenuLayoutResponse response = (MenuLayoutResponse) ServiceCallUtil
				.sendGetRequest(ServiceCallUtil.getMenuLayout, request,
						MenuLayoutResponse.class);
		return response;
	}

	public static ContactInfoResponse getContactInfo() throws Exception {
		ContactInfoRequest request = new ContactInfoRequest();
		ContactInfoResponse response = (ContactInfoResponse) ServiceCallUtil
				.sendGetRequest(ServiceCallUtil.getContactInfo, request,
						ContactInfoResponse.class);
		return response;
	}

	public static PostCategoryResponse getArticleCategory(int id)
			throws Exception {
		PostCategoryRequest request = new PostCategoryRequest();
		PostCategoryResponse response = (PostCategoryResponse) ServiceCallUtil
				.sendGetRequest(ServiceCallUtil.getArticleCategory + id
						+ ServiceCallUtil.suffix, request,
						PostCategoryResponse.class);
		return response;
	}

//	public static PostResponse getArticle(int id) throws Exception {
//		PostRequest request = new PostRequest();
//		PostResponse response = (PostResponse) ServiceCallUtil.sendGetRequest(
//				ServiceCallUtil.getArticle + id, request, PostResponse.class);
//		return response;
//	}
}
