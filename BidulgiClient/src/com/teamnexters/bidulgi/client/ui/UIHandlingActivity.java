package com.teamnexters.bidulgi.client.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;

public abstract class UIHandlingActivity extends FragmentActivity {
	public abstract void onHandleUI(BidulgiResponsePacket response);

	private UILocker uiLocker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		uiLocker = new UILocker(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		HttpRequestThread.getInstance().setHandler(this);
	}

	protected void lockUI() {
		uiLocker.lock();
	}

	protected void unlockUI() {
		uiLocker.unlock();
	}
}
