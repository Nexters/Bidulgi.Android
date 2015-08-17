package com.teamnexters.bidulgi.client;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.data.SoldierData;
import com.teamnexters.bidulgi.common.request.BidulgiRequestCode;
import com.teamnexters.bidulgi.common.request.CommonRequestPacket;
import com.teamnexters.bidulgi.common.request.LongRequestPacket;
import com.teamnexters.bidulgi.common.response.BidulgiResponseCode;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;
import com.teamnexters.bidulgi.common.response.SoldierListResponsePacket;
import com.teamnexters.bidulgi.fragments.BidoolgiFreinds;
import com.teamnexters.bidulgi.fragments.BidoolgiMail;
import com.teamnexters.bidulgi.fragments.BidoolgiMailList;
import com.teamnexters.bidulgi.fragments.BidoolgiSetting;

public class ClientActivity extends BidoolgiFragmentActivity {
	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	BidoolgiFreinds fragmentFriends;
	Intent intent;

	static int CURRENT_PAGE = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_client);

		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		actionBar.setViewPager(mViewPager);

		fragmentFriends = (BidoolgiFreinds) mSectionsPagerAdapter.fragments.get(0);

		try {
			CommonRequestPacket request = new CommonRequestPacket();
			request.setRequestCode(BidulgiRequestCode.REQUEST_LIST_FRIEND_SOLDIER);
			HttpRequestThread.getInstance().addRequest(request);
		} catch (Exception e) {
			Log.d("error", "군인친구 목록 조회 에러" + e.toString());
		}
		/*
		 * CommonRequestPacket requestAdd = new CommonRequestPacket();
		 * requestAdd.setRequestCode(BidulgiRequestCode.
		 * REQUEST_ADD_FRIEND_SOLDIER);
		 * HttpRequestThreadForFriends.getInstance().addRequest(request);
		 */

		/*
		 * if(intent.getIntent().getExtras().getString("response").equals(
		 * "success")){
		 * fragmentFriends.deleteData(intent.getExtras().getInt("resPosition"));
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
		}
		return super.onOptionsItemSelected(item);

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
		case 1:
			if (data.getBooleanExtra("addFriend", false)) { // 친구추가 여부 확인
				// 친구추가 시 시행할 동작
				fragmentFriends.addData(data.getExtras().getString("name"), data.getExtras().getString("enterday"),
						data.getExtras().getString("regiment"), data.getExtras().getString("company"),
						data.getExtras().getString("platoon"), data.getExtras().getString("number"),
						data.getExtras().getLong("soldierId"));
				LongRequestPacket request = new LongRequestPacket();
				request.setValue(data.getExtras().getLong("soldierId"));
				request.setRequestCode(BidulgiRequestCode.REQUEST_ADD_FRIEND_SOLDIER);
				HttpRequestThread.getInstance().addRequest(request);

				Toast.makeText(getApplicationContext(), data.getExtras().getString("name") + " 비둘기가 추가 되었습니다.",
						Toast.LENGTH_SHORT).show();

			} else {
			}
		case 2:
			if (data.getBooleanExtra("sendEmail", false)) {
				Toast.makeText(getApplicationContext(), "편지가 전송 되었습니다.", Toast.LENGTH_SHORT).show();
			} else {

			}
		}

	}

	@Override
	public void onHandleUI(BidulgiResponsePacket response) {
		// TODO Auto-generated method stub
		
		Log.d("aaaa", "서버 회신은 " + response.getResponseCode());
		try{
		switch(response.getResponseCode()){
		case BidulgiResponseCode.RESPONSE_LIST_FRIEND_SOLDIER:
		SoldierListResponsePacket responseSoldierList = (SoldierListResponsePacket)response;
		List<SoldierData> data = responseSoldierList.getSoldierData();
		
		Log.d("aaaa", "군인친구 목록 첫번째 친구는"+ data.get(0).getName().toString());
		for(int i = 0 ; i < data.size() ; i++ ){
			fragmentFriends.addData(data.get(i).getName().toString() , data.get(i).getEnterDateString().toString() ,data.get(i).getRegiment().toString() ,data.get(i).getCompany().toString() ,data.get(i).getPlatoon().toString() ,data.get(i).getNumber().toString(),data.get(i).getSoldierId());
		}
		break;
		}
		} catch(Exception e){
			Log.d("error", "리스트 목록 받아올 때 에러 발생 에러 내용은 " + e.toString());
		}
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
		fragments.add(new BidoolgiMailList());
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
		Bundle args = new Bundle();
		args.putInt("position", position + 1); // tab의 인덱스는 항상 position으로
												// Bundle에 넘김.
		fragment.setArguments(args);

		return fragment;
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

}