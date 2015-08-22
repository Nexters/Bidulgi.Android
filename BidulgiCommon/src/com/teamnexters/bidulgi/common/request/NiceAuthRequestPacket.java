package com.teamnexters.bidulgi.common.request;

import com.teamnexters.bidulgi.common.data.NiceAuthData;


public class NiceAuthRequestPacket extends BidulgiRequestPacket{
	private NiceAuthData niceAuthData;

	public NiceAuthData getNiceAuthData() {
		return niceAuthData;
	}

	public void setNiceAuthData(NiceAuthData niceAuthData) {
		this.niceAuthData = niceAuthData;
	}
}
