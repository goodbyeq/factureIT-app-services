package com.beatus.factureIT.app.services.model;

public class SMSConfiguration  extends BaseData{
	
	private String configurationId;
	private String smsUrl;
	private String parameterUsername;
	private String parameterPassword;
	private String sendCode;
	private String messageHeader;
	private String messageFooter;
	
	public String getSmsUrl() {
		return smsUrl;
	}
	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}
	public String getParameterUsername() {
		return parameterUsername;
	}
	public void setParameterUsername(String parameterUsername) {
		this.parameterUsername = parameterUsername;
	}
	public String getParameterPassword() {
		return parameterPassword;
	}
	public void setParameterPassword(String parameterPassword) {
		this.parameterPassword = parameterPassword;
	}
	public String getSendCode() {
		return sendCode;
	}
	public void setSendCode(String sendCode) {
		this.sendCode = sendCode;
	}
	public String getMessageHeader() {
		return messageHeader;
	}
	public void setMessageHeader(String messageHeader) {
		this.messageHeader = messageHeader;
	}
	public String getMessageFooter() {
		return messageFooter;
	}
	public void setMessageFooter(String messageFooter) {
		this.messageFooter = messageFooter;
	}
	public String getConfigurationId() {
		return configurationId;
	}
	public void setConfigurationId(String configurationId) {
		this.configurationId = configurationId;
	}
}
