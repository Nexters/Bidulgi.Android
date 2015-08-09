package com.teamnexters.bidulgi.fragments;

import java.util.ArrayList;

import com.teamnexters.bidulgi.client.ClickFriendActivity;
import com.teamnexters.bidulgi.client.DialogLongClickFriend;
import com.teamnexters.bidulgi.client.R;
import com.teamnexters.bidulgi.list.ListViewAdapter;
import com.teamnexters.bidulgi.list.ListViewItem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;

public class BidoolgiFreinds extends Fragment {
	ListView listView;
	private String name;
	Intent intent;
	private boolean isFriendsEmpty = true;

	private ArrayList<ListViewItem> data = new ArrayList<ListViewItem>();
	private ListViewAdapter adapter;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		Log.d("data", "data 값 비었니 " + data.isEmpty());
		
		ListView listView = (ListView) getView().findViewById(R.id.listview);
		listView.setBackgroundResource(R.drawable.backgroundWhite);
		ImageView imgEmpty = (ImageView) getView().findViewById(R.id.imgEmpty);
		listView.setEmptyView(imgEmpty);
		adapter = new ListViewAdapter(getView().getContext(), R.layout.fragment_list, data);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(onItemClickListener);
		listView.setOnItemLongClickListener(onItemLongClickListener);
		Log.d("data", "data 값 비었니 " + data.isEmpty());

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View moodView = inflater.inflate(R.layout.fragment_friends, container, false);

		return moodView;

	}

	public void addData(String name, String date , String regiment, String company, String platoon, String number) {

		Log.d("aaaa", "넘어온 String 값은 " + name);
		if (name.equals("나둘기")) {
			ListViewItem test1 = new ListViewItem(R.drawable.user06, name, "복무중ㅠ.ㅠ","","","","");

			data.add(test1);

			adapter.notifyDataSetChanged();
		} else {
			ListViewItem test1 = new ListViewItem(R.drawable.ic_launcher, name, date , regiment,company, platoon, number);

			data.add(test1);

			adapter.notifyDataSetChanged();
		}

	}

	public void deleteData(int position) {
		data.remove(position);
		adapter.notifyDataSetChanged();

	}

	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			intent = new Intent(getView().getContext(), ClickFriendActivity.class);
			intent.putExtra("icon", data.get(position).getIcon());
			intent.putExtra("name", data.get(position).getName());
			startActivity(intent);
		}

	};

	OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub

			intent = new Intent(getView().getContext(), DialogLongClickFriend.class);
			intent.putExtra("position", position);
			startActivityForResult(intent, 0);

			Log.d("aaaa", "LongClick리스너 실행 position 값은 " + position);
			return true;
		}

	};


}
