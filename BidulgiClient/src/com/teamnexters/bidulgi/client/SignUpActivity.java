package com.teamnexters.bidulgi.client;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
		//ȸ������ ������ �α��� ȭ������ �̵�
		if (response.getResponseCode() == 5) {
			Toast.makeText(getApplicationContext(), "ȸ������ ����",
					Toast.LENGTH_SHORT).show();
			SharedPreferences.Editor editor = pref.edit();
			editor.putString("email", editEmail.getText().toString());
			editor.commit();
			intent = new Intent(getApplicationContext(), ClientActivity.class);
			startActivity(intent);
			finish();
		}else{
			Toast.makeText(getApplicationContext(), "ȸ������ ����",
					Toast.LENGTH_SHORT).show();
		}

	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// �ùٸ� �Է����� Ȯ�� �� ȸ������ ����
			if (editEmail.getText().toString().length() != 0) {
				if (editPassWord.getText().toString().length() != 0) {
					if (editPassWord.getText().toString()
							.equals(editCheckPassWord.getText().toString())) {

						email = editEmail.getText().toString();
						passWord = editPassWord.getText().toString();
						
						LoginRequestPacket request = new LoginRequestPacket();
						request.setEmail(email);
						request.setPassword(passWord);
						request.setRequestCode(BidulgiRequestCode.REQUEST_REGISTRATION);
						HttpRequestThread.getInstance().addRequest(request);

						
					} else {
						Toast.makeText(getApplicationContext(),
								"��й�ȣ�� Ȯ�� �� �ּ���.", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "��й�ȣ�� �Է� �� �ּ���.",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(getApplicationContext(), "�̸����� �Է� �� �ּ���.",
						Toast.LENGTH_SHORT).show();
			}

			
		}

	};

}
