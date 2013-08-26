package com.appfactory.service.response;

import java.util.List;

import com.appfactory.model.Hotline;

public class ContactInfoResponse extends BaseServiceResponse {
	private List<Hotline> contact_info_list;

	public List<Hotline> getContact_info_list() {
		return contact_info_list;
	}

	public void setContact_info_list(List<Hotline> contact_info_list) {
		this.contact_info_list = contact_info_list;
	}

}
