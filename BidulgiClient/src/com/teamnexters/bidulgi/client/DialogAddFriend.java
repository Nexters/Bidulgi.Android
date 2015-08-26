package com.teamnexters.bidulgi.client;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

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

	TextView txtName;
	TextView txtBirthDay;
	TextView txtDate;
	EditText editName;
	EditText editBirthDay;
	TextView editDate;
	Button btnAddFriend;
	Button btnNotAddFriend;
	final int CHOICE_DATE = 1;
	Calendar cal = new GregorianCalendar();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_add_friend);

		Typeface typeface = Typeface.createFromAsset(getAssets(), "NANUMGOTHIC.TTF");
		txtName = (TextView) findViewById(R.id.txtName);
		txtBirthDay = (TextView) findViewById(R.id.txtBirthDay);
		txtDate = (TextView) findViewById(R.id.txtDate);
		editName = (EditText) findViewById(R.id.editName);
		editBirthDay = (EditText) findViewById(R.id.editBirthDay);
		editDate = (TextView) findViewById(R.id.editDate);
		btnAddFriend = (Button) findViewById(R.id.btnAddFriend);
		btnNotAddFriend = (Button) findViewById(R.id.btnNotAddFriend);

		txtName.setTypeface(typeface);
		txtBirthDay.setTypeface(typeface);
		txtDate.setTypeface(typeface);
		btnAddFriend.setOnClickListener(onClickListener);
		btnNotAddFriend.setOnClickListener(onClickListener);
		editDate.setOnClickListener(onClickListener);

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
				request.setBirthString(editBirthDay.getText().toString());
				request.setEnterDateString(editDate.getText().toString());
				Log.d("aaaa", "군인 정보는 " + editName.getText().toString());
				request.setRequestCode(BidulgiRequestCode.REQUEST_SEARCH_SOLDIER);
				HttpRequestThread.getInstance().addRequest(request);
				/*
				 * resIntent.putExtra("addFriend", true);
				 * resIntent.putExtra("name", editName.getText().toString());
				 * resIntent.putExtra("birthday",
				 * editBirthDay.getText().toString());
				 * resIntent.putExtra("date", editDate.getText().toString());
				 */
				Log.d("aaaa", "추가할 친구 이름은 " + editName.getText().toString());
				break;
			case R.id.btnNotAddFriend:
				resIntent.putExtra("addFriend", false);
				setResult(1, resIntent);
				finish();
				break;

			case R.id.editDate:

				try {
					/*
					 * Calendar cal = new GregorianCalendar();
					 * //showDialog(CHOICE_DATE); new
					 * DatePickerDialog(getApplicationContext(),
					 * mDateSetListener,
					 * cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
					 * cal.get(Calendar.DAY_OF_MONTH)).show();
					 */
					showDialog(CHOICE_DATE);
					break;

				} catch (Exception e) {
					Log.d("aaaa", "달력 오류는 " + e.toString());

				}

			}

			// setResult(1, resIntent);
			// finish();
		}
	};

	protected android.app.Dialog onCreateDialog(int id) {

		switch (id) {
		case 1:

			return new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
					cal.get(Calendar.DAY_OF_MONTH));

		}
		return null;

	};

	@Override
	public void onHandleUI(BidulgiResponsePacket response) {
		// TODO Auto-generated method stub
		Log.d("aaaa", "서버 회신은 " + response.getResponseCode());
		Intent resIntent = new Intent();
		switch (response.getResponseCode()) {

		case BidulgiResponseCode.RESPONSE_SOLDIER_INFO:
			SoldierResponsePacket responseSoldier = (SoldierResponsePacket) response;
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

	DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			// TODO Auto-generated method stub
			if (monthOfYear + 1 < 10) {
				editDate.setText(new StringBuilder().append(year).append(0).append(monthOfYear + 1).append(dayOfMonth));
			} else {
				editDate.setText(new StringBuilder().append(year).append(monthOfYear + 1).append(dayOfMonth));
			}
		}
	};

}
