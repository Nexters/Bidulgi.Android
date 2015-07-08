package com.teamnexters.kkaba.common.request;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public abstract class BidulgiRequestPacket implements BidulgiRequestCode{
	private Integer requestCode;

	public List<NameValuePair> toHttpParams(){
		List<NameValuePair> returnList = new ArrayList<NameValuePair>();
		for(Field f : this.getClass().getDeclaredFields()){
			try{
				f.setAccessible(true);
				returnList.add(new BasicNameValuePair(f.getName(), f.get(this).toString()));
			}catch(Exception e){}
		}
		return returnList;
	}

	public Integer getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(Integer requestCode) {
		this.requestCode = requestCode;
	}
}
