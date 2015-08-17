package com.teamnexters.bidulgi.client.ui;

import java.lang.ref.WeakReference;

import com.teamnexters.bidulgi.client.BidoolgiFragmentActivity;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;

import android.app.Activity;
import android.os.Handler;

public class UiHandlerForFriends extends Handler {
	final WeakReference<BidoolgiFragmentActivity> hActivity;

	public UiHandlerForFriends(BidoolgiFragmentActivity activity) {
		this.hActivity = new WeakReference<BidoolgiFragmentActivity>(activity);
	}

	@Override
	public void handleMessage(android.os.Message msg) {
		BidulgiResponsePacket response = (BidulgiResponsePacket) msg.obj;
		BidoolgiFragmentActivity realActivity = hActivity.get();
		if (realActivity != null) {
			realActivity.onHandleUI(response);
		}
	};
	
	public void runAction(Runnable action) {
		Activity realActivity = hActivity.get();
		if (realActivity != null) {
			realActivity.runOnUiThread(action);
		}
	}
}