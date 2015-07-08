package com.teamnexters.kkaba.common.response;

public class BooleanResponsePacket extends BidulgiResponsePacket{
	private Boolean value;

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}
}
