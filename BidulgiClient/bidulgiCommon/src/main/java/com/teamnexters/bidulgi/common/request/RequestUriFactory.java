package com.teamnexters.bidulgi.common.request;

import java.lang.reflect.Field;
import java.util.HashMap;

public class RequestUriFactory{
	private static HashMap<Integer, String> storeMap = new HashMap<Integer,String>();
	static{
		for(Field f : BidulgiRequestCode.class.getFields()){
			try {
				storeMap.put((Integer)f.get(null), (String)BidulgiRequestUri.class.getField(f.getName()).get(null));
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	public static String requestUri(Integer requestCode){
		return storeMap.get(requestCode);
	}
}
