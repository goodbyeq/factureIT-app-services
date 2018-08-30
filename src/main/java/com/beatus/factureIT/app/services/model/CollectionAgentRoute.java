package com.beatus.factureIT.app.services.model;

public class CollectionAgentRoute {

	private String collectionAgentRouteId;
	private String collectionAgentId;
	private String sourceUid;
	private String destUid;
	private String amountToBeCollected;
	private String amountCollected;
	private String isAmountVerifiedBySourceUser;
	private String isAmountVerifiedByDestUser;
	private String isCollectionAgentUpdatedTheAmount;
	
	public String getCollectionAgentRouteId() {
		return collectionAgentRouteId;
	}
	public void setCollectionAgentRouteId(String collectionAgentRouteId) {
		this.collectionAgentRouteId = collectionAgentRouteId;
	}
	public String getCollectionAgentId() {
		return collectionAgentId;
	}
	public void setCollectionAgentId(String collectionAgentId) {
		this.collectionAgentId = collectionAgentId;
	}
	public String getSourceUid() {
		return sourceUid;
	}
	public void setSourceUid(String sourceUid) {
		this.sourceUid = sourceUid;
	}
	public String getDestUid() {
		return destUid;
	}
	public void setDestUid(String destUid) {
		this.destUid = destUid;
	}
	public String getAmountToBeCollected() {
		return amountToBeCollected;
	}
	public void setAmountToBeCollected(String amountToBeCollected) {
		this.amountToBeCollected = amountToBeCollected;
	}
	public String getAmountCollected() {
		return amountCollected;
	}
	public void setAmountCollected(String amountCollected) {
		this.amountCollected = amountCollected;
	}
	public String getIsAmountVerifiedBySourceUser() {
		return isAmountVerifiedBySourceUser;
	}
	public void setIsAmountVerifiedBySourceUser(String isAmountVerifiedBySourceUser) {
		this.isAmountVerifiedBySourceUser = isAmountVerifiedBySourceUser;
	}
	public String getIsAmountVerifiedByDestUser() {
		return isAmountVerifiedByDestUser;
	}
	public void setIsAmountVerifiedByDestUser(String isAmountVerifiedByDestUser) {
		this.isAmountVerifiedByDestUser = isAmountVerifiedByDestUser;
	}
	public String getIsCollectionAgentUpdatedTheAmount() {
		return isCollectionAgentUpdatedTheAmount;
	}
	public void setIsCollectionAgentUpdatedTheAmount(String isCollectionAgentUpdatedTheAmount) {
		this.isCollectionAgentUpdatedTheAmount = isCollectionAgentUpdatedTheAmount;
	}
}
