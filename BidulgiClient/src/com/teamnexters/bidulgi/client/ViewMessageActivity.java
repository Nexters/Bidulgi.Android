package com.teamnexters.bidulgi.client;

import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ViewMessageActivity extends UIHandlingActivity {
	private ActionBar actionBar;
	private Intent intent;
	private TextView txtTitle;
	private TextView txtMessageTitle;
	private TextView txtContent;
	private TextView txtMessageContent;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_message);

		Typeface typeface = Typeface.createFromAsset(getAssets(), "NANUMGOTHIC.TTF");
		actionBar = getActionBar();
		actionBar.setTitle(null);
		actionBar.setIcon(null);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);
		
		intent = getIntent();
		
		txtTitle = (TextView) findViewById(R.id.txtTitle);
		txtMessageTitle = (TextView) findViewById(R.id.txtMessageTitle);
		txtContent = (TextView) findViewById(R.id.txtContent);
		txtMessageContent = (TextView) findViewById(R.id.txtMessageContent);
		
		txtTitle.setTypeface(typeface);
		txtMessageTitle.setTypeface(typeface);
		txtContent.setTypeface(typeface);
		txtMessageContent.setTypeface(typeface);
		
		txtMessageTitle.setText(intent.getExtras().getString("title"));
		txtMessageContent.setText(intent.getExtras().getString("content"));
		txtMessageContent.setMovementMethod(new ScrollingMovementMethod());
		
		
	}

	@Override
	public void onHandleUI(BidulgiResponsePacket response) {
		// TODO Auto-generated method stub

	}
}
