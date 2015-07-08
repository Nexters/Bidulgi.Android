package com.teamnexters.kkaba.common.response;

public class LongResponsePacket extends BidulgiResponsePacket{
	private Long value;

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
}
