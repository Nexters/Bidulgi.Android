package com.teamnexters.bidulgi.common.response;

import com.teamnexters.bidulgi.common.data.SoldierData;

public class SoldierResponsePacket extends BidulgiResponsePacket{
	private SoldierData soldierData;

	public SoldierData getSoldierData() {
		return soldierData;
	}

	public void setSoldierData(SoldierData soldierData) {
		this.soldierData = soldierData;
	}

}
