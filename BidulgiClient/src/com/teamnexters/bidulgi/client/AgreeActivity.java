package com.teamnexters.bidulgi.client;

import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class AgreeActivity extends UIHandlingActivity implements OnClickListener {

	Intent intent;
	CheckBox checkBoxAgree;
	CheckBox checkBoxAgree2;
	TextView txtBidoolgi;
	TextView txtAgree;
	Button signUpButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("aaaa", "Agree onCreate");
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_agree);
			Typeface typeface = Typeface.createFromAsset(getAssets(), "NANUMGOTHIC.TTF");
			txtBidoolgi = (TextView) findViewById(R.id.txtBidoolgi);
			txtAgree = (TextView) findViewById(R.id.txtAgree);
			txtBidoolgi.setTypeface(typeface);
			txtAgree.setTypeface(typeface);
			checkBoxAgree = (CheckBox) findViewById(R.id.terms_checkbox1);
			checkBoxAgree2 = (CheckBox) findViewById(R.id.terms_checkbox2);
			signUpButton = (Button) findViewById(R.id.terms_signup_button);
			signUpButton.setOnClickListener(this);
		} catch (Exception e) {
			Log.d("aaaa", "Agree error :" + e.toString());
		}
		/*
		 * txtAgree = (TextView)findViewById(R.id.txtAgree);
		 * txtAgree.setMaxLines(5); txtAgree.setVerticalScrollBarEnabled(true);
		 * txtAgree.setMovementMethod(new ScrollingMovementMethod());
		 */
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

	@Override
	public void onClick(View v) {
		if (checkBoxAgree.isChecked() && checkBoxAgree2.isChecked()) {
			intent = new Intent(getApplicationContext(), SignUpActivity.class);
			startActivity(intent);
			finish();
		} else {
			Toast.makeText(this, "회원가입을 위해서는 약관에 동의해주세요.", Toast.LENGTH_SHORT).show();
		}

	}
}
