package com.appfactory.service;

import com.appfactory.service.request.ContactInfoRequest;
import com.appfactory.service.request.MenuDataRequest;
import com.appfactory.service.request.MenuLayoutRequest;
import com.appfactory.service.response.ContactInfoResponse;
import com.appfactory.service.response.MenuDataResponse;
import com.appfactory.service.response.MenuLayoutResponse;

public class CommunicateService {

	public static MenuLayoutResponse getMenuLayout() throws Exception {
		MenuLayoutRequest request = new MenuLayoutRequest();
		MenuLayoutResponse response = (MenuLayoutResponse) ServiceCallUtil
				.sendGetRequest(ServiceCallUtil.getMenuLayout, request,
						MenuLayoutResponse.class);
		return response;
	}

	public static MenuDataResponse getMenuLayoutData() throws Exception {
		MenuDataRequest request = new MenuDataRequest();
		MenuDataResponse response = (MenuDataResponse) ServiceCallUtil
				.sendGetRequest(ServiceCallUtil.getMenuLayoutData, request,
						MenuDataResponse.class);
		return response;
	}

	public static ContactInfoResponse getContactInfo() throws Exception {
		ContactInfoRequest request = new ContactInfoRequest();
		ContactInfoResponse response = (ContactInfoResponse) ServiceCallUtil
				.sendGetRequest(ServiceCallUtil.getContactInfo, request,
						ContactInfoResponse.class);
		return response;
	}
}
