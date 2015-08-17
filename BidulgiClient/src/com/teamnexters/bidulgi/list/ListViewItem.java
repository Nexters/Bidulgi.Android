package com.teamnexters.bidulgi.list;

public class ListViewItem {
	private String profilePhotoSrc;
	private String name;
	private String date;
	private String regiment;
	private String company;
	private String platoon;
	private String number;
	private Long soldierId;

	public String getProfilePhotoSrc() {
		return profilePhotoSrc;
	}

	public String getName() {
		return name;
	}
	
	public String getDate(){
		return date;
	}

	public String getRegiment(){
		return regiment;
	}
	public String getCompany(){
		return company;
	}
	public String getPlatoon(){
		return platoon;
	}
	public String getNumber(){
		return number;
	}
	public Long getsoldierId(){
		return soldierId;
	}
	public ListViewItem(String profilePhotoSrc, String name, String date , String regiment, String company, String platoon, String number, Long soldierId) {
		this.profilePhotoSrc = profilePhotoSrc;
		this.name = name;
		this.date = date;
		this.regiment = regiment;
		this.company = company;
		this.platoon = platoon;
		this.number = number;
		this.soldierId = soldierId;
	}
}
