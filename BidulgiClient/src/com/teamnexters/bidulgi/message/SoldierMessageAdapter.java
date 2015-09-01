package com.teamnexters.bidulgi.message;

import java.sql.Date;
import java.util.List;

import com.bumptech.glide.Glide;
import com.teamnexters.bidulgi.client.R;
import com.teamnexters.bidulgi.client.SoldierInfoStore;
import com.teamnexters.bidulgi.common.data.MessageData;
import com.teamnexters.bidulgi.common.data.SoldierData;
import com.teamnexters.bidulgi.glide.CircleTransform;
import com.teamnexters.bidulgi.message.UserMessageAdapter.UserMessageViewHolder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SoldierMessageAdapter extends BaseAdapter {

	private List<MessageData> messageDataList;
	private LayoutInflater inflater;
	private SoldierInfoStore soldierInfoStore;

	public SoldierMessageAdapter(List<MessageData> messageDataList, Context context) {
		this.messageDataList = messageDataList;
		this.inflater = LayoutInflater.from(context);
		soldierInfoStore = SoldierInfoStore.getInstance();
	}

	@Override
	public int getCount() {
		return messageDataList.size();
	}

	@Override
	public Object getItem(int position) {
		return messageDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return messageDataList.get(position).getMessageId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			SoldierMessageViewHolder viewHolder = new SoldierMessageViewHolder();
			convertView = inflater.inflate(R.layout.list_row_soldier_message, parent, false);
			viewHolder.txtMailSendDate = (TextView) convertView.findViewById(R.id.txtMailSendDate);
			viewHolder.txtMailContent = (TextView) convertView.findViewById(R.id.txtMailContent);
			convertView.setTag(viewHolder);
		}

		MessageData currentMessageData = messageDataList.get(position);
		SoldierMessageViewHolder viewHolder = (SoldierMessageViewHolder) convertView.getTag();
		
		viewHolder.txtMailContent.setText(currentMessageData.getContent());
		Date date = new Date(currentMessageData.getSendTime());
		
		viewHolder.txtMailSendDate.setText("" + date);
		Log.d("aaaa", "편지보낸 시간은 " + currentMessageData.getSendTime());
		Log.d("aaaa", "Date에 들어간 시간" + date);
		Log.d("aaaa", "편지내용은 " + currentMessageData.getContent());

		if (position % 2 == 0) {
			convertView.setBackgroundResource(R.drawable.backgroundGray);
		} else if (position % 2 == 1) {
			convertView.setBackgroundResource(R.drawable.backgroundWhite);
		}
		return convertView;
	}

	class SoldierMessageViewHolder {
		public TextView txtMailSendDate, txtMailContent;
	}

}
