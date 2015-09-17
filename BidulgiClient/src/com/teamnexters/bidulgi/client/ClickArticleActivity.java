package com.teamnexters.bidulgi.client;

import java.util.List;

import com.teamnexters.bidulgi.client.board.BoardListAdapter;
import com.teamnexters.bidulgi.client.board.BoardReplyListAdapter;
import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.data.ArticleData;
import com.teamnexters.bidulgi.common.request.BidulgiRequestCode;
import com.teamnexters.bidulgi.common.request.CommentRequestPacket;
import com.teamnexters.bidulgi.common.request.LongRequestPacket;
import com.teamnexters.bidulgi.common.response.BidulgiResponseCode;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;
import com.teamnexters.bidulgi.common.response.CommentListResponsePacket;
import com.teamnexters.bidulgi.common.response.MessageListResponsePacket;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ClickArticleActivity extends UIHandlingActivity {

	Intent intent;
	private BoardReplyListAdapter boardReplyListAdapter;
	private TextView articleBidoolgi; 
	private TextView articleTitle; 
	private TextView articleWriterName; 
	private TextView articleWriteDate; 
	private TextView articleWatchCount; 
	private TextView articleContent; 
	private Button btnWriteComent; 
	private ListView listViewComents;  
	private LinearLayout layoutEditReply; 
	private TextView txtEditReply; 
	private EditText editReply;
	private Button btnSendReply; 
	private Long articleId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_click_article);

		intent = getIntent();
		
		
		LongRequestPacket request = new LongRequestPacket();
		request.setValue(intent.getExtras().getLong("articleId"));
		request.setRequestCode(BidulgiRequestCode.REQUEST_LIST_COMMENT);
		HttpRequestThread.getInstance().addRequest(request);
		articleId = intent.getExtras().getLong("articleId");
		
		Typeface typeface = Typeface.createFromAsset(getAssets(), "NANUMGOTHIC.TTF");
		articleBidoolgi = (TextView) findViewById(R.id.articleBidoolgi);
		
		articleTitle = (TextView) findViewById(R.id.articleTitle);
		articleTitle.setText(intent.getExtras().getString("articleTitle"));
		articleTitle.setTypeface(typeface);
		
		articleWriterName = (TextView) findViewById(R.id.articleWriterName);
		articleWriterName.setText(intent.getExtras().getString("writeUserName"));
		articleWriterName.setTypeface(typeface);
		
		articleWriteDate = (TextView) findViewById(R.id.articleWriteDate);
		articleWriteDate.setText(intent.getExtras().getString("writeDate"));
		articleWriteDate.setTypeface(typeface);
		
		articleWatchCount = (TextView) findViewById(R.id.articleWatchCount);
		articleWatchCount.setText(intent.getExtras().getString("watchCount"));
		articleWatchCount.setTypeface(typeface);
		
		articleContent = (TextView) findViewById(R.id.articleContent);
		articleContent.setText(intent.getExtras().getString("articleContent"));
		articleContent.setTypeface(typeface);
		
		btnWriteComent = (Button) findViewById(R.id.btnWriteComent);
		btnWriteComent.setOnClickListener(onClickListener);
		
		
		layoutEditReply = (LinearLayout) findViewById(R.id.layoutEditReply);
		
		txtEditReply = (TextView) findViewById(R.id.txtEditReply);
		txtEditReply.setTypeface(typeface);
		
		editReply = (EditText) findViewById(R.id.editReply);
		editReply.setTypeface(typeface);
		
		btnSendReply = (Button) findViewById(R.id.btnSendReply);
		btnSendReply.setOnClickListener(onClickListener);
		
		
	}
	
	


	@Override
	public void onHandleUI(BidulgiResponsePacket response) {
		// TODO Auto-generated method stub
		switch(response.getResponseCode()){
		case BidulgiResponseCode.RESPONSE_LIST_COMMENT:
			CommentListResponsePacket commentListResponsePacket = (CommentListResponsePacket) response;
			boardReplyListAdapter = new BoardReplyListAdapter(commentListResponsePacket.getCommentList(),getApplicationContext());
			
			listViewComents = (ListView) findViewById(R.id.listViewComents);
			listViewComents.setAdapter(boardReplyListAdapter);
			break;
		case BidulgiResponseCode.RESPONSE_WRITE_COMMENT_SUCCESS:
			intent.putExtra("writeComment", true);
			setResult(6, intent);
			Toast.makeText(getApplicationContext(), "댓글을 등록하였습니다.", Toast.LENGTH_SHORT).show();
			finish();
			break;
			
		case BidulgiResponseCode.RESPONSE_WRITE_COMMENT_FAIL:
			Toast.makeText(getApplicationContext(), "댓글에 실패하였습니다.", Toast.LENGTH_SHORT).show();
			break;
			
		}

	}
	
	public void onBackPressed() {
		intent.putExtra("backPress", true);
		setResult(6, intent);
		finish();
	};

	
	OnClickListener onClickListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
				case R.id.btnWriteComent:
					layoutEditReply.setVisibility(View.VISIBLE);
					
					break;
					
				case R.id.btnSendReply:
					if(editReply.getText().toString() != ""){
						
					CommentRequestPacket request = new CommentRequestPacket();
					request.setArticleId(articleId);
					request.setContent(editReply.getText().toString());
					request.setRequestCode(BidulgiRequestCode.REQUEST_WRITE_COMMENT);
					HttpRequestThread.getInstance().addRequest(request);
					break;
					
					
					} else if(editReply.getText().toString().isEmpty()){
						Toast.makeText(getApplicationContext(), "댓글 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
						
					}
					break;
			}
		}
		
	};
	
}
