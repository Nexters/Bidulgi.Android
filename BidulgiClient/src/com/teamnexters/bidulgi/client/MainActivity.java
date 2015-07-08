package com.teamnexters.bidulgi.client;

import android.os.Bundle;

import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.kkaba.common.response.BidulgiResponsePacket;

public class MainActivity extends UIHandlingActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	@Override
	public void onHandleUI(BidulgiResponsePacket response) {
		// TODO Auto-generated method stub
		
	}

}
