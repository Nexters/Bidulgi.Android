package com.teamnexters.bidulgi.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamnexters.bidulgi.client.R;

public class BidoolgiMailList extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View moodView = inflater.inflate(R.layout.fragment_mail_list, container, false);

		return moodView;
	}
}
