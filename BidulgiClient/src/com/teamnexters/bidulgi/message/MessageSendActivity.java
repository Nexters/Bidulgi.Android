package com.teamnexters.bidulgi.message;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teamnexters.bidulgi.client.R;
import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.request.BidulgiRequestCode;
import com.teamnexters.bidulgi.common.request.MessageRequestPacket;
import com.teamnexters.bidulgi.common.response.BidulgiResponseCode;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;

public class MessageSendActivity extends UIHandlingActivity implements OnClickListener {
	public static final String INTENT_KEY_SOLDIER_ID = "soldier_id";
	public static final int RESULT_CODE_SEND_SUCCESS = 1000;
	public static final int RESULT_CODE_SEND_FAIL = 1001;
	public static final int RESULT_CODE_SEND_NICE_AUTH_REQUIRE = 1002;
	
	
	private Button submitButton;
	private EditText titleEditText, contentEditText, passwordEditText;
	private long soldierId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_send);
		submitButton = (Button) findViewById(R.id.sendMessageSubmitButton);
		submitButton.setOnClickListener(this);

		titleEditText = (EditText) findViewById(R.id.sendMessageTitleEditText);
		contentEditText = (EditText) findViewById(R.id.sendMessageContentEditText);
		passwordEditText = (EditText) findViewById(R.id.sendMessagePasswordEditText);
		
		soldierId = getIntent().getLongExtra(INTENT_KEY_SOLDIER_ID,-1);
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sendMessageSubmitButton:
			lockUI();
			MessageRequestPacket messageRequest = new MessageRequestPacket();
			messageRequest.setArticlePassword(passwordEditText.getText().toString());
			messageRequest.setArticleText(contentEditText.getText().toString());
			messageRequest.setArticleTitle(titleEditText.getText().toString());
			messageRequest.setSoldierId(soldierId);
			messageRequest.setRequestCode(BidulgiRequestCode.REQUEST_SEND_MESSAGE);
			HttpRequestThread.getInstance().addRequest(messageRequest);
			break;

		default:
			break;
		}
	}

}
