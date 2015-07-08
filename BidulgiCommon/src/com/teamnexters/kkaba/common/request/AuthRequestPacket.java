package com.teamnexters.kkaba.common.request;


public class AuthRequestPacket extends BidulgiRequestPacket{
	private String accessToken;
	private String pushInstallationKey;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getPushInstallationKey() {
		return pushInstallationKey;
	}

	public void setPushInstallationKey(String pushInstallationKey) {
		this.pushInstallationKey = pushInstallationKey;
	}
}
