package com.teamnexters.bidulgi.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.teamnexters.bidulgi.client.board.BoardListAdapter;
import com.teamnexters.bidulgi.client.board.BoardReplyListAdapter;
import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.data.ArticleData;
import com.teamnexters.bidulgi.common.request.BidulgiRequestCode;
import com.teamnexters.bidulgi.common.request.CommentRequestPacket;
import com.teamnexters.bidulgi.common.request.LongRequestPacket;
import com.teamnexters.bidulgi.common.response.ArticleResponsePacket;
import com.teamnexters.bidulgi.common.response.BidulgiResponseCode;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;
import com.teamnexters.bidulgi.common.response.CommentListResponsePacket;
import com.teamnexters.bidulgi.common.response.MessageListResponsePacket;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ClickArticleActivity extends UIHandlingActivity {

	Intent intent;
	private ScrollView scrollView;
	private BoardReplyListAdapter boardReplyListAdapter;
	private TextView articleBidoolgi;
	private TextView articleTitle;
	private TextView articleWriterName;
	private TextView articleWriteDate;
	private TextView articleWatchCount;
	private TextView articleContent;
	private TextView commentCount;
	private Button btnWriteComent;
	private ScrollView scrollListView;
	private ListView listViewComents;
	private LinearLayout layoutEditReply;
	private TextView txtEditReply;
	private EditText editReply;
	private Button btnSendReply;
	private Long articleId;
	private ActionBar actionBar;
	private static boolean PAGE_END = true;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lockUI();

		Log.d("aaaa", "게시판 글 클릭하고 넘어옴");
		try{
		actionBar = getActionBar();
		actionBar.setTitle(null);
		actionBar.setIcon(null);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setHomeButtonEnabled(false);
		
		setContentView(R.layout.activity_click_article);

		intent = getIntent();

		articleId = intent.getExtras().getLong("articleId");

		Typeface typeface = Typeface.createFromAsset(getAssets(), "NANUMGOTHIC.TTF");
		scrollView = (ScrollView) findViewById(R.id.scrollView);
		articleBidoolgi = (TextView) findViewById(R.id.articleBidoolgi);
		commentCount = (TextView) findViewById(R.id.txtComentCount);
		btnWriteComent = (Button) findViewById(R.id.btnWriteComent);
		btnWriteComent.setOnClickListener(onClickListener);
		scrollListView = (ScrollView) findViewById(R.id.scrollListView);

		layoutEditReply = (LinearLayout) findViewById(R.id.layoutEditReply);

		txtEditReply = (TextView) findViewById(R.id.txtEditReply);
		txtEditReply.setTypeface(typeface);

		editReply = (EditText) findViewById(R.id.editReply);
		editReply.setTypeface(typeface);

		btnSendReply = (Button) findViewById(R.id.btnSendReply);
		btnSendReply.setOnClickListener(onClickListener);
		}catch(Exception e){
			Log.d("error", "게시판 초기화 작업 진행 중 에러 내용은 "+e.toString());
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		try{
		LongRequestPacket request = new LongRequestPacket();
		request.setValue(intent.getExtras().getLong("articleId"));
		request.setRequestCode(BidulgiRequestCode.REQUEST_LIST_COMMENT);
		HttpRequestThread.getInstance().addRequest(request);

		LongRequestPacket requestRead = new LongRequestPacket();
		requestRead.setValue(intent.getExtras().getLong("articleId"));
		requestRead.setRequestCode(BidulgiRequestCode.REQUEST_READ_ARTICLE);
		HttpRequestThread.getInstance().addRequest(requestRead);
		}catch(Exception e){
			Log.d("error", "게시판 글 가져오는 네트워크 작업 중 에러 내용은 " + e.toString());
		}
	}

	@Override
	public void onHandleUI(BidulgiResponsePacket response) {
		// TODO Auto-generated method stub
		switch (response.getResponseCode()) {
		case BidulgiResponseCode.RESPONSE_LIST_COMMENT:
			CommentListResponsePacket commentListResponsePacket = (CommentListResponsePacket) response;
			boardReplyListAdapter = new BoardReplyListAdapter(commentListResponsePacket.getCommentList(),
					getApplicationContext());

			listViewComents = (ListView) findViewById(R.id.listViewComents);
			listViewComents.setAdapter(boardReplyListAdapter);
			unlockUI();
			Log.d("aaaa", "댓글 리스트 가져옴");
			break;
		case BidulgiResponseCode.RESPONSE_WRITE_COMMENT_SUCCESS:
			/*
			 * intent.putExtra("writeComment", true); setResult(6, intent);
			 */
			lockUI();

			LongRequestPacket request = new LongRequestPacket();
			request.setValue(intent.getExtras().getLong("articleId"));
			request.setRequestCode(BidulgiRequestCode.REQUEST_LIST_COMMENT);
			HttpRequestThread.getInstance().addRequest(request);
			layoutEditReply.setVisibility(View.GONE);
			PAGE_END = true;
			Toast.makeText(getApplicationContext(), "댓글을 등록하였습니다.", Toast.LENGTH_SHORT).show();
			// finish();
			break;

		case BidulgiResponseCode.RESPONSE_WRITE_COMMENT_FAIL:
			Toast.makeText(getApplicationContext(), "포인트가 부족하여 댓글을 쓸 수 없습니다.", Toast.LENGTH_SHORT).show();
			break;

		case BidulgiResponseCode.RESPONSE_READ_ARTICLE:

			ArticleResponsePacket responseArticle = (ArticleResponsePacket) response;
			Typeface typeface = Typeface.createFromAsset(getAssets(), "NANUMGOTHIC.TTF");

			try {
				articleTitle = (TextView) findViewById(R.id.articleTitle);
				articleTitle.setText(responseArticle.getArticleData().getTitle());
				Log.d("aaaa", "게시판 클릭 후 넘어온 제목은 " + responseArticle.getArticleData().getTitle());
				articleTitle.setTypeface(typeface);

				articleWriterName = (TextView) findViewById(R.id.articleWriterName);
				articleWriterName.setText(responseArticle.getArticleData().getWriteUserName());
				articleWriterName.setTypeface(typeface);

				articleWriteDate = (TextView) findViewById(R.id.articleWriteDate);
				Date date = new Date(responseArticle.getArticleData().getWriteDate());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd", java.util.Locale.getDefault());
				String strDate = dateFormat.format(date);

				articleWriteDate.setText(strDate);
				Log.d("aaaa", "게시판 작성날짜는 " + strDate);
				articleWriteDate.setTypeface(typeface);

				articleWatchCount = (TextView) findViewById(R.id.articleWatchCount);
				articleWatchCount.setText("조회수 : " + responseArticle.getArticleData().getViewCount());
				Log.d("aaaa", "게시판 글 조회수는 " + responseArticle.getArticleData().getViewCount());
				articleWatchCount.setTypeface(typeface);
				commentCount.setText("["+responseArticle.getArticleData().getCommentCount()+"]");
				articleContent = (TextView) findViewById(R.id.articleContent);
				articleContent.setText(responseArticle.getArticleData().getContent());
				Log.d("aaaa", "게시판 글 내용은 " + responseArticle.getArticleData().getContent());
				articleContent.setTypeface(typeface);

			} catch (Exception e) {
				Log.d("error", "게시판 글 가져오는데 왜 실패하는지..." + e.toString());
			}
			break;
		}

	}
	
	OnTouchListener onTouchListener = new OnTouchListener(){

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			scrollListView.requestDisallowInterceptTouchEvent(true);
			return false;
		}
		
	};

	public void onBackPressed() {
		if (PAGE_END == true) {
			intent.putExtra("backPress", true);
			setResult(6, intent);
			finish();
		}

		else if (PAGE_END == false) {
			layoutEditReply.setVisibility(View.GONE);
			PAGE_END = true;
		}
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnWriteComent:
				listViewComents.setOnTouchListener(onTouchListener);
				listViewComents.setFocusable(true);
				listViewComents.setSelected(true);
				listViewComents.setSelection(listViewComents.getCount()-1);
				layoutEditReply.setVisibility(View.VISIBLE);
				//layoutEditReply.requestFocus();
				
				Handler hd = new Handler();
				hd.postDelayed(new Runnable() {

					@Override
					public void run() {
						scrollView.fullScroll(View.FOCUS_DOWN);
						
					}
				}, 150);
				
				
				
				PAGE_END = false;
				break;

			case R.id.btnSendReply:
				if (editReply.getText().toString() != "") {

					CommentRequestPacket request = new CommentRequestPacket();
					request.setArticleId(articleId);
					request.setContent(editReply.getText().toString());
					request.setRequestCode(BidulgiRequestCode.REQUEST_WRITE_COMMENT);
					HttpRequestThread.getInstance().addRequest(request);
					break;

				} else if (editReply.getText().toString().isEmpty()) {
					Toast.makeText(getApplicationContext(), "댓글 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();

				}
				break;
			}
		}

	};

}
