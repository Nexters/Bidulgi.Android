package com.teamnexters.bidulgi.client;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.request.BidulgiRequestCode;
import com.teamnexters.bidulgi.common.request.BidulgiRequestPacket;
import com.teamnexters.bidulgi.common.request.LongRequestPacket;
import com.teamnexters.bidulgi.common.response.BidulgiResponseCode;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;
import com.teamnexters.bidulgi.common.response.MessageListResponsePacket;
import com.teamnexters.bidulgi.glide.CircleTransform;
import com.teamnexters.bidulgi.message.MessageSendActivity;
import com.teamnexters.bidulgi.message.SoldierMessageAdapter;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ClickMailActivity extends UIHandlingActivity {

	Intent intent;
	private ImageView imgMailFriend;
	private TextView txtMailFriendName;
	private ListView mailListMessageListView;
	private SoldierMessageAdapter soldierMessageAdapter;
	private ActionBar actionBar;
	private Button btnLincEditMail;
	private long soldierId;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_click_mail);

		actionBar = getActionBar();
		actionBar.setTitle(null);
		actionBar.setIcon(null);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);
		intent = getIntent();
		soldierId = intent.getExtras().getLong("id");
		LongRequestPacket request = new LongRequestPacket();
		request.setValue(soldierId);
		request.setRequestCode(BidulgiRequestCode.REQUEST_LIST_SOLDIER_MESSAGE);
		HttpRequestThread.getInstance().addRequest(request);

		btnLincEditMail = (Button) findViewById(R.id.btnLincEditMail);
		btnLincEditMail.setOnClickListener(onClickListener);
	}

	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra("back", true);
		setResult(3, intent);
		finish();
	};

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
		Log.d("aaaa", "BidulgiResponseCode는" + response.getResponseCode());
		switch (response.getResponseCode()) {
		case BidulgiResponseCode.RESPONSE_LIST_SOLDIER_MESSAGE:

			MessageListResponsePacket messageListResponsePacket = (MessageListResponsePacket) response;
			soldierMessageAdapter = new SoldierMessageAdapter(messageListResponsePacket.getMessageData(),
					getApplication());

			intent = getIntent();
			imgMailFriend = (ImageView) findViewById(R.id.imgMailFriend);
			txtMailFriendName = (TextView) findViewById(R.id.txtMailFriendName);
			if (intent.getExtras().getString("profilePhotoSrc") == null) {
				imgMailFriend.setImageResource(R.drawable.icon_noprofile);
			} else {
				Glide.with(this).load(intent.getExtras().getString("profilePhotoSrc"))
						.transform(new CircleTransform(getApplicationContext())).into(imgMailFriend);
			}
			txtMailFriendName.setText(intent.getExtras().getString("name") + " 훈련병");
			mailListMessageListView = (ListView) findViewById(R.id.mailListMessageListView);

			mailListMessageListView.setAdapter(soldierMessageAdapter);

			break;
		}
	}
	
	OnClickListener onClickListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			intent = new Intent(getApplicationContext(),MessageSendActivity.class);
			intent.putExtra("soldier_id", soldierId);
			Log.d("aaaa", "군인 ID는 " + soldierId);
			startActivityForResult(intent, 1);
		}
		
	};
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		switch (resultCode) {
		case MessageSendActivity.RESULT_CODE_SEND_SUCCESS:
			Toast.makeText(this, "편지 전송에 성공하였습니다.", Toast.LENGTH_SHORT).show();
			break;
		case MessageSendActivity.RESULT_CODE_SEND_FAIL:
			Toast.makeText(this, "편지 전송에 실패하였습니다.", Toast.LENGTH_SHORT).show();
			break;
		case MessageSendActivity.RESULT_CODE_SEND_NICE_AUTH_REQUIRE:
			Toast.makeText(this, "본인 인증이 필요합니다.", Toast.LENGTH_SHORT).show();
			break;
		}
	};
}
