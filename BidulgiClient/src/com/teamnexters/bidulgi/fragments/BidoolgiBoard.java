package com.teamnexters.bidulgi.fragments;

import java.util.List;

import com.teamnexters.bidulgi.client.BoardEditArticleActivity;
import com.teamnexters.bidulgi.client.ClickArticleActivity;
import com.teamnexters.bidulgi.client.R;
import com.teamnexters.bidulgi.client.board.BoardListAdapter;
import com.teamnexters.bidulgi.common.data.ArticleData;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class BidoolgiBoard extends Fragment implements OnScrollListener {

	private BoardListAdapter boardListAdapter;
	private List<ArticleData> articleDataList;
	private ListView listView;
	private Button btnEditArticle;
	private boolean lockForReload;
	Intent intent;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		listView = (ListView) getView().findViewById(R.id.listviewBoard);
		View header = getActivity().getLayoutInflater().inflate(R.layout.list_board_header, null, false);
		listView.addHeaderView(header);
		listView.setOnItemClickListener(onItemClickListener);
		listView.setBackgroundResource(R.drawable.backgroundWhite);
		listView.setOnScrollListener(this);
		if (articleDataList != null) {
			refreshList(articleDataList, getActivity());
		}

		btnEditArticle = (Button) getView().findViewById(R.id.btnEditArticle);
		btnEditArticle.setOnClickListener(onClickListener);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View moodView = inflater.inflate(R.layout.fragment_board, container, false);
		return moodView;
	}
	
	

	public void refreshList(List<ArticleData> articleDataList, Context context) {
		// userMessageAdapter.notifyDataSetChanged();
		this.articleDataList = articleDataList;
		Log.d("aaaa","넘어온 게시판 목록의 수는 " + articleDataList.size() );
		boardListAdapter = new BoardListAdapter(this.articleDataList, context);
		if (listView != null) {
			listView.setAdapter(boardListAdapter);
		}
		// listView.setOnItemClickListener(onItemClickListener);
	}
	
	public void reloadArticle(List<ArticleData> articleDataList){
		
		for(ArticleData data : articleDataList){
			this.articleDataList.add(data);
		}
		boardListAdapter.notifyDataSetChanged();
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			try {
				Intent intent = new Intent(getView().getContext(), BoardEditArticleActivity.class);
				startActivityForResult(intent, 1);
			} catch (Exception e) {
				Log.d("error", "글쓰기 에러는 " + e.toString());
			}
		}

	};

	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub

			try {
				intent = new Intent(getView().getContext(), ClickArticleActivity.class);

				intent.putExtra("articleId", articleDataList.get(position - 1).getArticleId());
				Log.d("aaaa", "게시판 글 클릭시 넘겨주는 아이디는 " + articleDataList.get(position - 1).getArticleId());
				intent.putExtra("articleTitle", articleDataList.get(position - 1).getTitle());
				intent.putExtra("writeUserName", articleDataList.get(position - 1).getWriteUserName());
				intent.putExtra("writeDate", articleDataList.get(position - 1).getWriteDate());
				intent.putExtra("articleContent", articleDataList.get(position - 1).getContent());
				Log.d("aaaa", "게시판 글 클릭시 넘겨주는 내용은 " + articleDataList.get(position - 1).getContent());
				intent.putExtra("commentCount", articleDataList.get(position - 1).getCommentCount());
				intent.putExtra("watchCount", articleDataList.get(position - 1).getViewCount());

				startActivityForResult(intent, 1);
				Log.d("aaaa", "게시판 클릭");
			} catch (Exception e) {
				Log.d("error", "게시판 클릭 에러는 " + e.toString());
			}

		}

	};
	
	public interface ArticleLoadListener{
		public void onReload(long id);
	}

	private ArticleLoadListener articleLoadListener;
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
		int count = totalItemCount - visibleItemCount;
		
		if(totalItemCount != 0 && totalItemCount == firstVisibleItem + visibleItemCount && lockForReload == false){

			lockForReload = true;
			Runnable reload = new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					articleLoadListener.onReload(articleDataList.get(articleDataList.size() - 1).getArticleId());
					Log.d("aaaa", "페이지의 맨 바닥입니다.");
					lockForReload = false;
				}

			};
			
			Handler handler = new Handler();
			handler.postDelayed(reload, 1000);
			
		}
		
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		articleLoadListener = (ArticleLoadListener) activity;
	}
}