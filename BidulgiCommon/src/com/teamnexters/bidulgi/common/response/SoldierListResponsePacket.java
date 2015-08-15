package com.teamnexters.bidulgi.common.response;

import java.util.List;

import com.teamnexters.bidulgi.common.data.SoldierData;

public class SoldierListResponsePacket extends BidulgiResponsePacket{
	private List<SoldierData> soldierData;

	public List<SoldierData> getSoldierData() {
		return soldierData;
	}

	public void setSoldierData(List<SoldierData> soldierData) {
		this.soldierData = soldierData;
	}
}
