package com.teamnexters.bidulgi.message;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamnexters.bidulgi.client.R;
import com.teamnexters.bidulgi.common.data.SoldierData;

public class UserMessageAdapter extends BaseAdapter{

	private List<SoldierData> soldierDataList;
	private LayoutInflater inflater;
	
	public UserMessageAdapter(List<SoldierData> soldierDataList, Context context) {
		this.soldierDataList = soldierDataList;
		this.inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return soldierDataList.size();
	}

	@Override
	public Object getItem(int position) {
		return soldierDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return soldierDataList.get(position).getSoldierId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			UserMessageViewHolder viewHolder = new UserMessageViewHolder();
			convertView = inflater.inflate(R.layout.list_row_user_message, parent);
			viewHolder.soldierImageImageView = (ImageView) convertView.findViewById(R.id.userMessageSoldierImageImageView);
			viewHolder.soldierNameTextView = (TextView) convertView.findViewById(R.id.userMessageSoldierNameTextView);
			viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.userMessageTitleTextView);
			viewHolder.timeTextView = (TextView) convertView.findViewById(R.id.userMessageTimeTextView);
			convertView.setTag(viewHolder);
		}
		
		UserMessageViewHolder viewHolder = (UserMessageViewHolder) convertView.getTag();
//		viewHolder.soldierImageImageView;
		return convertView;
	}
	class UserMessageViewHolder{
		public ImageView soldierImageImageView;
		public TextView soldierNameTextView, titleTextView, timeTextView;
	}
}
