package com.beatus.factureIT.authorization.api;

public enum FactureITAuthorities {

	READ_DISTRIBUTOR("READ_DISTRIBUTOR"), WRITE_DISTRIBUTOR("WRITE_DISTRIBUTOR"), READ_MANUFACTURER("READ_MANUFACTURER"), WRITE_MANUFACTURER("WRITE_MANUFACTURER"),
	READ_RETAILER("READ_RETAILER"), WRITE_RETAILER("WRITE_RETAILER"), READ_CUSTOMER("READ_CUSTOMER"), WRITE_CUSTOMER("WRITE_CUSTOMER"),
	UPDATE_USER("UPDATE_USER"),ADD_OR_UPDATE_COLLECTION_AGENT("ADD_OR_UPDATE_COLLECTION_AGENT"), 
	ADD_COLLECTION_AGENT_ROUTE("ADD_COLLECTION_AGENT_ROUTE"), UPDATE_COLLECTION_AGENT_ROUTE("UPDATE_COLLECTION_AGENT_ROUTE");

	private String value;

	FactureITAuthorities(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}
}
