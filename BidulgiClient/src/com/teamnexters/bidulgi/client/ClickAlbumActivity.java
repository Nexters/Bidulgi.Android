package com.teamnexters.bidulgi.client;

import com.bumptech.glide.Glide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ClickAlbumActivity extends Activity {

	Intent intent;
	private ImageView imgTraining;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_click_album);

		intent = getIntent();
		imgTraining = (ImageView) findViewById(R.id.imgTraining);
		Glide.with(getApplicationContext()).load(intent.getExtras().getString("imgTrainingURL")).into(imgTraining);
	}

}
