package com.teamnexters.bidulgi.client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

		pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
		Log.d("aaa", "������� ����");
		if (pref.contains("email")) {
			Log.d("aaa", "pref ���� " + pref.getString("email", "Nothing"));
			Log.d("aaa","if�� ����");
			intent = new Intent(getApplicationContext(), ClientActivity.class);
			startActivity(intent);
			finish();
		} else {
			Log.d("aaa","else�� ����");
			try{
			setContentView(R.layout.activity_main);
			editEmail = (EditText) findViewById(R.id.editEmail);
			editPassWord = (EditText) findViewById(R.id.editPassWord);
			btnLogin = (Button) findViewById(R.id.btnLogin);
			btnSignUp = (Button) findViewById(R.id.btnSignUp);

			btnLogin.setOnClickListener(onClickListener);
			btnSignUp.setOnClickListener(onClickListener);
			}catch(Exception e){
				Log.d("aaa", e.toString());
			}
		}

	}

	@Override
	public void onHandleUI(BidulgiResponsePacket response) {
		if (response.getResponseCode() == 2) {
			Toast.makeText(getApplicationContext(), "�α��� ����",
					Toast.LENGTH_SHORT).show();
			SharedPreferences.Editor editor = pref.edit();
			editor.putString("email", editEmail.getText().toString());
			editor.commit();
			intent = new Intent(getApplicationContext(), ClientActivity.class);
			startActivity(intent);
			finish();
		} else {
			Toast.makeText(getApplicationContext(), "�α��� ����",
					Toast.LENGTH_SHORT).show();
		}
	}

	protected OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// �α��� ��ư Ŭ���� �ùٸ� �Է� ���� Ȯ�� �� �α���
			if (v.getId() == R.id.btnLogin) {
				if (editEmail.getText().toString().length() != 0) {
					if (editPassWord.getText().toString().length() != 0) {
						email = editEmail.getText().toString();
						passWord = editPassWord.getText().toString();

						LoginRequestPacket request = new LoginRequestPacket();
						request.setEmail(email);
						request.setPassword(passWord);
						request.setRequestCode(BidulgiRequestCode.REQUEST_LOGIN);
						HttpRequestThread.getInstance().addRequest(request);

					} else {
						Toast.makeText(getApplicationContext(), "��й�ȣ�� �Է��ϼ���.",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "�̸����� �Է��ϼ���.",
							Toast.LENGTH_SHORT).show();
				}

				// ȸ������ ��ư Ŭ�� �� �̿��� ȭ������ �̵�
			} else if (v.getId() == R.id.btnSignUp) {
				intent = new Intent(getApplicationContext(),
						AgreeActivity.class);
				startActivity(intent);
			}

		}

	};

}
