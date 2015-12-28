package com.teamnexters.bidulgi.list;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.teamnexters.bidulgi.client.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class AlbumAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private ArrayList<String> albumURL;
	private Context context;

	public AlbumAdapter(Context context, ArrayList<String> albumURL) {

		this.context = context;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.albumURL = albumURL;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return albumURL.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return albumURL.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
			imageView.setAdjustViewBounds(false);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(2, 2, 2, 2);
		} else {
			imageView = (ImageView) convertView;
		}
		/*
		 * if(convertView == null){ convertView =
		 * inflater.inflate(R.layout.gridview_album, parent ,false); }
		 */

		//ImageView imgAlbum = (ImageView) convertView.findViewById(R.id.imgAlbum);
		
		Log.d("aaaa", "AlbumAdapter 훈련사진 URL은 " + albumURL.get(position));
		//Glide.with(convertView.getContext()).load(albumURL.get(position)).into(imgAlbum);
		Glide.with(context).load(albumURL.get(position)).into(imageView);
		return imageView;
	}

}
