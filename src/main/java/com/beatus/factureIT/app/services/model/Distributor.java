package com.beatus.factureIT.app.services.model;

public class Distributor extends BaseData{
	private int distributorId;
	private String distributorName;
	private String distributorPhone;
	private int locationId;
	private String distributorLocation;
	
	public int getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(int distributorId) {
		this.distributorId = distributorId;
	}
	public String getDistributorName() {
		return distributorName;
	}
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	public String getDistributorPhone() {
		return distributorPhone;
	}
	public void setDistributorPhone(String distributorPhone) {
		this.distributorPhone = distributorPhone;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getDistributorLocation() {
		return distributorLocation;
	}
	public void setDistributorLocation(String distributorLocation) {
		this.distributorLocation = distributorLocation;
	}

}
