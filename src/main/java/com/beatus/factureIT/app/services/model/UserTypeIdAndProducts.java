package com.beatus.factureIT.app.services.model;

import java.util.List;

public class UserTypeIdAndProducts {
	
	private String userTypeId;
	private List<String> productIds;
	
	public String getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}
	public List<String> getProductIds() {
		return productIds;
	}
	public void setProductIds(List<String> productIds) {
		this.productIds = productIds;
	}

}
