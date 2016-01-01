package com.teamnexters.bidulgi.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.bumptech.glide.Glide;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

public class ClickAlbumActivity extends Activity {

	Intent intent;
	private ImageView imgTraining;
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_click_album);

		intent = getIntent();
		imgTraining = (ImageView) findViewById(R.id.imgTraining);

		Glide.with(getApplicationContext()).load(intent.getExtras().getString("imgTrainingURL")).into(imgTraining);

		Log.d("aaaa", "외부 저장소 절대 경로는 " + Environment.getExternalStorageDirectory().toString());
		try {
			final File path = new File(Environment.getExternalStorageDirectory().toString() + "/Pictures/bidoolgi/");
			if (!path.isDirectory()) {
				path.mkdir();
			}
			new AsyncTask<Void, Void, Void>() {

				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					try {
						bitmap = Glide.with(getApplicationContext())
								.load(intent.getExtras().getString("imgTrainingURL")).asBitmap().into(100, 100).get();
						// Drawable drawable = imgTraining.getDrawable();
						// bitmap = ((BitmapDrawable)drawable).getBitmap();
					} catch (Exception e) {
						Log.d("error", "글라이드로 비트맵 변환 시 에러 내용은 " + e.toString());
					}
					return null;
				}

				protected void onPostExecute(Void result) {
					try {
						File file = new File(path.getAbsolutePath(), "bidoolgi.jpg");
						OutputStream outputStream = new FileOutputStream(file);

						bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
						outputStream.flush();
						outputStream.close();
					} catch (Exception e) {
						Log.d("error", "비트맵 저장 시 에러내용은 " + e.toString());
					}
				};
			}.execute();

		} catch (Exception e) {
			Log.d("error", "프로필 사진 보기에서 Glide를 통해 bitmap을 추출하는 도중 오류 내용은 " + e.toString());

		}

	}

}
