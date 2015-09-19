package com.teamnexters.bidulgi.client.board;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.teamnexters.bidulgi.client.R;
import com.teamnexters.bidulgi.client.board.BoardListAdapter.BidoolgiBoardViewHolder;
import com.teamnexters.bidulgi.common.data.ArticleData;
import com.teamnexters.bidulgi.common.data.CommentData;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BoardReplyListAdapter extends BaseAdapter{

	private List<CommentData> commentDataList;
	private LayoutInflater inflater;
	
	
	public BoardReplyListAdapter(List<CommentData> commentDataList , Context context) {
		this.commentDataList = commentDataList;
		this.inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return commentDataList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return commentDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return commentDataList.get(position).getArticleId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			BidoolgiReplyViewHolder viewHolder = new BidoolgiReplyViewHolder();
			convertView = inflater.inflate(R.layout.list_article_reply, parent, false);
			viewHolder.txtReplyContent = (TextView) convertView.findViewById(R.id.txtReplyContent);
			viewHolder.txtReplyWriterName = (TextView) convertView.findViewById(R.id.txtReplyWriterName);
			viewHolder.txtReplyDate = (TextView) convertView.findViewById(R.id.txtReplyDate);
			convertView.setTag(viewHolder);
		}

		CommentData currentCommentData = commentDataList.get(position);
		BidoolgiReplyViewHolder viewHolder = (BidoolgiReplyViewHolder) convertView.getTag();
		
		viewHolder.txtReplyContent.setText(currentCommentData.getContent());
		viewHolder.txtReplyWriterName.setText(currentCommentData.getWriteUserName());
		Log.d("aaaa", "댓글 단 사람 이름은 " + currentCommentData.getWriteUserName());
		
		/*Date date = new Date(currentCommentData.get);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd일", java.util.Locale.getDefault());
		String strDate = dateFormat.format(date);
		
		viewHolder.boardWriteDate.setText(strDate);*/
		

		return convertView;
	}
	class BidoolgiReplyViewHolder {
		public TextView txtReplyContent, txtReplyWriterName, txtReplyDate;
	}
	
}
