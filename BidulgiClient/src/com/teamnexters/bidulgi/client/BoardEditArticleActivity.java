package com.teamnexters.bidulgi.client;

import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.client.ui.UIHandlingActivity;
import com.teamnexters.bidulgi.common.request.BidulgiRequestCode;
import com.teamnexters.bidulgi.common.request.BoardRequestPacket;

import com.teamnexters.bidulgi.common.response.BidulgiResponseCode;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BoardEditArticleActivity extends UIHandlingActivity {

	private TextView txtArticleTitle;
	private EditText editArticleTitle;
	private TextView txtArticleContent;
	private EditText editArticleContent;

	private Button btnSendArticle;
	private ActionBar actionBar;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_board_edit_article);

		actionBar = getActionBar();
		actionBar.setTitle(null);
		actionBar.setIcon(null);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setHomeButtonEnabled(false);

		Typeface typeface = Typeface.createFromAsset(getAssets(), "NANUMGOTHIC.TTF");

		txtArticleTitle = (TextView) findViewById(R.id.txtArticleTitle);
		editArticleTitle = (EditText) findViewById(R.id.editArticleTitle);
		txtArticleContent = (TextView) findViewById(R.id.txtArticleContent);
		editArticleContent = (EditText) findViewById(R.id.editArticleContent);
		btnSendArticle = (Button) findViewById(R.id.btnSendArticle);

		txtArticleTitle.setTypeface(typeface);
		txtArticleContent.setTypeface(typeface);
		editArticleTitle.setTypeface(typeface);
		editArticleContent.setTypeface(typeface);

		btnSendArticle.setOnClickListener(onClickListener);

	}

	@Override
	public void onHandleUI(BidulgiResponsePacket response) {
		// TODO Auto-generated method stub
		Intent resIntent = new Intent();
		Log.d("aaaa", "게시판 글쓰기 리스폰스 코드는 " + response.getResponseCode());
		switch (response.getResponseCode()) {

		case BidulgiResponseCode.RESPONSE_WRITE_ARTICLE_SUCCESS:
			resIntent.putExtra("writeAricle", true);
			break;

		case BidulgiResponseCode.RESPONSE_WRITE_ARTICLE_FAIL:
			resIntent.putExtra("writeAricle", false);
			break;
		}
		setResult(4, resIntent);
		finish();

	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			try{
				Log.d("aaaa", "게시판 글쓰기 버튼 눌렀어~!! ");
			BoardRequestPacket requestEditArticle = new BoardRequestPacket();
			requestEditArticle.setTitle(editArticleTitle.getText().toString());
			requestEditArticle.setContent(editArticleContent.getText().toString());
			requestEditArticle.setRequestCode(BidulgiRequestCode.REQUEST_WRITE_ARTICLE);
			HttpRequestThread.getInstance().addRequest(requestEditArticle);
			}catch(Exception e){
				Log.d("error", "게시판 글쓰기 에러내용은 " + e.toString());
			}
		}

	};
}
