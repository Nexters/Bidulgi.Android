package com.teamnexters.bidulgi.nice;

import com.teamnexters.bidulgi.common.data.NiceAuthData;
import com.teamnexters.bidulgi.common.request.NiceAuthRequestPacket;

public interface NiceAuthRequester {
	public void startNiceAuth(NiceAuthRequestPacket request);
	public void sendNiceSMS(String authImageNumber);
	public void notifySMSNumber(String smsNumber);
}
