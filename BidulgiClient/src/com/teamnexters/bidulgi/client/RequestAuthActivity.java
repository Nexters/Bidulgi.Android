package com.teamnexters.bidulgi.client;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RequestAuthActivity extends Activity {

	private TextView txtRequestAuth;
	private Button btnRequestCancel;
	private Button btnRequestAuth;

	public static final int RESULT_REQUEST_AUTH = 2000;
	public static final int RESULT_REQUEST_NO_AUTH = 2001;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_request_auth);

		Typeface typeface = Typeface.createFromAsset(getAssets(), "NANUMGOTHIC.TTF");

		txtRequestAuth = (TextView) findViewById(R.id.txtRequestAuth);
		txtRequestAuth.setTypeface(typeface);
		btnRequestCancel = (Button) findViewById(R.id.btnRequestCancel);
		btnRequestAuth = (Button) findViewById(R.id.btnRequestAuth);

		btnRequestCancel.setOnClickListener(onClickListener);
		btnRequestAuth.setOnClickListener(onClickListener);
	}

	public void onBackPressed() {

		Intent intentNO = new Intent();
		setResult(RESULT_REQUEST_NO_AUTH, intentNO);
		finish();

	};

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnRequestCancel:

				Intent intentNO = new Intent();
				setResult(RESULT_REQUEST_NO_AUTH, intentNO);
				finish();

				break;

			case R.id.btnRequestAuth:

				Toast.makeText(getApplicationContext(), "본인인증을 시작합니다.", Toast.LENGTH_SHORT).show();
				Intent intentOK = new Intent();
				setResult(RESULT_REQUEST_AUTH, intentOK);
				finish();

				break;
			}
		}

	};

	public boolean onTouchEvent(MotionEvent event) {
		return false;
	};

}
