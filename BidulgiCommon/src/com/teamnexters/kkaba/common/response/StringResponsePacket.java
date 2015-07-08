package com.teamnexters.kkaba.common.response;

public class StringResponsePacket extends BidulgiResponsePacket{
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
