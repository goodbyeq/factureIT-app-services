package com.beatus.factureIT.app.services.model;

import java.sql.Timestamp;

public class Notification {
	
	private String notificationId;
	private String sender;
	private String receiver;
	private String message;
	private Timestamp notificationTimestamp;
	
	public String getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getNotificationTimestamp() {
		return notificationTimestamp;
	}
	public void setNotificationTimestamp(Timestamp notificationTimestamp) {
		this.notificationTimestamp = notificationTimestamp;
	}
}
