package com.teamnexters.bidulgi.client;

import android.support.v7.app.ActionBarActivity;

import com.bumptech.glide.Glide;
import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.response.BidulgiResponseCode;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;
import com.teamnexters.bidulgi.glide.CircleTransform;
import com.teamnexters.bidulgi.message.SoldierMessageAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ClickMailActivity extends UIHandlingActivity {

	Intent intent;
	private ImageView imgMailFriend;
	private TextView txtMailFriendName;
	private ListView mailListMessageListView;
	private SoldierMessageAdapter soldierMessageAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_click_mail);

		intent = getIntent();
		imgMailFriend = (ImageView) findViewById(R.id.imgMailFriend);
		txtMailFriendName = (TextView) findViewById(R.id.txtMailFriendName);
		
		Glide.with(this).load(intent.getExtras().getString("profilePhotoSrc")).transform(new CircleTransform(getApplicationContext())).into(imgMailFriend);
		txtMailFriendName.setText(intent.getExtras().getString("name"));
		mailListMessageListView = (ListView) findViewById(R.id.mailListMessageListView);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.click_mail, menu);
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

		switch(response.getResponseCode()){
		case BidulgiResponseCode.RESPONSE_LIST_SOLDIER_MESSAGE:
		mailListMessageListView.setAdapter(soldierMessageAdapter);
		break;
		}
	}
}
