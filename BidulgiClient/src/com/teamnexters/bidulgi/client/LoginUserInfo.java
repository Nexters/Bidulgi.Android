package com.teamnexters.bidulgi.client;

import com.teamnexters.bidulgi.common.data.LoginData;

public class LoginUserInfo {
	private static LoginUserInfo instance = new LoginUserInfo();

	private LoginUserInfo() {
	}

	private LoginData loginData;

	public LoginData getLoginData() {
		return loginData;
	}

	public void setLoginData(LoginData loginData) {
		this.loginData = loginData;
	}

	public static LoginUserInfo getInstance() {
		return instance;
	}
}
