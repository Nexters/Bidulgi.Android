package com.teamnexters.bidulgi.client;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;
import com.teamnexters.bidulgi.glide.CircleTransform;
import com.teamnexters.bidulgi.list.AlbumAdapter;
import com.teamnexters.bidulgi.message.MessageSendActivity;

public class ClickFriendActivity extends UIHandlingActivity implements OnClickListener {

	public static final int REQUEST_CODE_SEND_MESSAGE = 1000;
	
	public static final int REQUEST_CODE_AUTH_REQUEST = 2000;

	Intent intent;
	private ImageView imgFriend;
	private TextView txtFriendName;
	private TextView txtEnterDate;
	private Button btnEditEmail;
	private TextView txtFriendsAddress;
	private TextView txtFriendBirth;
	private GridView gridAlbum;
	private AlbumAdapter albumAdapter;
	private ArrayList<String> albumURL = new ArrayList<String>();
	//String address = "[320-839] 충청남도 논산시 연무읍 \r\n 득안대로 504번길 사서함 76 - ";

	ActionBar actionBar;

	private long soldierId;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_click_friend);

		actionBar = getActionBar();
		actionBar.setTitle(null);
		actionBar.setIcon(null);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setHomeButtonEnabled(false);
		intent = getIntent();
		soldierId = intent.getLongExtra("id", -1);

		imgFriend = (ImageView) findViewById(R.id.imgFriend);
		txtFriendName = (TextView) findViewById(R.id.txtFriendName);
		btnEditEmail = (Button) findViewById(R.id.btnEditMail);
		txtFriendsAddress = (TextView) findViewById(R.id.txtFriendsAddress);
		txtEnterDate = (TextView) findViewById(R.id.txtEnterDate);
		txtFriendBirth = (TextView) findViewById(R.id.txtBirth);
		gridAlbum = (GridView) findViewById(R.id.gridAlbum);
		
		
		if (intent.getExtras().getString("profilePhotoSrc") == null) {
			imgFriend.setImageResource(R.drawable.icon_noprofile);
		} else {
			Glide.with(this).load(intent.getExtras().getString("profilePhotoSrc"))
					.transform(new CircleTransform(getApplicationContext())).into(imgFriend);
		}
		
		//훈련사진 URL크기가 0 보다 크면(있으면)
		if(intent.getExtras().getInt("albumURLSize") > 0 ){
			for(int i = 0 ; i < intent.getExtras().getInt("albumURLSize") ; i++){
				
				//분대사진 URL을 ArrayList<String>에 추가
				Log.d("aaaa", "ClickFriendActivity에서 훈련사진 받아와서 URL 담는 중");
				albumURL.add(intent.getExtras().getString("albumURL"+i));
			}
			
			
		}
		
		//분대사진 그리드 뷰 Adapter 초기화 및 gridView에 연결
		albumAdapter = new AlbumAdapter(getApplicationContext(), albumURL);		
		gridAlbum.setAdapter(albumAdapter);
		gridAlbum.setOnItemClickListener(onAlbumItemClickListener);

		txtFriendName.setText(intent.getExtras().getString("name"));
		txtEnterDate.setText(intent.getExtras().getString("enterDate"));
		txtFriendBirth.setText(intent.getExtras().getString("birth"));
		Log.d("aaaa", "친구 클릭 후 넘어온 연대는 " + Integer.parseInt(intent.getExtras().getString("regiment")));
		Log.d("aaaa", "친구 클릭 후 넘어온 사진 URL은 " + intent.getExtras().getString("profilePhotoSrc"));

		switch (Integer.parseInt(intent.getExtras().getString("regiment"))) {

		case 23:
			//txtFriendsAddress.setText(address + "8 \r\n" +
			//intent.getExtras().getString("address"));
			// break;
		case 25:
			//txtFriendsAddress.setText(address + "9 \r\n" +
			//intent.getExtras().getString("address"));
			//break;
		case 26:
			//txtFriendsAddress.setText(address + "10 \r\n" +
			//intent.getExtras().getString("address"));
			//break;
		case 27:
			//txtFriendsAddress.setText(address + "11 \r\n" +
			//intent.getExtras().getString("address"));
			//break;
		case 28:
			//txtFriendsAddress.setText(address + "12 \r\n" +
			//intent.getExtras().getString("address"));
			//break;
		case 29:
			//txtFriendsAddress.setText(address + "13 \r\n" +
			//intent.getExtras().getString("address"));
			//break;
		case 30:
			//txtFriendsAddress.setText(address + "14 \r\n" +
			//intent.getExtras().getString("address"));
			txtFriendsAddress.setText(intent.getExtras().getString("address"));
			break;

		}

		btnEditEmail.setOnClickListener(this);

		/*
		 * RelativeLayout rl = new RelativeLayout(getApplicationContext());
		 * 
		 * RelativeLayout.LayoutParams imgFriendParams = new
		 * RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
		 * ViewGroup.LayoutParams.WRAP_CONTENT);
		 * imgFriendParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		 * imgFriendParams.addRule(RelativeLayout.CENTER_VERTICAL);
		 * imgFriend.setLayoutParams(imgFriendParams);
		 * 
		 * rl.addView(imgFriendParams); rl.addView(btnEditEmail);
		 * rl.addView(txtFriendsAddress);
		 * 
		 * setContentView(rl);
		 */
	}

	public void onBackPressed() {
		Intent intent = new Intent(getApplicationContext(), ClientActivity.class);

		startActivity(intent);
		finish();
	};
	
	OnItemClickListener onAlbumItemClickListener = new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(getApplicationContext(), ClickAlbumActivity.class);
			intent.putExtra("imgTrainingURL", albumURL.get(position));
			startActivity(intent);
			
		}

	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnEditMail:
			if (LoginUserInfo.getInstance().getLoginData().getName() == null) {

				//Toast.makeText(getApplicationContext(), "편지를 쓰기 위해서는 본인인증이 필요합니다.", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getApplicationContext(),RequestAuthActivity.class);
				startActivityForResult(intent, REQUEST_CODE_AUTH_REQUEST);
				

			} else {

				Intent i = new Intent(this, MessageSendActivity.class);
				i.putExtra(MessageSendActivity.INTENT_KEY_SOLDIER_ID, soldierId);
				startActivityForResult(i, REQUEST_CODE_SEND_MESSAGE);

			}

			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQUEST_CODE_SEND_MESSAGE:
			switch (resultCode) {
			case MessageSendActivity.RESULT_CODE_SEND_SUCCESS:
				Toast.makeText(this, "편지 전송에 성공하였습니다.", Toast.LENGTH_SHORT).show();
				break;
			case MessageSendActivity.RESULT_CODE_SEND_FAIL:
				Toast.makeText(this, "편지 전송에 실패하였습니다.", Toast.LENGTH_SHORT).show();
				break;
			case MessageSendActivity.RESULT_CODE_SEND_NICE_AUTH_REQUIRE:
				Toast.makeText(this, "본인 인증이 필요합니다.", Toast.LENGTH_SHORT).show();
				break;
			}
			break;
		case REQUEST_CODE_AUTH_REQUEST:
			switch(resultCode){
			case RequestAuthActivity.RESULT_REQUEST_AUTH:
				
				Intent intentOK = new Intent(getApplicationContext(), ClientActivity.class);
				intentOK.putExtra("requestAuth", true);
				startActivity(intentOK);
				finish();
				
				break;
				
			case RequestAuthActivity.RESULT_REQUEST_NO_AUTH:
				
				Toast.makeText(getApplicationContext(), "본인인증을 취소합니다.", Toast.LENGTH_SHORT).show();
				/*Intent intentNO = new Intent(getApplicationContext(), ClientActivity.class);
				intentNO.putExtra("requestAuth", false);
				startActivity(intentNO);
				finish();*/
				
				break;
			}
			break;
		}
		
		
	}

	@Override
	public void onHandleUI(BidulgiResponsePacket response) {

	}
}
