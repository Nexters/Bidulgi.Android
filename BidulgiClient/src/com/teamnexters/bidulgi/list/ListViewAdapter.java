package com.teamnexters.bidulgi.list;

import java.util.ArrayList;

import com.teamnexters.bidulgi.client.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private ArrayList<ListViewItem> data;
	private int layout;

	public ListViewAdapter(Context context, int layout, ArrayList<ListViewItem> data) {
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.data = data;
		this.layout = layout;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position).getName();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = inflater.inflate(layout, parent, false);
		}
		ListViewItem listViewItem = data.get(position);

		ImageView icon = (ImageView) convertView.findViewById(R.id.imgFriends);
		icon.setImageResource(listViewItem.getIcon());

		TextView name = (TextView) convertView.findViewById(R.id.txtFriendsName);
		name.setText(listViewItem.getName());
		
		TextView date = (TextView) convertView.findViewById(R.id.txtDate);
		date.setText(listViewItem.getDate());

		return convertView;
	}

}
