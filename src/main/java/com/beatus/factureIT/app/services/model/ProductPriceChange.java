package com.beatus.factureIT.app.services.model;

import java.util.List;

public class ProductPriceChange {
	
	private String productId;
	private String price;
	private String unit;
	private String receiverUserType;
	private List<String> receiverUserTypeIds;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getReceiverUserType() {
		return receiverUserType;
	}
	public void setReceiverUserType(String receiverUserType) {
		this.receiverUserType = receiverUserType;
	}
	public List<String> getReceiverUserTypeIds() {
		return receiverUserTypeIds;
	}
	public void setReceiverUserTypeIds(List<String> receiverUserTypeIds) {
		this.receiverUserTypeIds = receiverUserTypeIds;
	}
}
