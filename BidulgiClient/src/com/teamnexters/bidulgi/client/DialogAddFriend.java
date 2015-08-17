package com.teamnexters.bidulgi.client;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.data.SoldierData;
import com.teamnexters.bidulgi.common.request.BidulgiRequestCode;
import com.teamnexters.bidulgi.common.request.SoldierRequestPacket;
import com.teamnexters.bidulgi.common.response.BidulgiResponseCode;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;
import com.teamnexters.bidulgi.common.response.SoldierResponsePacket;

public class DialogAddFriend extends UIHandlingActivity {

	EditText editName;
	EditText editBirthDay;
	EditText editDate;
	Button btnAddFriend;
	Button btnNotAddFriend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_add_friend);

		editName = (EditText) findViewById(R.id.editName);
		editBirthDay = (EditText)findViewById(R.id.editBirthDay);
		editDate = (EditText) findViewById(R.id.editDate);
		btnAddFriend = (Button) findViewById(R.id.btnAddFriend);
		btnNotAddFriend = (Button) findViewById(R.id.btnNotAddFriend);
		
		btnAddFriend.setOnClickListener(onClickListener);
		btnNotAddFriend.setOnClickListener(onClickListener);

	}
	
	public void onBackPressed() {
		Intent resIntent = new Intent();
		resIntent.putExtra("addFriend", false);
		setResult(1, resIntent);
		finish();
	};

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Intent resIntent = new Intent();

			switch (v.getId()) {
			case R.id.btnAddFriend:
				
				SoldierRequestPacket request = new SoldierRequestPacket();
				request.setName(editName.getText().toString());
				request.setBirthString( editBirthDay.getText().toString());
				request.setEnterDateString(editDate.getText().toString());
				Log.d("aaaa", "군인 정보는 " + editName.getText().toString());
				request.setRequestCode(BidulgiRequestCode.REQUEST_SEARCH_SOLDIER);
				HttpRequestThread.getInstance().addRequest(request);
				/*resIntent.putExtra("addFriend", true);
				resIntent.putExtra("name", editName.getText().toString());
				resIntent.putExtra("birthday", editBirthDay.getText().toString());
				resIntent.putExtra("date", editDate.getText().toString());*/
				Log.d("aaaa", "추가할 친구 이름은 " + editName.getText().toString());
				break;
			case R.id.btnNotAddFriend:
				resIntent.putExtra("addFriend", false);
				setResult(1, resIntent);
				finish();
				break;
			}

			//setResult(1, resIntent);
			//finish();
		}
	};

	@Override
	public void onHandleUI(BidulgiResponsePacket response) {
		// TODO Auto-generated method stub
		Log.d("aaaa", "서버 회신은 " + response.getResponseCode());
		Intent resIntent = new Intent();
		switch(response.getResponseCode()){
		
		case BidulgiResponseCode.RESPONSE_SOLDIER_INFO:
			SoldierResponsePacket responseSoldier = (SoldierResponsePacket)response;
			SoldierData data = responseSoldier.getSoldierData();
				
			
			Log.d("aaaa", data.getBirthString() + " " + data.getName() + " " + data.getCompany());
			Log.d("aaaa", "사진 URL은 " + data.getProfilePhotoSrc());
			resIntent.putExtra("addFriend", true);
			resIntent.putExtra("name", data.getName());
			resIntent.putExtra("profilePhotoSrc", data.getProfilePhotoSrc());
			resIntent.putExtra("birthday", data.getBirthString());
			resIntent.putExtra("enterday", data.getEnterDateString());
			resIntent.putExtra("regiment", data.getRegiment());
			resIntent.putExtra("company", data.getCompany());
			resIntent.putExtra("platoon", data.getPlatoon());
			resIntent.putExtra("number", data.getNumber());
			resIntent.putExtra("soldierId", data.getSoldierId());
			setResult(1, resIntent);
			finish();
			break;
		case BidulgiResponseCode.RESPONSE_SOLDIER_SEARCH_FAIL:
			Toast.makeText(getApplicationContext(), "해당 훈련병 정보가 없습니다.", Toast.LENGTH_SHORT).show();
			break;
		}
	}



}
