package com.beatus.factureIT.authorization.api;

public enum FactureITUserType {

	MANUFACTURER("MANUFACTURER"), DISTRIBUTOR("DISTRIBUTOR"), RETAILER("RETAILER"), CUSTOMER("CUSTOMER"), COLLECTION_AGENT("COLLECTION_AGENT");

	private String value;

	FactureITUserType(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}
}
