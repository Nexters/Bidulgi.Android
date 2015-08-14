package com.teamnexters.bidulgi.common.request;

public class LongRequestPacket extends BidulgiRequestPacket{
	private long value;

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
}
