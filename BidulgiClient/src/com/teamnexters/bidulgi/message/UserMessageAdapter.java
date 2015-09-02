package com.teamnexters.bidulgi.message;

import java.util.List;

import com.bumptech.glide.GenericTranscodeRequest;
import com.bumptech.glide.Glide;
import com.teamnexters.bidulgi.client.R;
import com.teamnexters.bidulgi.client.SoldierInfoStore;
import com.teamnexters.bidulgi.common.data.MessageData;
import com.teamnexters.bidulgi.common.data.SoldierData;
import com.teamnexters.bidulgi.glide.CircleTransform;

import android.content.Context;
import android.provider.Telephony.Sms.Conversations;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserMessageAdapter extends BaseAdapter {

	private List<MessageData> messageDataList;
	private LayoutInflater inflater;
	private SoldierInfoStore soldierInfoStore;

	public UserMessageAdapter(List<MessageData> messageDataList, Context context) {
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
			UserMessageViewHolder viewHolder = new UserMessageViewHolder();
			convertView = inflater.inflate(R.layout.list_row_user_message, parent, false);
			viewHolder.soldierImageImageView = (ImageView) convertView
					.findViewById(R.id.userMessageSoldierImageImageView);
			viewHolder.soldierNameTextView = (TextView) convertView.findViewById(R.id.userMessageSoldierNameTextView);
			viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.userMessageTitleTextView);
			viewHolder.timeTextView = (TextView) convertView.findViewById(R.id.userMessageTimeTextView);
			convertView.setTag(viewHolder);
		}
		MessageData currentMessageData = messageDataList.get(position);
		SoldierData currentSoldierData = soldierInfoStore.getData(currentMessageData.getReceiveSoldierId());
		UserMessageViewHolder viewHolder = (UserMessageViewHolder) convertView.getTag();
		viewHolder.soldierNameTextView.setText(currentSoldierData.getName() + " 훈련병");
		viewHolder.titleTextView.setText(currentMessageData.getTitle());
		Log.d("aaaa", "메일리스트 출력 어뎁터 들어옴");
		if (currentSoldierData.getProfilePhotoSrc()== null) {
			Log.d("aaaa", "프로필사진이 없는 사람의 프로필 값은 " + currentSoldierData.getProfilePhotoSrc());
			viewHolder.soldierImageImageView.setImageResource(R.drawable.icon_noprofile);
		} else {
			Glide.with(convertView.getContext()).load(currentSoldierData.getProfilePhotoSrc())
					.transform(new CircleTransform(convertView.getContext())).into(viewHolder.soldierImageImageView);
			// viewHolder.soldierImageImageView;
		}
		
		if(position % 2 == 0){
			convertView.setBackgroundResource(R.drawable.backgroundGray);
		} else if(position % 2 == 1){
			convertView.setBackgroundResource(R.drawable.backgroundWhite);
		}
		
		return convertView;
	}

	class UserMessageViewHolder {
		public ImageView soldierImageImageView;
		public TextView soldierNameTextView, titleTextView, timeTextView;
	}
}
