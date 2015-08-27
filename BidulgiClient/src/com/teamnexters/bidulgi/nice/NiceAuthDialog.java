package com.teamnexters.bidulgi.nice;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.signature.StringSignature;
import com.teamnexters.bidulgi.client.R;
import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.client.network.NetworkConfiguration;
import com.teamnexters.bidulgi.common.request.BidulgiRequestUri;
import com.teamnexters.bidulgi.common.request.NiceAuthRequestPacket;

public class NiceAuthDialog extends Dialog implements android.view.View.OnClickListener {

	private View infoLayout, imageLayout, smsLayout;
	private NiceAuthRequester requester;
	private Spinner mobileCoporationSpinner;
	private ImageView authImageView;
	private Button infoSubmitButton,imageSubmitButton,smsSubmitButton;
	private EditText nameEditText, birthYearEditText, birthMonthEditText, birthDayEditText, phoneNumber1EditText, phoneNumber2EditText, phoneNumber3EditText, imageEditText, smsEditText;
	private RadioGroup genderRadioGroup, nationRadioGroup;
	public NiceAuthDialog(Context context, NiceAuthRequester requester) {
		super(context);
		setCanceledOnTouchOutside(false);
		this.requester = requester;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_nice_auth);
		mobileCoporationSpinner = (Spinner) findViewById(R.id.niceAuthMobileCoporationSpinner);
		infoSubmitButton = (Button) findViewById(R.id.niceAuthInfoSubmitButton);
		imageSubmitButton = (Button) findViewById(R.id.niceAuthImageSubmitButton);
		smsSubmitButton = (Button) findViewById(R.id.niceAuthSMSSubmitButton);
		nameEditText = (EditText) findViewById(R.id.niceAuthNameEditText);
		birthYearEditText = (EditText) findViewById(R.id.niceAuthBirthYearEditText);
		birthMonthEditText = (EditText) findViewById(R.id.niceAuthBirthMonthEditText);
		birthDayEditText = (EditText) findViewById(R.id.niceAuthBirthDayEditText);
		phoneNumber1EditText = (EditText) findViewById(R.id.niceAuthPhoneNumber1EditText);
		phoneNumber2EditText = (EditText) findViewById(R.id.niceAuthPhoneNumber2EditText);
		phoneNumber3EditText = (EditText) findViewById(R.id.niceAuthPhoneNumber3EditText);
		genderRadioGroup = (RadioGroup) findViewById(R.id.niceAuthGenderRadioGroup);
		nationRadioGroup = (RadioGroup) findViewById(R.id.niceAuthNationRadioGroup);
		
		authImageView = (ImageView) findViewById(R.id.niceAuthAuthImageView);
		imageEditText = (EditText) findViewById(R.id.niceAuthImageEditText);
		
		smsEditText = (EditText) findViewById(R.id.niceAuthSMSEditText);
		
		infoLayout = findViewById(R.id.niceAuthInfoLayout);
		imageLayout = findViewById(R.id.niceAuthImageLayout);
		smsLayout = findViewById(R.id.niceAuthSMSLayout);
		
		infoSubmitButton.setOnClickListener(this);
		imageSubmitButton.setOnClickListener(this);
		smsSubmitButton.setOnClickListener(this);
	}

	public void showAuthImage() {
		infoLayout.setVisibility(View.GONE);
		imageLayout.setVisibility(View.VISIBLE);
		GlideUrl glideUrl = new GlideUrl(NetworkConfiguration.getHost()+BidulgiRequestUri.REQUEST_NICE_AUTH_IMAGE, new Headers() {
			
			@Override
			public Map<String, String> getHeaders() {
				Map<String,String> returnMap = new HashMap<String, String>();
				returnMap.put("Cookie", "JSESSIONID="+HttpRequestThread.getInstance().getJSessionId());
				return returnMap;
			}
		});
		Glide.with(getContext()).load(glideUrl).signature(new StringSignature(UUID.randomUUID().toString())).into(authImageView);
	}

	public void listenSMS() {
		imageLayout.setVisibility(View.GONE);
		smsLayout.setVisibility(View.VISIBLE);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.niceAuthInfoSubmitButton:
			NiceAuthRequestPacket request = new NiceAuthRequestPacket();
			request.setBirthDate1(birthYearEditText.getText().toString());
			request.setBirthDate2(birthMonthEditText.getText().toString());
			request.setBirthDate3(birthDayEditText.getText().toString());
			
			request.setGender(genderRadioGroup.getCheckedRadioButtonId()==R.id.niceAuthGenderMale?1:0);
		/*
		<string-array name="mobile_co">
	        <item>SKT</item>
	        <item>KT</item>
	        <item>LGU+</item>
	        <item>SKT(알뜰폰)</item>
	        <item>KT(알뜰폰)</item>
	        <item>LGU+(알뜰폰)</item>
	    </string-array>
	    */
			switch (mobileCoporationSpinner.getSelectedItemPosition()) {
			case 0:
				request.setMobileCorporation("SKT");
				break;
			case 1:
				request.setMobileCorporation("KTF");
				break;
			case 2:
				request.setMobileCorporation("LGT");
				break;
			case 3:
				request.setMobileCorporation("SKM");
				break;
			case 4:
				request.setMobileCorporation("KTM");
				break;
			case 5:
				request.setMobileCorporation("LGM");
				break;
			}
			request.setMobileno1(phoneNumber1EditText.getText().toString());
			request.setMobileno2(phoneNumber2EditText.getText().toString());
			request.setMobileno3(phoneNumber3EditText.getText().toString());
			request.setName(nameEditText.getText().toString());
			request.setNationalInfo(nationRadioGroup.getCheckedRadioButtonId()==R.id.niceAuthNationLocal?0:1);
			requester.startNiceAuth(request);
			break;

		case R.id.niceAuthImageSubmitButton:
			requester.sendNiceSMS(imageEditText.getText().toString());
			break;

		case R.id.niceAuthSMSSubmitButton:
			requester.notifySMSNumber(smsEditText.getText().toString());
			break;
		}
	}

}
