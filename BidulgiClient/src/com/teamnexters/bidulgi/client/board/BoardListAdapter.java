package com.teamnexters.bidulgi.client.board;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.teamnexters.bidulgi.client.R;
import com.teamnexters.bidulgi.common.data.ArticleData;
import com.teamnexters.bidulgi.common.data.MessageData;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BoardListAdapter extends BaseAdapter{
	
	private List<ArticleData> articleDataList;
	private LayoutInflater inflater;
	
	public BoardListAdapter(List<ArticleData> articleDataList , Context context) {
		// TODO Auto-generated constructor stub
		this.articleDataList = articleDataList;
		this.inflater = LayoutInflater.from(context);		
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return articleDataList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return articleDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return articleDataList.get(position).getArticleId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			BidoolgiBoardViewHolder viewHolder = new BidoolgiBoardViewHolder();
			convertView = inflater.inflate(R.layout.list_bidoolgi_board, parent, false);
			viewHolder.boardTitle = (TextView) convertView.findViewById(R.id.boardTitle);
			viewHolder.boardWriterName = (TextView) convertView.findViewById(R.id.boardWriterName);
			viewHolder.boardWriteDate = (TextView) convertView.findViewById(R.id.boardWriteDate);
			viewHolder.boardWatchCount = (TextView) convertView.findViewById(R.id.boardWatchCount);
			convertView.setTag(viewHolder);
		}

		ArticleData currentArticleData = articleDataList.get(position);
		BidoolgiBoardViewHolder viewHolder = (BidoolgiBoardViewHolder) convertView.getTag();
		
		viewHolder.boardTitle.setText(currentArticleData.getTitle());
		viewHolder.boardWriterName.setText(currentArticleData.getWriteUserName());
		
		Date date = new Date(currentArticleData.getWriteDate());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일", java.util.Locale.getDefault());
		String strDate = dateFormat.format(date);
		
		viewHolder.boardWriteDate.setText(strDate);
		Log.d("aaaa", "편지보낸 시간은 "+ strDate);
		
		viewHolder.boardWatchCount.setText("조화수 : 30");

		return convertView;
	}

	class BidoolgiBoardViewHolder {
		public TextView boardTitle, boardWriterName, boardWriteDate, boardWatchCount;
	}
}
