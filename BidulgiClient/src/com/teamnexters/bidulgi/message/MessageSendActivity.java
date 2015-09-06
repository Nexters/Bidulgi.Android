package com.teamnexters.bidulgi.message;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.teamnexters.bidulgi.client.MessageSendCheckActivity;
import com.teamnexters.bidulgi.client.R;
import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.request.BidulgiRequestCode;
import com.teamnexters.bidulgi.common.request.LoginRequestPacket;
import com.teamnexters.bidulgi.common.request.MessageRequestPacket;
import com.teamnexters.bidulgi.common.response.BidulgiResponseCode;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;

public class MessageSendActivity extends UIHandlingActivity {
	public static final String INTENT_KEY_SOLDIER_ID = "soldier_id";
	public static final int RESULT_CODE_SEND_SUCCESS = 1000;
	public static final int RESULT_CODE_SEND_FAIL = 1001;
	public static final int RESULT_CODE_SEND_NICE_AUTH_REQUIRE = 1002;

	private EditText titleEditText, contentEditText, passwordEditText;
	private long soldierId;
	private TextView txtTitle;
	private TextView txtContent;
	private TextView txtPassword;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_send);
		Typeface typeface = Typeface.createFromAsset(getAssets(), "NANUMGOTHIC.TTF");
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(null);
		actionBar.setIcon(null);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);

		txtTitle = (TextView) findViewById(R.id.txtTitle);
		txtContent = (TextView) findViewById(R.id.txtContent);
		txtPassword = (TextView) findViewById(R.id.txtPassword);

		txtTitle.setTypeface(typeface);
		txtContent.setTypeface(typeface);
		txtPassword.setTypeface(typeface);

		titleEditText = (EditText) findViewById(R.id.sendMessageTitleEditText);
		contentEditText = (EditText) findViewById(R.id.sendMessageContentEditText);
		passwordEditText = (EditText) findViewById(R.id.sendMessagePasswordEditText);

		titleEditText.setTypeface(typeface);
		contentEditText.setTypeface(typeface);
		passwordEditText.setTypeface(typeface);

		soldierId = getIntent().getLongExtra(INTENT_KEY_SOLDIER_ID, -1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.send_mail_menu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.itemSendMail:

			if (titleEditText.getText().toString().length() != 0) {
				if (contentEditText.getText().toString().length() != 0) {
					if (passwordEditText.getText().toString().length() != 0) {
						
						try{
						Intent intent = new Intent(getApplicationContext(),MessageSendCheckActivity.class);
						startActivityForResult(intent, 1);
						} catch(Exception e){
							Log.d("error",  "메일보내기 확인 액티비티 에러 내용은 " + e.toString());
						}

						
						break;

					} else {
						Toast.makeText(getApplicationContext(), "비밀번호를 입력 해 주세요.", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "편지내용을 입력 해 주세요.", Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(getApplicationContext(), "제목을 입력 해 주세요.", Toast.LENGTH_SHORT).show();
			}

		}
		return super.onOptionsItemSelected(item);
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch(resultCode){
		
		case 1:
			lockUI();
			MessageRequestPacket messageRequest = new MessageRequestPacket();
			messageRequest.setArticlePassword(passwordEditText.getText().toString());
			messageRequest.setArticleText(contentEditText.getText().toString());
			messageRequest.setArticleTitle(titleEditText.getText().toString());
			messageRequest.setSoldierId(soldierId);
			messageRequest.setRequestCode(BidulgiRequestCode.REQUEST_SEND_MESSAGE);
			HttpRequestThread.getInstance().addRequest(messageRequest);
			break;
		case 2:
			Toast.makeText(getApplicationContext(), "편지보내기를 취소하였습니다.", Toast.LENGTH_SHORT).show();
			break;
		}
	}
	@Override
	public void onHandleUI(BidulgiResponsePacket response) {
		switch (response.getResponseCode()) {
		case BidulgiResponseCode.RESPONSE_SEND_MESSAGE_FAIL:
			unlockUI();
			setResult(RESULT_CODE_SEND_SUCCESS);
			finish();
			break;
		case BidulgiResponseCode.RESPONSE_SEND_MESSAGE_SUCCESS:
			unlockUI();
			setResult(RESULT_CODE_SEND_SUCCESS);
			finish();
			break;
		case BidulgiResponseCode.RESPONSE_REQUIRE_NICE_AUTH:
			unlockUI();
			setResult(RESULT_CODE_SEND_NICE_AUTH_REQUIRE);
			finish();
			break;
		}
	}

	/*
	 * public void onClick(View v) { switch (v.getId()) { case
	 * R.id.sendMessageSubmitButton: lockUI(); MessageRequestPacket
	 * messageRequest = new MessageRequestPacket();
	 * messageRequest.setArticlePassword(passwordEditText.getText().toString());
	 * messageRequest.setArticleText(contentEditText.getText().toString());
	 * messageRequest.setArticleTitle(titleEditText.getText().toString());
	 * messageRequest.setSoldierId(soldierId);
	 * messageRequest.setRequestCode(BidulgiRequestCode.REQUEST_SEND_MESSAGE);
	 * HttpRequestThread.getInstance().addRequest(messageRequest); break;
	 * 
	 * default: break; } }
	 */

}
