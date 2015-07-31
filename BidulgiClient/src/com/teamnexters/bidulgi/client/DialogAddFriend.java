package com.teamnexters.bidulgi.client;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DialogAddFriend extends Activity {

	EditText editName;
	EditText editDate;
	Button btnAddFriend;
	Button btnNotAddFriend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_add_friend);

		editName = (EditText) findViewById(R.id.editName);
		editDate = (EditText) findViewById(R.id.editDate);
		btnAddFriend = (Button) findViewById(R.id.btnAddFriend);
		btnNotAddFriend = (Button) findViewById(R.id.btnNotAddFriend);
		
		btnAddFriend.setOnClickListener(onClickListener);
		btnNotAddFriend.setOnClickListener(onClickListener);

	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Intent resIntent = new Intent();

			switch (v.getId()) {
			case R.id.btnAddFriend:
				resIntent.putExtra("addFriend", true);
				resIntent.putExtra("name", editName.getText().toString());
				resIntent.putExtra("date", editDate.getText().toString());
				Log.d("aaaa", "추가할 친구 이름은 " + editName.getText().toString());
				break;
			case R.id.btnNotAddFriend:
				resIntent.putExtra("addFriend", false);
				break;
			}

			setResult(1, resIntent);
			finish();
		}
	};

}
