package com.teamnexters.bidulgi.client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.request.BidulgiRequestCode;
import com.teamnexters.bidulgi.common.request.LoginRequestPacket;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;

public class SignUpActivity extends UIHandlingActivity {
	EditText editEmail;
	EditText editPassWord;
	EditText editCheckPassWord;
	Button btnSignUpOk;

	String email;
	String passWord;

	Intent intent;
	private SharedPreferences pref;
	private static final int RESPONSE_REGISTRATION_FAIL = 4;
	private static final int RESPONSE_REGISTRATION_SUCCESS = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		editEmail = (EditText) findViewById(R.id.editEmail);
		editPassWord = (EditText) findViewById(R.id.editPassWord);
		editCheckPassWord = (EditText) findViewById(R.id.editCheckPassWord);
		btnSignUpOk = (Button) findViewById(R.id.btnSignUpOk);
		btnSignUpOk.setOnClickListener(onClickListener);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
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
		// 회원가입 성공시 로그인 화면으로 이동
		try {
			if (response.getResponseCode() == RESPONSE_REGISTRATION_SUCCESS) {
				Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();
				Log.d("aaa", "회원가입 성공");
				pref = getSharedPreferences("email", Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = pref.edit();
				editor.putString("email", editEmail.getText().toString());
				Log.d("aaa", "회원가입 email은 " + editEmail.getText().toString());
				editor.commit();

				intent = new Intent(getApplicationContext(), ClientActivity.class);
				startActivity(intent);
				finish();
			} else if (response.getResponseCode() == RESPONSE_REGISTRATION_FAIL) {
				Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {

			Log.d("aaa", "회원가입 서버 코드는 " +response.getResponseCode() );
			Log.d("aaa", e.toString());
		}

	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 올바른 입력인지 확인 후 회원가입 진행
			if (editEmail.getText().toString().length() != 0) {
				if (editPassWord.getText().toString().length() != 0) {
					if (editPassWord.getText().toString().equals(editCheckPassWord.getText().toString())) {

						email = editEmail.getText().toString();
						passWord = editPassWord.getText().toString();

						LoginRequestPacket request = new LoginRequestPacket();
						request.setEmail(email);
						request.setPassword(passWord);
						request.setRequestCode(BidulgiRequestCode.REQUEST_REGISTRATION);
						HttpRequestThread.getInstance().addRequest(request);

					} else {
						Toast.makeText(getApplicationContext(), "비밀번호를 확인 해 주세요.", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "비밀번호를 입력 해 주세요.", Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(getApplicationContext(), "이메일을 입력 해 주세요.", Toast.LENGTH_SHORT).show();
			}

		}

	};

}