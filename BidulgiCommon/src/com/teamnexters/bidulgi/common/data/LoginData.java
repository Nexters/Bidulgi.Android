package com.teamnexters.bidulgi.common.data;

public class LoginData {
	private long id;
	private int point;
	private String name;
	private String phoneNumber;
	private String authHash;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getAuthHash() {
		return authHash;
	}

	public void setAuthHash(String authHash) {
		this.authHash = authHash;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
