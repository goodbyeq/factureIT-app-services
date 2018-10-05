package com.beatus.factureIT.app.services.model;

public class Product implements Comparable<Product>{
	private String productId;
	private String productName;
	private String productDesc;
	private byte[] productImage;
	private String productImageString;
	private String productCategoryId;
	private String brandName;
	private String hsnCode;
	private String costPrice;
	private String marginAmount;
	private String sellingPrice;
	private String unit;
	private String totalQuantityAvailable;
	private String gstTax;
	private String cgstTax;
	private String sgstTax;
	private String igstTax;
	private String userType;
	private String userTypeID;
	private ProductCategory productCategory;
	private String gstNumber;
	
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

	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}

	public String getMarginAmount() {
		return marginAmount;
	}

	public void setMarginAmount(String marginAmount) {
		this.marginAmount = marginAmount;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTotalQuantityAvailable() {
		return totalQuantityAvailable;
	}

	public void setTotalQuantityAvailable(String totalQuantityAvailable) {
		this.totalQuantityAvailable = totalQuantityAvailable;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserTypeID() {
		return userTypeID;
	}

	public void setUserTypeID(String userTypeID) {
		this.userTypeID = userTypeID;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public String getGstTax() {
		return gstTax;
	}

	public void setGstTax(String gstTax) {
		this.gstTax = gstTax;
	}

	public String getCgstTax() {
		return cgstTax;
	}

	public void setCgstTax(String cgstTax) {
		this.cgstTax = cgstTax;
	}

	public String getSgstTax() {
		return sgstTax;
	}

	public void setSgstTax(String sgstTax) {
		this.sgstTax = sgstTax;
	}

	public String getIgstTax() {
		return igstTax;
	}

	public void setIgstTax(String igstTax) {
		this.igstTax = igstTax;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

}