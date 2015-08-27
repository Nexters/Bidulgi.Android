package com.teamnexters.bidulgi.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.teamnexters.bidulgi.common.data.SoldierData;

public class SoldierInfoStore {
	
	private static SoldierInfoStore instance = new SoldierInfoStore();
	
	public static SoldierInfoStore getInstance(){
		return instance;
	}
	
	private Map<Long, SoldierData> innerStore;
	
	private SoldierInfoStore() {
		innerStore = new HashMap<Long, SoldierData>();
	}
	
	public void reload(List<SoldierData> soldierDataList){
		for(SoldierData soldierData : soldierDataList){
			innerStore.put(soldierData.getSoldierId(), soldierData);
		}
	}
	
	public SoldierData getData(long soldierId){
		SoldierData returnData = innerStore.get(soldierId);
		if(returnData==null){
			returnData = new SoldierData();
			returnData.setName("알 수 없음");
		}
		return returnData;
	}
}
