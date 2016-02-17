package com.teamnexters.bidulgi.client;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class BidoolgiActionBar {

	// ActionBar 를 활성화시킬 activity
	private Activity activity;

	// activity로부터 가져온 ActionBar
	private ActionBar actionBar;

	// tabFriendList Tab
	private ActionBar.Tab tabFriendList;

	// tabMailList Tab
	private ActionBar.Tab tabMailList;

	// tabBoardList Tab
	private ActionBar.Tab tabBoardList;

	// Test 4 Tab
	private ActionBar.Tab tabSetting;

	/**
	 *
	 * @param act
	 *            ActionBar를 활성화 시킬 Activity activity.getActionBar()로 actionBar
	 *            를 사용하기위해서 받아옵니다.
	 */
	@SuppressLint("NewApi")
	public BidoolgiActionBar(Activity act) {
			activity = act;
			actionBar = activity.getActionBar();
			actionBar.setTitle(null);
			actionBar.setIcon(null);
			actionBar.setDisplayHomeAsUpEnabled(false);
			actionBar.setDisplayUseLogoEnabled(false);
			actionBar.setHomeButtonEnabled(false);
			actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			initTabs();
		

	}

	public void initTabs() {
		tabFriendList = actionBar.newTab().setIcon(R.drawable.re_friend);
		tabMailList = actionBar.newTab().setIcon(R.drawable.re_letter);
		tabBoardList = actionBar.newTab().setIcon(R.drawable.re_board);
		tabSetting = actionBar.newTab().setIcon(R.drawable.re_setting);
	}

	public void setTabListener(ActionBar.TabListener tabListener) {

			tabFriendList.setTabListener(tabListener);
			tabMailList.setTabListener(tabListener);
			tabBoardList.setTabListener(tabListener);
			tabSetting.setTabListener(tabListener);

			actionBar.addTab(tabFriendList);
			actionBar.addTab(tabMailList);
			actionBar.addTab(tabBoardList);
			actionBar.addTab(tabSetting);
	}

	/**
	 * Fragment에서 스와이프로 탭을 옮겨가면 탭 선택자도 바꾸기위해서 ViewPager를 받아옵니다.
	 *
	 * @param pager
	 *            Fragment가 사용하는 ViewPager
	 */
	public void setViewPager(ViewPager pager) {
		pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onPageSelected(int position) {
				try {
					actionBar.setSelectedNavigationItem(position);
				} catch (Exception e) {
					Log.d("aaa", e.toString());
				}
			}
		});
	}
}