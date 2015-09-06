package com.teamnexters.bidulgi.client;

import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MessageSendCheckActivity extends Activity {

	private Button btnNotSendMail;
	private Button btnSendMessage;
	private TextView txtCheckSendMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_send_check);

		Typeface typeface = Typeface.createFromAsset(getAssets(), "NANUMGOTHIC.TTF");
		btnNotSendMail = (Button) findViewById(R.id.btnNotSendMail);
		btnSendMessage = (Button) findViewById(R.id.btnSendMessage);
		txtCheckSendMessage = (TextView) findViewById(R.id.txtCheckSendMessage);
		btnNotSendMail.setOnClickListener(onClickListener);
		btnSendMessage.setOnClickListener(onClickListener);
		txtCheckSendMessage.setTypeface(typeface);

	}


	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			case R.id.btnNotSendMail:
				Intent resNotIntent = new Intent();
				setResult(2, resNotIntent);
				finish();
				break;
			case R.id.btnSendMessage:
				Intent resIntent = new Intent();
				setResult(1, resIntent);
				finish();
				break;
			}
		}

	};
}
