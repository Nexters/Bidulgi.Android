package com.teamnexters.bidulgi.common.response;

public class NiceImageResponsePacket extends BidulgiResponsePacket {
	private String imageSrc;
	private String niceCookie;

	public String getNiceCookie() {
		return niceCookie;
	}

	public void setNiceCookie(String niceCookie) {
		this.niceCookie = niceCookie;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

}
