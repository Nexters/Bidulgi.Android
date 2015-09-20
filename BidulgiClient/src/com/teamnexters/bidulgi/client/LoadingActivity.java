package com.teamnexters.bidulgi.client;

import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class LoadingActivity extends UIHandlingActivity {

	AnimationDrawable animation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_loading);

		ImageView animationImg = (ImageView) findViewById(R.id.animationImg);
		animationImg.setBackgroundResource(R.layout.animation_loading);
		animationImg.setVisibility(ImageView.VISIBLE);

		animation = (AnimationDrawable) animationImg.getBackground();
		animation.start();
		Handler hd = new Handler();
		hd.postDelayed(new Runnable() {

			@Override
			public void run() {
				animation.stop();
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
				lockUI();
				finish(); // 3 초후 이미지를 닫아버림
			
				
			}
		}, 3200);
	}

	@Override
	public void onHandleUI(BidulgiResponsePacket response) {
		// TODO Auto-generated method stub
		
	}
}
