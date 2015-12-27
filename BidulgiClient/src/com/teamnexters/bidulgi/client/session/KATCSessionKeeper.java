package com.teamnexters.bidulgi.client.session;

import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.common.request.BidulgiRequestCode;
import com.teamnexters.bidulgi.common.request.BidulgiRequestPacket;
import com.teamnexters.bidulgi.common.request.CommonRequestPacket;

public class KATCSessionKeeper extends Thread {

	private static boolean isStarted = false;

	private KATCSessionKeeper() {
	}

	@Override
	public void run() {
		while (true) {
			try {
				BidulgiRequestPacket request = new CommonRequestPacket();
				request.setRequestCode(BidulgiRequestCode.REQUEST_KEEP_SESSION);
				HttpRequestThread.getInstance().addRequest(request);
				Thread.sleep(60000L);
			} catch (Exception e) {
			}
		}
	}

	public synchronized static void startSession() {
		if (!isStarted) {
			new KATCSessionKeeper().start();
			isStarted = true;
		}
	}
}
