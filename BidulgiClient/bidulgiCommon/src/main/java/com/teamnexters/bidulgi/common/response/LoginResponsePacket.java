package com.teamnexters.bidulgi.common.response;

import com.teamnexters.bidulgi.common.data.LoginData;

public class LoginResponsePacket extends BidulgiResponsePacket{
	private LoginData loginData;

	public LoginData getLoginData() {
		return loginData;
	}

	public void setLoginData(LoginData loginData) {
		this.loginData = loginData;
	}

}
