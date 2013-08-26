package com.appfactory.service;

import com.appfactory.service.response.BaseServiceResponse;
import com.google.gson.Gson;

class JsonParser {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static BaseServiceResponse parse(String jsonString, Class response) {
		Gson gson = new Gson();
		BaseServiceResponse baseServiceResponse = (BaseServiceResponse) gson
				.fromJson(jsonString, response);
		return baseServiceResponse;
	}
}
