package com.beatus.factureIT.app.services.model;

import java.util.List;

public class BaseData {

	private String companyId;
	private String uid;
	private List<String> userType;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<String> getUserType() {
		return userType;
	}

	public void setUserType(List<String> userType) {
		this.userType = userType;
	}
	
}
