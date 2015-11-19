package com.teamnexters.bidulgi.fragments;

import com.teamnexters.bidulgi.client.ClientActivity;
import com.teamnexters.bidulgi.client.LoginUserInfo;
import com.teamnexters.bidulgi.client.MainActivity;
import com.teamnexters.bidulgi.client.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BidoolgiSetting extends Fragment {

	Intent intent;
	private SharedPreferences pref;
	private Button niceAuthButton;
	private TextView nameView;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Button btnLogout = (Button) getView().findViewById(R.id.btnLogout);
		nameView = (TextView) getView().findViewById(R.id.settingNameTextView);
		niceAuthButton = (Button) getView().findViewById(R.id.btnNiceAuth);
		niceAuthButton.setOnClickListener(onClickLogout);
		btnLogout.setOnClickListener(onClickLogout);
		if (LoginUserInfo.getInstance().getLoginData().getName() == null) {
			niceAuthButton.setVisibility(View.VISIBLE);
			nameView.setVisibility(View.GONE);
		} else {
			niceAuthButton.setVisibility(View.GONE);
			nameView.setVisibility(View.VISIBLE);
			nameView.setText(LoginUserInfo.getInstance().getLoginData().getName()+"님의 비둘기 포인트는 "+LoginUserInfo.getInstance().getLoginData().getPoint()+"p입니다!");
		}
	}

	public void onAuthSuccess() {
		niceAuthButton.setVisibility(View.GONE);
		nameView.setVisibility(View.VISIBLE);
		nameView.setText(LoginUserInfo.getInstance().getLoginData().getName()+"님의 비둘기 포인트는 "+LoginUserInfo.getInstance().getLoginData().getPoint()+"p입니다!");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View moodView = inflater.inflate(R.layout.fragment_setting, container, false);

		return moodView;
	}

	OnClickListener onClickLogout = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnLogout:
				try {
					LoginUserInfo.getInstance().setLoginData(null);
					pref = getView().getContext().getSharedPreferences("email", Activity.MODE_PRIVATE);
					SharedPreferences.Editor editor = pref.edit();
					editor.clear();
					editor.commit();
					intent = new Intent(getView().getContext(), MainActivity.class);
					startActivity(intent);
					getActivity().finish();
					Toast.makeText(getView().getContext(), "둥지를 잠시 떠났습니다.", Toast.LENGTH_SHORT).show();
					break;
				} catch (Exception e) {
					Log.d("aaaa", "error : " + e.toString());
				}

			case R.id.btnNiceAuth:
				((ClientActivity) getActivity()).onNiceAuthStart();
			}
		}

	};
}
