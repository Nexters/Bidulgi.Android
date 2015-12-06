package com.teamnexters.bidulgi.client;

import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.request.BidulgiRequestCode;
import com.teamnexters.bidulgi.common.request.LoginRequestPacket;
import com.teamnexters.bidulgi.common.response.BidulgiResponseCode;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;
import com.teamnexters.bidulgi.common.response.LoginResponsePacket;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends UIHandlingActivity {

	Intent intent;
	EditText editEmail;
	EditText editPassWord;
	Button btnLogin;
	Button btnSignUp;

	String email;
	String passWord;
	private SharedPreferences pref;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("aaaa", "로딩 후 들어옴");

		pref = getSharedPreferences("email", Activity.MODE_PRIVATE);
		Log.d("pref", "pref 값은 " + pref.getString("email", "Nothing"));
		if (pref.contains("email")) {
			lockUI();
			setContentView(R.layout.activity_login_loading);
			((TextView)findViewById(R.id.login_loading_text)).setTypeface(Typeface.createFromAsset(getAssets(), "NANUMGOTHIC.TTF"));
			LoginRequestPacket request = new LoginRequestPacket();
			request.setEmail(pref.getString("email", null));
			request.setPassword(pref.getString("password", null));
			request.setRequestCode(BidulgiRequestCode.REQUEST_LOGIN);
			HttpRequestThread.getInstance().addRequest(request);
		} else {
			unlockUI();
			Typeface typeface = Typeface.createFromAsset(getAssets(), "NANUMGOTHIC.TTF");
			setContentView(R.layout.activity_main);
			editEmail = (EditText) findViewById(R.id.editEmail);
			editEmail.setTypeface(typeface);
			editPassWord = (EditText) findViewById(R.id.editPassWord);
			btnLogin = (Button) findViewById(R.id.btnLogin);
			btnSignUp = (Button) findViewById(R.id.btnSignUp);

			btnLogin.setOnClickListener(onClickListener);
			btnSignUp.setOnClickListener(onClickListener);
		}

	}

	@Override
	public void onHandleUI(BidulgiResponsePacket response) {
		Log.d("aaa", "res : " + String.valueOf(response.getResponseCode()));
		Log.d("aaa", "val : " + String.valueOf(BidulgiResponseCode.RESPONSE_LOGIN_SUCCESS));
		switch (response.getResponseCode()) {
		case BidulgiResponseCode.RESPONSE_LOGIN_SUCCESS:
			LoginUserInfo.getInstance().setLoginData(((LoginResponsePacket) response).getLoginData());
			Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
			if (editEmail != null) {
				pref = getSharedPreferences("email", Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = pref.edit();
				editor.putString("email", editEmail.getText().toString());
				editor.putString("password", editPassWord.getText().toString());
				Log.d("aaaa", "로그인 ID는 " + editEmail.getText().toString());
				editor.commit();
			}
			intent = new Intent(getApplicationContext(), ClientActivity.class);
			startActivity(intent);
			finish();
			break;
		case BidulgiResponseCode.RESPONSE_LOGIN_FAIL_AUTH:
			unlockUI();
			Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호를 잘못 입력하셨습니다. ", Toast.LENGTH_SHORT).show();
			break;
		case BidulgiResponseCode.RESPONSE_LOGIN_FAIL_KATC:
			unlockUI();
			Toast.makeText(getApplicationContext(), ".", Toast.LENGTH_SHORT).show();
		       AlertDialog.Builder builder = new AlertDialog.Builder(this);
		        builder.setTitle("로그인 실패");
		        builder.setMessage("현재 육군훈련소 홈페이지의 문제로 인해 로그인을 할 수 없습니다.\n잠시 후에 접속해주시기 바랍니다.");
		        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int id) {
		            	finish();
		            	System.exit(0);
		            }
		        });
		        builder.create().show();
			break;
		}
	}

	protected OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			// TODO Auto-generated method stub
			// 로그인 버튼 클릭시 올바른 입력 여부 확인 및 로그인
			if (v.getId() == R.id.btnLogin) {

				if (editEmail.getText().toString().length() != 0) {
					if (editPassWord.getText().toString().length() != 0) {
						lockUI();
						email = editEmail.getText().toString();
						passWord = editPassWord.getText().toString();

						LoginRequestPacket request = new LoginRequestPacket();
						request.setEmail(email);
						request.setPassword(passWord);
						request.setRequestCode(BidulgiRequestCode.REQUEST_LOGIN);
						HttpRequestThread.getInstance().addRequest(request);
						Log.d("aaaa", "버튼 클릭 후 서버에 request 보냄");

					} else {
						Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "이메일을 입력하세요.", Toast.LENGTH_SHORT).show();
				}

				// 회원가입 버튼 클릭 시 이용약관 화면으로 이동
			} else if (v.getId() == R.id.btnSignUp) {
				try {
					intent = new Intent(getApplicationContext(), AgreeActivity.class);
					startActivity(intent);
					finish();
				} catch (Exception e) {
					Log.d("aaaa", "error 2 : " + e.toString());
				}
			}

		}

	};

}