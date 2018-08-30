package main.java.com.beatus.factureIT.app.services.model;

public class UserDeviceInfo {
	
	private String userDeviceInfoId;
	private String deviceId;
	private String firebaseId;
	private String gcmId;
	private String visionId;
	private String uid;
	
	public String getUserDeviceInfoId() {
		return userDeviceInfoId;
	}
	public void setUserDeviceInfoId(String userDeviceInfoId) {
		this.userDeviceInfoId = userDeviceInfoId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getFirebaseId() {
		return firebaseId;
	}
	public void setFirebaseId(String firebaseId) {
		this.firebaseId = firebaseId;
	}
	public String getGcmId() {
		return gcmId;
	}
	public void setGcmId(String gcmId) {
		this.gcmId = gcmId;
	}
	public String getVisionId() {
		return visionId;
	}
	public void setVisionId(String visionId) {
		this.visionId = visionId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
}
