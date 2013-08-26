package com.appfactory.service.response;

import java.util.List;

import com.appfactory.model.MenuCell;

public class MenuDataResponse extends BaseServiceResponse {
	private List<MenuCell> cells;

	public List<MenuCell> getCells() {
		return cells;
	}

	public void setCells(List<MenuCell> cells) {
		this.cells = cells;
	}

}
