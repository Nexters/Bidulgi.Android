package com.teamnexters.bidulgi.common.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SoldierData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1130058079673487193L;
	private String name;
	private String birthString;
	private String enterDateString;
	private String regiment;
	private String company;
	private String platoon;
	private String number;
	private String identityCookie;
	private long soldierId;
	private boolean isFriend;

	private String profilePhotoSrc;
	private Set<String> photoSrcSet;
	private Set<String> newPhotoSrcSet;
	private long lastRefreshTime;
	// 86400000=го╥Г

	public SoldierData() {
		photoSrcSet = new HashSet<String>();
		newPhotoSrcSet = new HashSet<String>();

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

	public String getEnterDateString() {
		return enterDateString;
	}

	public void setEnterDateString(String enterDateString) {
		this.enterDateString = enterDateString;
	}

	public String getRegiment() {
		return regiment;
	}

	public void setRegiment(String regiment) {
		this.regiment = regiment;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPlatoon() {
		return platoon;
	}

	public void setPlatoon(String platoon) {
		this.platoon = platoon;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIdentityCookie() {
		return identityCookie;
	}

	public void setIdentityCookie(String identityCookie) {
		this.identityCookie = identityCookie;
	}

	public Set<String> getPhotoSrcSet() {
		return photoSrcSet;
	}

	public void setPhotoSrcSet(Set<String> photoSrcSet) {
		this.photoSrcSet = photoSrcSet;
	}

	public String getProfilePhotoSrc() {
		return profilePhotoSrc;
	}

	public void setProfilePhotoSrc(String profilePhotoSrc) {
		this.profilePhotoSrc = profilePhotoSrc;
	}

	public long getSoldierId() {
		return soldierId;
	}

	public void setSoldierId(long soldierId) {
		this.soldierId = soldierId;
	}

	public boolean isFriend() {
		return isFriend;
	}

	public void setFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}

	public boolean canRefresh() {
		if (System.currentTimeMillis() - lastRefreshTime > 86400000) {
			return true;
		}
		return false;
	}

	public void setLastRefreshTime(long lastRefreshTime) {
		this.lastRefreshTime = lastRefreshTime;
	}

	public Set<String> getNewPhotoSrcSet() {
		return newPhotoSrcSet;
	}

	public void setNewPhotoSrcSet(Set<String> newPhotoSrcSet) {
		this.newPhotoSrcSet = newPhotoSrcSet;
	}

}