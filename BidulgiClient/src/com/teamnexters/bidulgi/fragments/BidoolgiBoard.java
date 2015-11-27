package com.teamnexters.bidulgi.fragments;

import java.util.List;

import com.teamnexters.bidulgi.client.BoardEditArticleActivity;
import com.teamnexters.bidulgi.client.ClickArticleActivity;
import com.teamnexters.bidulgi.client.R;
import com.teamnexters.bidulgi.client.board.BoardListAdapter;
import com.teamnexters.bidulgi.common.data.ArticleData;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class BidoolgiBoard extends Fragment {

	private BoardListAdapter boardListAdapter;
	private List<ArticleData> articleDataList;
	private ListView listView;
	private Button btnEditArticle;
	Intent intent;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		listView = (ListView) getView().findViewById(R.id.listviewBoard);
		View header = getActivity().getLayoutInflater().inflate(R.layout.list_board_header, null, false);
		listView.addHeaderView(header);
		listView.setOnItemClickListener(onItemClickListener);
		listView.setBackgroundResource(R.drawable.backgroundWhite);
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
		boardListAdapter = new BoardListAdapter(articleDataList, context);
		if (listView != null) {
			listView.setAdapter(boardListAdapter);
		}
		// listView.setOnItemClickListener(onItemClickListener);
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

			try{
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
			}catch(Exception e){
				Log.d("error", "게시판 클릭 에러는 "+ e.toString());
			}

		}

	};

}