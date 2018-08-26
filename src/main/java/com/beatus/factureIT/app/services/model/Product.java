package com.beatus.factureIT.app.services.model;

public class Product  extends BaseData implements Comparable<Product>{
	private String productId;
	private String productName;
	private String productDesc;
	private byte[] productImage;
	private String productImageString;
	private String productCategoryId;
	private String brandName;
	private String hsnCode;
	private String price;
	private String unit;
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	@Override
	public int compareTo(Product compare) {
		return this.productName.compareTo(compare.productName);
	}

	public String getProductImageString() {
		return productImageString;
	}

	public void setProductImageString(String productImageString) {
		this.productImageString = productImageString;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
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
	
}