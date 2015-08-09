package com.teamnexters.bidulgi.client;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

public class BidoolgiFragmentActivity extends FragmentActivity {

	Intent intent;
	// ActionBar의 탭이 가리키고있는 인덱스
	protected int tabPosition = 0;
	private static int tabNum = 0;

	protected BidoolgiActionBar actionBar;

	/**
	 * ActionBar.TabListner 의 onTabSelected, onTabReSelected때 작동하는 콜백 메소드
	 *
	 * @param i
	 *            선택된 탭의 인덱스
	 */
	protected void tabSelected(int i) {
	}

	/**
	 * ActionBar.TabListner 의 onTabUnSelected 때 작동하는 콜백 메소드
	 *
	 * @param i
	 *            선택해제된 탭의 인덱스
	 */
	protected void tabUnselected(int i) {
	}

	@Override
	public void onCreate(Bundle saveInstance) {
		super.onCreate(saveInstance);
		actionBar = new BidoolgiActionBar(this);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.custom_actionbar, menu);
		actionBar.setTabListener(new ActionBar.TabListener() {

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				tabPosition = tab.getPosition();
				tabSelected(tabPosition);
				Log.d("aaaa", "호출된 탭의 위치는 " + tabPosition);


			}

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				tabUnselected(tabPosition);
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				tabPosition = tab.getPosition();
				tabSelected(tabPosition);
			}
		});

		return super.onCreateOptionsMenu(menu);
	}

}