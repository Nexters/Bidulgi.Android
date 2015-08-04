package com.teamnexters.bidulgi.common.request;


public class SoldierRequestPacket extends BidulgiRequestPacket{
	private String enterDateString;
	private String name;
	private String birthString;
	
	public String getEnterDateString() {
		return enterDateString;
	}
	public void setEnterDateString(String enterDateString) {
		this.enterDateString = enterDateString;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthString() {
		return birthString;
	}
	public void setBirthString(String birthString) {
		this.birthString = birthString;
	}
}
