package com.teamnexters.bidulgi.client;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DialogLongClickFriend extends Activity {

	Intent intent;
	private Button btnDelete;
	private Button btnNotDelete;
	private TextView txtAlert;

	int position;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_long_click_friend);
		
		
		btnDelete = (Button)findViewById(R.id.btnDelete);
		btnNotDelete = (Button)findViewById(R.id.btnNotDelete);
		
		Typeface typeface = Typeface.createFromAsset(getAssets(), "NANUMGOTHIC.TTF");
		txtAlert = (TextView)findViewById(R.id.txtAlert);
		txtAlert.setTypeface(typeface);
		
	//	btnDelete.setOnClickListener(onClickDelete);
	//	btnNotDelete.setOnClickListener(onClickNotDelete);
		
		btnDelete.setOnClickListener(onClickListener);
		btnNotDelete.setOnClickListener(onClickListener);
		
		intent = getIntent();
		position = intent.getExtras().getInt("position");
		
		
	}

	public void onBackPressed() {
		Intent resIntent = new Intent();
		resIntent.putExtra("delete", false);
		setResult(0, resIntent);
		finish();
	};
	
	OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent resIntent = new Intent();
			
			switch(v.getId()){
			case R.id.btnDelete:
					resIntent.putExtra("position", position);
					resIntent.putExtra("delete", true);
				break;
			case R.id.btnNotDelete:
				resIntent.putExtra("delete", false);
				break;
			}
			Log.d("aaaa", "다이얼로그에서 받은 position 값은 " + position);
			setResult(0, resIntent);
			finish();
		}
	};
	
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	};
/*	OnClickListener onClickDelete = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			intent = getIntent();
			int position = intent.getExtras().getInt("position");
			Log.d("aaaa", "position 값은 " + position);
			intent.putExtra("resPosition", position);
			intent.putExtra("response", "success");
			/*intent = new Intent(getApplicationContext(),ExamFragment1.class);
			startActivity(intent);
			MainActivity.
			finish();
			
		}
		
	};
	
	OnClickListener onClickNotDelete = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
			
		}
		
	};*/
}
