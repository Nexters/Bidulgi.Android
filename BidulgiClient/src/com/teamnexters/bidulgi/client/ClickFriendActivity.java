package com.teamnexters.bidulgi.client;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.request.BidulgiRequestCode;
import com.teamnexters.bidulgi.common.request.MessageRequestPacket;
import com.teamnexters.bidulgi.common.response.BidulgiResponseCode;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;

public class ClickFriendActivity extends UIHandlingActivity implements OnClickListener {

	Intent intent;
	static final int IMG_BIDOOLGIFRIEND = 1;
	static final int BTN_EDITEMAIL = 2;
	static final int TXT_FRIENDSADDRESS = 3;
	String address = "[320-839] 충청남도 논산시 연무읍 득안대로 504번길 사서함 76 - ";
	
	private long soldierId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		//setContentView(R.layout.activity_dialog_click_friend);
		
		RelativeLayout rl = new RelativeLayout(getApplicationContext());
		intent = getIntent();
		soldierId = intent.getLongExtra("id", -1);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
		rl.setLayoutParams(params);
		
		ImageView imgFriend = new ImageView(getApplicationContext());
		imgFriend.setId(IMG_BIDOOLGIFRIEND);
		Glide.with(this).load(intent.getExtras().getString("profilePhotoSrc")).transform(new CircleTransform(getApplicationContext())).into(imgFriend);
		imgFriend.setImageResource(intent.getExtras().getInt("icon"));
		
		
		

		
		
		RelativeLayout.LayoutParams imgFriendParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		imgFriendParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		imgFriendParams.addRule(RelativeLayout.CENTER_VERTICAL);
		imgFriend.setLayoutParams(imgFriendParams);
		
		Button btnEditEmail = new Button(getApplicationContext());
		btnEditEmail.setId(BTN_EDITEMAIL);
		btnEditEmail.setText("편지쓰기");
		btnEditEmail.setOnClickListener(this);
		
		
		RelativeLayout.LayoutParams btnEditEmailParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		btnEditEmailParams.addRule(RelativeLayout.BELOW, IMG_BIDOOLGIFRIEND);
		btnEditEmailParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		btnEditEmail.setLayoutParams(btnEditEmailParams);
		
		Log.d("aaaa", "친구 클릭 후 넘어온 연대는 " + Integer.parseInt(intent.getExtras().getString("regiment")));
		
		
		TextView txtFriendsAddress = new TextView(getApplicationContext());
		txtFriendsAddress.setId(TXT_FRIENDSADDRESS);
		
		
		switch(Integer.parseInt(intent.getExtras().getString("regiment"))){
		
			case 23:
				txtFriendsAddress.setText(address + "8");
				break;
			case 25:
				txtFriendsAddress.setText(address + "9");
				break;
			case 26:
				txtFriendsAddress.setText(address + "10");
				break;
			case 27:
				txtFriendsAddress.setText(address + "11");
				break;
			case 28:
				txtFriendsAddress.setText(address + "12");
				break;
			case 29:
				txtFriendsAddress.setText(address + "13");
				break;
			case 30:
				txtFriendsAddress.setText(address + "14");
				break;
				
				
		}
		
		RelativeLayout.LayoutParams txtFriendsAddressParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		txtFriendsAddressParams.addRule(RelativeLayout.BELOW, BTN_EDITEMAIL);
		txtFriendsAddressParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		txtFriendsAddress.setLayoutParams(txtFriendsAddressParams);
		
		rl.addView(imgFriend);
		rl.addView(btnEditEmail);
		rl.addView(txtFriendsAddress);
		
		setContentView(rl);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case BTN_EDITEMAIL:
			lockUI();
			MessageRequestPacket messageRequest = new MessageRequestPacket();
			messageRequest.setArticlePassword("1234");
			messageRequest.setArticleText("군생활 잘하세요 ^^");
			messageRequest.setArticleTitle("군생활 잘하세요 ^^");
			messageRequest.setSoldierId(soldierId);
			messageRequest.setRequestCode(BidulgiRequestCode.REQUEST_SEND_MESSAGE);
			HttpRequestThread.getInstance().addRequest(messageRequest);
			break;
		}
	}

	@Override
	public void onHandleUI(BidulgiResponsePacket response) {
		switch (response.getResponseCode()) {
		case BidulgiResponseCode.RESPONSE_SEND_MESSAGE_FAIL:
			unlockUI();
			Toast.makeText(this, "편지보내기에 실패하였습니다.",Toast.LENGTH_SHORT).show();
			break;
		case BidulgiResponseCode.RESPONSE_SEND_MESSAGE_SUCCESS:
			unlockUI();
			Toast.makeText(this, "편지보내기에 성공하였습니다.",Toast.LENGTH_SHORT).show();
			break;
		}
	}
}
