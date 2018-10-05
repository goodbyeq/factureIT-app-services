package com.beatus.factureIT.app.services.model;

import java.util.List;

public class ManufacturerAndProduct {
	
	private String manufacturerId;
	private List<String> productIds;
	
	public List<String> getProductIds() {
		return productIds;
	}
	public void setProductIds(List<String> productIds) {
		this.productIds = productIds;
	}
	public String getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
}
