package com.teamnexters.bidulgi.list;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teamnexters.bidulgi.client.R;
import com.teamnexters.bidulgi.glide.CircleTransform;

public class FriendAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private ArrayList<ListViewItem> data;

	public FriendAdapter(Context context, ArrayList<ListViewItem> data) {
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public String getItem(int position) {
		return data.get(position).getName();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.fragment_list, parent, false);
		}
		
		ListViewItem listViewItem = data.get(position);
		ImageView icon = (ImageView) convertView.findViewById(R.id.friendListProfileImageView);
		Log.d("aaaa", "넘어온 프로필 URL은 " + listViewItem.getProfilePhotoSrc());
		if (listViewItem.getProfilePhotoSrc() == null) {
			icon.setImageResource(R.drawable.icon_noprofile);
		} else {
			Glide.with(convertView.getContext()).load(listViewItem.getProfilePhotoSrc()).transform(new CircleTransform(convertView.getContext())).into(icon);
		}
		// icon.setImageResource(listViewItem.getIcon());

		TextView name = (TextView) convertView.findViewById(R.id.friendListNameTextView);
		name.setText(listViewItem.getName()+" 훈련병");

		TextView date = (TextView) convertView.findViewById(R.id.friendListDDayTextView);
		date.setText("D-40");
//
		
		if (position % 2 == 0) {
			convertView.setBackgroundResource(R.drawable.backgroundGray);
		} else if (position % 2 == 1) {
			convertView.setBackgroundResource(R.drawable.backgroundWhite);
		}
		return convertView;
	}
	
	
}
