package com.teamnexters.bidulgi.common.response;



public abstract class BidulgiResponsePacket implements BidulgiResponseCode{
	private Integer responseCode;

	public final static int RESPONSE_TYPE_UI = 1;
	public final static int RESPONSE_TYPE_PROCESS = 2;

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public static Class<? extends BidulgiResponsePacket> getResponseClass(Integer responseCode){
		switch (responseCode) {
		case RESPONSE_TEST:
			return LongResponsePacket.class;

		case RESPONSE_REGISTRATION_SUCCESS:
		case RESPONSE_LOGIN_SUCCESS:
			return LoginResponsePacket.class;
		}
		return CommonResponsePacket.class;
	}
	

	public int getResponseType(){
		switch (responseCode) {
		case RESPONSE_TEST:
			return RESPONSE_TYPE_PROCESS;
		}
		return RESPONSE_TYPE_UI;
	}
}
