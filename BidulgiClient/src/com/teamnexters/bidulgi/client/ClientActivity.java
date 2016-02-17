package com.teamnexters.bidulgi.client;

import java.util.ArrayList;
import java.util.List;

import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.client.session.KATCSessionKeeper;
import com.teamnexters.bidulgi.common.data.SoldierData;
import com.teamnexters.bidulgi.common.request.BidulgiRequestCode;
import com.teamnexters.bidulgi.common.request.CommonRequestPacket;
import com.teamnexters.bidulgi.common.request.LongRequestPacket;
import com.teamnexters.bidulgi.common.request.NiceAuthRequestPacket;
import com.teamnexters.bidulgi.common.request.StringRequestPacket;
import com.teamnexters.bidulgi.common.response.ArticleListResponsePacket;
import com.teamnexters.bidulgi.common.response.BidulgiResponseCode;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;
import com.teamnexters.bidulgi.common.response.LoginResponsePacket;
import com.teamnexters.bidulgi.common.response.MessageListResponsePacket;
import com.teamnexters.bidulgi.common.response.SoldierListResponsePacket;
import com.teamnexters.bidulgi.fragments.BidoolgiBoard;
import com.teamnexters.bidulgi.fragments.BidoolgiFreinds;
import com.teamnexters.bidulgi.fragments.BidoolgiMail;
import com.teamnexters.bidulgi.fragments.BidoolgiSetting;
import com.teamnexters.bidulgi.nice.NiceAuthDialog;
import com.teamnexters.bidulgi.nice.NiceAuthRequester;
import com.teamnexters.bidulgi.nice.NiceSMSCallBack;
import com.teamnexters.bidulgi.nice.SMSReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class ClientActivity extends BidoolgiFragmentActivity implements NiceAuthRequester, NiceSMSCallBack, BidoolgiBoard.ArticleLoadListener {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	private NiceAuthDialog niceAuthDialog;
	private BroadcastReceiver smsReceiver = new SMSReceiver(this);
	BidoolgiFreinds fragmentFriends;
	BidoolgiMail fragmentMail;
	BidoolgiBoard fragmentBoard;
	Intent intent;
	private boolean isReload = false;

	static int CURRENT_PAGE = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		KATCSessionKeeper.startSession();
		setContentView(R.layout.activity_client);
		try {
			mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager	());
			mViewPager = (ViewPager) findViewById(R.id.pager);
			mViewPager.setAdapter(mSectionsPagerAdapter);
			actionBar.setViewPager(mViewPager);

			fragmentFriends = (BidoolgiFreinds) mSectionsPagerAdapter.fragments.get(0);
			fragmentMail = (BidoolgiMail) mSectionsPagerAdapter.fragments.get(1);
			fragmentBoard = (BidoolgiBoard) mSectionsPagerAdapter.fragments.get(2);

			CommonRequestPacket request = new CommonRequestPacket();
			request.setRequestCode(BidulgiRequestCode.REQUEST_LIST_FRIEND_SOLDIER);
			HttpRequestThread.getInstance().addRequest(request);

			CommonRequestPacket requestMailList = new CommonRequestPacket();
			requestMailList.setRequestCode(BidulgiRequestCode.REQUEST_LIST_USER_MESSAGE);
			HttpRequestThread.getInstance().addRequest(requestMailList);

			LongRequestPacket requestArticleList = new LongRequestPacket();
			requestArticleList.setValue(Integer.MAX_VALUE);
			requestArticleList.setRequestCode(BidulgiRequestCode.REQUEST_LIST_ARTICLE);
			HttpRequestThread.getInstance().addRequest(requestArticleList);

			IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

			registerReceiver(smsReceiver, intentFilter);
		} catch (Exception e) {
			Log.d("error", "ClientActivity 시작 에러 내용은 " + e.toString());
		}

		try {
			Intent intent = getIntent();
			if (intent.hasExtra("requestAuth")) {
				if (intent.getExtras().getBoolean("requestAuth")) {

					// mViewPager.setCurrentItem(3);
					onNiceAuthStart();
				}
			}
		} catch (Exception e) {
			Log.d("error", "ClientActivity 에러 내용은 " + e.toString());
		}

		/*
		 * CommonRequestPacket requestAdd = new CommonRequestPacket();
		 * requestAdd.setRequestCode(BidulgiRequestCode.
		 * REQUEST_ADD_FRIEND_SOLDIER);
		 * HttpRequestThreadForFriends.getInstance().addRequest(request);
		 */

		/*
		 * if(intent.getIntent().getExtras().getString("response").equals(
		 * "success")){ fragmentFriends.deleteData(intent.getExtras().getInt(
		 * "resPosition"));
		 * 
		 * }
		 */

	}

	@Override
	protected void tabSelected(int i) {
		mViewPager.setCurrentItem(i);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Intent intent = new Intent(this, Activity.class);
		// startActivity(intent);
		int id = item.getItemId();
		if (id == R.id.itemPlusFriend) {
			intent = new Intent(getApplicationContext(), DialogAddFriend.class);
			startActivityForResult(intent, 1);

			// fragmentFriends.addData("양시영");

			/*
			 * intent = new Intent(getApplicationContext(), MainActivity.class);
			 * startActivity(intent);
			 */

			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("aaaa", "콜백 메소드 실행");
		switch (resultCode) {
		case 0:// request 코드랑 같은지 확인
			if (data.getBooleanExtra("delete", false)) { // 삭제 여부 확인
				// 삭제 시 시행할 동작

				fragmentFriends.deleteData(data.getExtras().getInt("position"));
				Log.d("aaaa", "삭제할 position은 " + data.getExtras().getInt("position"));

				Toast.makeText(getApplicationContext(), "비둘기가 둥지에서 떠났습니다.", Toast.LENGTH_SHORT).show();

			} else {
			}
			break;
		case 1:
			if (data.getBooleanExtra("addFriend", false)) { // 친구추가 여부 확인
				// 친구추가 시 시행할 동작
				fragmentFriends.addData((SoldierData) data.getExtras().getSerializable("added_soldier_data"));
				LongRequestPacket request = new LongRequestPacket();
				request.setValue(data.getExtras().getLong("soldierId"));
				request.setRequestCode(BidulgiRequestCode.REQUEST_ADD_FRIEND_SOLDIER);
				HttpRequestThread.getInstance().addRequest(request);

				Toast.makeText(getApplicationContext(), data.getExtras().getString("name") + " 비둘기가 추가 되었습니다.", Toast.LENGTH_SHORT).show();

			} else {
			}
			break;
		case 2:
			if (data.getBooleanExtra("sendEmail", false)) {
				Toast.makeText(getApplicationContext(), "편지가 전송 되었습니다.", Toast.LENGTH_SHORT).show();
			} else {

			}
			break;
		case 3:
			if (data.getBooleanExtra("back", false)) {
				mViewPager.setCurrentItem(1);
			} else {

			}
			break;

		case 4:
			if (data.getBooleanExtra("writeAricle", false)) {

				LongRequestPacket requestArticleList = new LongRequestPacket();
				requestArticleList.setValue(Integer.MAX_VALUE);
				requestArticleList.setRequestCode(BidulgiRequestCode.REQUEST_LIST_ARTICLE);
				HttpRequestThread.getInstance().addRequest(requestArticleList);

				mViewPager.setCurrentItem(2);
				Toast.makeText(getApplicationContext(), "게시판 글 등록이 되었습니다.", Toast.LENGTH_SHORT).show();
			} else {
				mViewPager.setCurrentItem(2);
				Toast.makeText(getApplicationContext(), "포인트가 부족하여 게시글을 작성할 수 없습니다.", Toast.LENGTH_SHORT).show();
			}
			break;

		case 5:
			if (data.getBooleanExtra("cancel", false)) {
				mViewPager.setCurrentItem(2);
				Toast.makeText(getApplicationContext(), "게시판 글 등록을 취소하였습니다.", Toast.LENGTH_SHORT).show();

			} else {

			}
			break;
		case 6:
			if (data.getBooleanExtra("writeComment", false)) {
				mViewPager.setCurrentItem(2);
				Toast.makeText(getApplicationContext(), "댓글 등록이 되었습니다.", Toast.LENGTH_SHORT).show();
			} else if (data.getBooleanExtra("backPress", false)) {
				mViewPager.setCurrentItem(2);
			} else {

			}
		}

	}

	@Override
	public void onHandleUI(BidulgiResponsePacket response) {
		switch (response.getResponseCode()) {
		case BidulgiResponseCode.RESPONSE_LIST_FRIEND_SOLDIER:

			Log.d("aaaa", "서버 회신은 " + response.getResponseCode());
			try {
				SoldierListResponsePacket responseSoldierList = (SoldierListResponsePacket) response;
				List<SoldierData> data = responseSoldierList.getSoldierData();

				Log.d("aaaa", "군인친구 목록 첫번째 친구는" + data.get(0).getName().toString());
				Log.d("aaaa", "군인 친구 목록 첫번째 친구 프로필 사진 URL은 " + data.get(0).getProfilePhotoSrc());
				for (SoldierData soldierData : data) {
					fragmentFriends.addData(soldierData);

				}
				SoldierInfoStore.getInstance().reload(data);
				unlockUI();
				break;
			} catch (Exception e) {
				Log.d("error", "리스트 목록 받아올 때 에러 발생 에러 내용은 " + e.toString());
			}
			break;
		case BidulgiResponseCode.RESPONSE_LIST_USER_MESSAGE:
			MessageListResponsePacket responseMessageList = (MessageListResponsePacket) response;
			fragmentMail.refreshList(responseMessageList.getMessageData(), this);
			break;
		case BidulgiResponseCode.RESPONSE_LIST_ARTICLE:
			
			if(isReload){
				ArticleListResponsePacket articleListResponse = (ArticleListResponsePacket) response;
				fragmentBoard.reloadArticle(articleListResponse.getArticleList());
				isReload = false;
				unlockUI();
			} else{
			ArticleListResponsePacket articleListResponse = (ArticleListResponsePacket) response;
			fragmentBoard.refreshList(articleListResponse.getArticleList(), this);
			}
			break;
		case BidulgiResponseCode.RESPONSE_NICE_AUTH_FAIL:
			unlockUI();
			Toast.makeText(this, "본인인증에 실패하였습니다.", Toast.LENGTH_SHORT).show();
			if (niceAuthDialog != null) {
				niceAuthDialog.dismiss();
				niceAuthDialog = null;
			}
			break;
		case BidulgiResponseCode.RESPONSE_NICE_AUTH_SUCCESS:
			unlockUI();
			LoginUserInfo.getInstance().setLoginData(((LoginResponsePacket) response).getLoginData());
			((BidoolgiSetting) mSectionsPagerAdapter.getItem(3)).onAuthSuccess();
			Toast.makeText(this, "본인인증에 성공하였습니다.", Toast.LENGTH_SHORT).show();
			if (niceAuthDialog != null) {
				niceAuthDialog.dismiss();
				niceAuthDialog = null;
			}
			break;
		case BidulgiResponseCode.RESPONSE_NICE_AUTH_IMAGE:
			unlockUI();
			Toast.makeText(this, "자동방지 확인 문자를 입력해주세요", Toast.LENGTH_SHORT).show();
			if (niceAuthDialog != null && niceAuthDialog.isShowing()) {
				niceAuthDialog.showAuthImage();
			}
			break;
		case BidulgiResponseCode.RESPONSE_NICE_SMS_SUCCESS:
			unlockUI();
			Toast.makeText(this, "본인확인용 SMS 문자를 입력해주세요", Toast.LENGTH_SHORT).show();
			if (niceAuthDialog != null && niceAuthDialog.isShowing()) {
				niceAuthDialog.listenSMS();
			}
			break;
		}
	}

	@Override
	public void startNiceAuth(NiceAuthRequestPacket request) {
		lockUI();
		request.setRequestCode(BidulgiRequestCode.REQUEST_START_NICE_AUTH);
		HttpRequestThread.getInstance().addRequest(request);
	}

	@Override
	public void sendNiceSMS(String authImageNumber) {
		lockUI();
		StringRequestPacket request = new StringRequestPacket();
		request.setRequestCode(BidulgiRequestCode.REQUEST_SEND_NICE_SMS);
		request.setValue(authImageNumber);
		HttpRequestThread.getInstance().addRequest(request);
	}

	@Override
	public void notifySMSNumber(String smsNumber) {
		lockUI();
		StringRequestPacket request = new StringRequestPacket();
		request.setRequestCode(BidulgiRequestCode.REQUEST_NOTIFY_SMS_NUMBER);
		request.setValue(smsNumber);
		HttpRequestThread.getInstance().addRequest(request);
	}

	public void onNiceAuthStart() {
		niceAuthDialog = new NiceAuthDialog(this, this);
		niceAuthDialog.show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(smsReceiver);
	}

	@Override
	public void onNiceSMSReceive(String authCode) {
		if (niceAuthDialog != null && niceAuthDialog.isShowing()) {
			niceAuthDialog.fillSMSCode(authCode);
		}
	}

	@Override
	public void onReload(long id) {
		// TODO Auto-generated method stub
		
		isReload = true;
		LongRequestPacket requestArticleList = new LongRequestPacket();
		requestArticleList.setValue(id);
		requestArticleList.setRequestCode(BidulgiRequestCode.REQUEST_LIST_ARTICLE);
		HttpRequestThread.getInstance().addRequest(requestArticleList);
		lockUI();
		
	}
}

/**
 * Fragment를 ViewPager에 적용시키기위한 아답터.
 */
class SectionsPagerAdapter extends FragmentPagerAdapter {

	// Tab에 들어가는 Fragment를 담는 ArrayList
	ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	private Context mContext;

	public SectionsPagerAdapter(FragmentManager fm) {
		super(fm);
		initFragments();
	}

	private void initFragments() {
		fragments.add(new BidoolgiFreinds());
		fragments.add(new BidoolgiMail());
		fragments.add(new BidoolgiBoard());
		fragments.add(new BidoolgiSetting());
	}

	/**
	 *
	 * @param position
	 *            tab이 선택된 위치
	 * @return 선택된 Fragment
	 */
	@Override
	public Fragment getItem(int position) {
		// 만약에 이상한숫자가 들어온다면 0으로 position을 바꿈. 이거없으면 ArrayIndexOutOfBound 날수있음
		if (position > fragments.size()) {
			position = 0;
		}

		Fragment fragment = fragments.get(position);
		// Bundle args = new Bundle();
		// args.putInt("position", position + 1); // tab의 인덱스는 항상 position으로
		// // Bundle에 넘김.
		// fragment.setArguments(args);

		return fragment;
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

}