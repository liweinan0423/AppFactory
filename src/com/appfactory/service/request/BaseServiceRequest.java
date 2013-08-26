package com.appfactory.service.request;

import com.google.gson.Gson;

public class BaseServiceRequest implements ServiceRequest {

	@Override
	public String getJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

}
