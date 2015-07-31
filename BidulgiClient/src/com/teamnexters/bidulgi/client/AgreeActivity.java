package com.teamnexters.bidulgi.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;

public class AgreeActivity extends UIHandlingActivity {
	
	Intent intent;
	CheckBox checkBoxAgree;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agree);
		checkBoxAgree = (CheckBox)findViewById(R.id.checkBoxAgree);
		checkBoxAgree.setOnCheckedChangeListener(onCheckedChangeListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agree, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onHandleUI(BidulgiResponsePacket response) {
		// TODO Auto-generated method stub
		
	}
	// 이용약관 체크 시 회원가입 화면으로 이동
	OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener(){

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			
			intent = new Intent(getApplicationContext(),SignUpActivity.class);
			startActivity(intent);
			finish();
			
		}
		
	};
}



