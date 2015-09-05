package com.teamnexters.bidulgi.fragments;

import java.util.List;

import com.teamnexters.bidulgi.client.R;
import com.teamnexters.bidulgi.client.board.BoardListAdapter;
import com.teamnexters.bidulgi.common.data.ArticleData;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class BidoolgiBoard extends Fragment {

	private BoardListAdapter boardListAdapter;
	private List<ArticleData> articleDataList;
	private ListView listView;
	Intent intent;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		listView = (ListView) getView().findViewById(R.id.listviewBoard);
		View header = getActivity().getLayoutInflater().inflate(R.layout.list_board_header, null, false);
		listView.addHeaderView(header);
		listView.setBackgroundResource(R.drawable.backgroundWhite);
		if (articleDataList != null) {
			refreshList(articleDataList, getActivity());
		}
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

	/*
	 * OnItemClickListener onItemClickListener = new OnItemClickListener() {
	 * 
	 * @Override public void onItemClick(AdapterView<?> parent, View view, int
	 * position, long id) { Log.d("aaaa", "메일 리스트 클릭"); SoldierInfoStore
	 * soldierInfoStore = SoldierInfoStore.getInstance(); intent = new
	 * Intent(getView().getContext(), ClickMailActivity.class);
	 * intent.putExtra("profilePhotoSrc",
	 * soldierInfoStore.getData(soldierDataList.get(position).
	 * getReceiveSoldierId()).getProfilePhotoSrc()); intent.putExtra("name",
	 * soldierInfoStore.getData(soldierDataList.get(position).
	 * getReceiveSoldierId()).getName()); intent.putExtra("id",
	 * soldierDataList.get(position).getReceiveSoldierId()); Log.d("aaaa",
	 * "메일 리스트 클릭시 넘어가는 훈련병 이름 " +
	 * soldierInfoStore.getData(soldierDataList.get(position).
	 * getReceiveSoldierId()).getName());
	 * 
	 * startActivityForResult(intent, 3); /* intent.putExtra("name",
	 * data.get(position).getName()); intent.putExtra("regiment",
	 * data.get(position).getRegiment()); intent.putExtra("address",
	 * data.get(position).getRegiment() + "연대 " +
	 * data.get(position).getCompany() + "소대 " + data.get(position).getPlatoon()
	 * + "분대 " + data.get(position).getNumber() + "번 훈련병");
	 * intent.putExtra("enterDate", data.get(position).getDate());
	 * intent.putExtra("id", data.get(position).getsoldierId());
	 * startActivity(intent); getActivity().finish();
	 * 
	 * }
	 * 
	 * };
	 */
}