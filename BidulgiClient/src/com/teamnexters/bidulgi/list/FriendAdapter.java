package com.teamnexters.bidulgi.list;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.content.Context;
import android.text.format.DateFormat;
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
			Glide.with(convertView.getContext()).load(listViewItem.getProfilePhotoSrc())
					.transform(new CircleTransform(convertView.getContext())).into(icon);
		}

		TextView name = (TextView) convertView.findViewById(R.id.friendListNameTextView);
		name.setText(listViewItem.getName() + " 훈련병");

		TextView enterDate = (TextView) convertView.findViewById(R.id.friendListEnterDateTextView);
		String txtEnterDate = listViewItem.getDate().substring(0, 4) + "." + listViewItem.getDate().substring(4, 6)
				+ "." + listViewItem.getDate().substring(6);
		enterDate.setText("입소일 " + txtEnterDate);
		// enterDate.setText(txtEnterDate);

		TextView DdayDate = (TextView) convertView.findViewById(R.id.friendListDDayTextView);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd", java.util.Locale.getDefault());
		try {
			Date date = dateFormat.parse(txtEnterDate);
			Calendar cal = new GregorianCalendar(Locale.KOREA);
			cal.setTime(date);
			cal.add(Calendar.MONTH, 21);
			cal.add(Calendar.DAY_OF_YEAR, -1);
			
			String dischargedDate = dateFormat.format(cal.getTime());
			String toDayDate = dateFormat.format(new Date());
			
			long longDday = (dateFormat.parse(dischargedDate).getTime() - dateFormat.parse(toDayDate).getTime());
			long Dday = longDday / (24 * 60 * 60 * 1000);
			DdayDate.setText("D-"+Dday);
			Log.d("aaaa", "Dday는 " + Dday);		
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Log.d("aaaa", "날짜 에러" + e.toString());
		}
		//DdayDate.setText("D-40");

		if (position % 2 == 0) {
			convertView.setBackgroundResource(R.drawable.backgroundGray);
		} else if (position % 2 == 1) {
			convertView.setBackgroundResource(R.drawable.backgroundWhite);
		}
		return convertView;
	}

}
