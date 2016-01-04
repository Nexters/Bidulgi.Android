package com.teamnexters.bidulgi.fragments;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.teamnexters.bidulgi.client.ClickFriendActivity;
import com.teamnexters.bidulgi.client.DialogLongClickFriend;
import com.teamnexters.bidulgi.client.R;
import com.teamnexters.bidulgi.client.network.HttpRequestThread;
import com.teamnexters.bidulgi.common.data.MessageData;
import com.teamnexters.bidulgi.common.data.SoldierData;
import com.teamnexters.bidulgi.common.request.BidulgiRequestCode;
import com.teamnexters.bidulgi.common.request.LongRequestPacket;
import com.teamnexters.bidulgi.list.FriendAdapter;
import com.teamnexters.bidulgi.list.ListViewItem;

public class BidoolgiFreinds extends Fragment {
	ListView listView;
	private String name;
	Intent intent;
	private boolean isFriendsEmpty = true;

	private ArrayList<SoldierData> data = new ArrayList<SoldierData>();
	private FriendAdapter adapter;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Log.d("data", "data 값 비었니 " + data.isEmpty());
		try {
			ListView listView = (ListView) getView().findViewById(R.id.listview);
			listView.setBackgroundResource(R.drawable.backgroundWhite);
			ImageView imgEmpty = (ImageView) getView().findViewById(R.id.imgEmpty);
			listView.setEmptyView(imgEmpty);
			adapter = new FriendAdapter(getView().getContext(), data);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(onItemClickListener);
			listView.setOnItemLongClickListener(onItemLongClickListener);
			Log.d("data", "data 값 비었니 " + data.isEmpty());
		} catch (Exception e) {
			Log.d("data", "data 에러는" + e.toString());
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View moodView = inflater.inflate(R.layout.fragment_friends, container, false);

		return moodView;

	}

	public void addData(SoldierData soldierData) {

		Log.d("aaaa", "넘어온 String 값은 " + name);
		// if (name.equals("나둘기")) {
		// ListViewItem test1 = new ListViewItem(profilePhotoSrc, name,
		// "복무중ㅠ.ㅠ", "", "", "", "", null, birth);
		//
		// data.add(test1);
		//
		// adapter.notifyDataSetChanged();
		// } else {

		data.add(soldierData);

		adapter.notifyDataSetChanged();

		// }

	}

	public void deleteData(int position) {
		LongRequestPacket request = new LongRequestPacket();
		request.setValue(data.get(position).getSoldierId());
		request.setRequestCode(BidulgiRequestCode.REQUEST_REMOVE_FRIEND_SOLDIER);
		HttpRequestThread.getInstance().addRequest(request);
		data.remove(position);
		adapter.notifyDataSetChanged();

	}
	

	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			String enterDate = data.get(position).getEnterDateString();
			intent = new Intent(getView().getContext(), ClickFriendActivity.class);
			intent.putExtra("birth", data.get(position).getEnterDateString());
			intent.putExtra("profilePhotoSrc", data.get(position).getProfilePhotoSrc());
			intent.putExtra("name", data.get(position).getName());
			intent.putExtra("regiment", data.get(position).getRegiment());
			intent.putExtra("address", data.get(position).getRegiment() + "연대 " + data.get(position).getCompany()
					+ "중대 " + data.get(position).getPlatoon() + "소대 " + data.get(position).getNumber() + "번");
			intent.putExtra("enterDate",
					enterDate.substring(0, 4) + "." + enterDate.substring(4, 6) + "." + enterDate.substring(6));
			intent.putExtra("id", data.get(position).getSoldierId());
			intent.putExtra("birth", data.get(position).getBirthString());
			
			//훈련사진 값이 비었는지 확인
			if (data.get(position).getPhotoSrcSet().isEmpty()) {

				Log.d("aaaa", "분대사진 값이 비었음");
			} else {
				
				//훈련사진 값이 비어있지 않으면 ArrayList<String>으로 형변환 후 차례대로 인텐트에 추가
				ArrayList<String> arrayListAlbumURL = new ArrayList<String>(data.get(position).getPhotoSrcSet());

				for (int i = 0; i < arrayListAlbumURL.size(); i++) {
					Log.d("aaaa", "분대사진 URL은 " + arrayListAlbumURL.get(i));
					intent.putExtra("albumURL" + i, arrayListAlbumURL.get(i));
					intent.putExtra("albumURLSize", i);
				}
			}
			// intent.putExtra("albumURL",
			// data.get(position).getNewPhotoSrcSet());
			startActivity(intent);
			getActivity().finish();
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
