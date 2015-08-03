package com.teamnexters.bidulgi.client;

import android.support.v7.app.ActionBarActivity;
import android.text.Html.ImageGetter;
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

public class ClickFriendActivity extends Activity {

	Intent intent;
	static final int IMG_BIDOOLGIFRIEND = 1;
	static final int BTN_EDITEMAIL = 2;

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
		
		rl.addView(imgFriend);
		rl.addView(btnEditEmail);
		
		setContentView(rl);
	}
	


	
}
