package com.appfactory.service.response;

import java.util.List;

import com.appfactory.model.MenuCell;

public class MenuLayoutResponse extends BaseServiceResponse {
	private String layout_type;
	private List<MenuCell> cells;

	public String getLayout_type() {
		return layout_type;
	}

	public void setLayout_type(String layout_type) {
		this.layout_type = layout_type;
	}

	public List<MenuCell> getCells() {
		return cells;
	}

	public void setCells(List<MenuCell> cells) {
		this.cells = cells;
	}

}
