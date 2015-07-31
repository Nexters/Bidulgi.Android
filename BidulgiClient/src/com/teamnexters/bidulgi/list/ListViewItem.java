package com.teamnexters.bidulgi.list;

public class ListViewItem {
	private int icon;
	private String name;
	private String date;

	public int getIcon() {
		return icon;
	}

	public String getName() {
		return name;
	}
	
	public String getDate(){
		return date;
	}

	public ListViewItem(int icon, String name, String date) {
		this.icon = icon;
		this.name = name;
		this.date = date;
	}
}
