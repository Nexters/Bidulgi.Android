package com.teamnexters.bidulgi.client;

import android.support.v7.app.ActionBarActivity;
import android.text.Html.ImageGetter;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.teamnexters.bidulgi.glide.CircleTransform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ClickFriendActivity extends Activity {

	Intent intent;
	static final int IMG_BIDOOLGIFRIEND = 1;
	static final int BTN_EDITEMAIL = 2;
	static final int TXT_FRIENDSADDRESS = 3;
	String address = "[320-839] 충청남도 논산시 연무읍 득안대로 504번길 사서함 76 - ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		//setContentView(R.layout.activity_dialog_click_friend);
		
		RelativeLayout rl = new RelativeLayout(getApplicationContext());
		intent = getIntent();
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
	

}
