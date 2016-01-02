package com.teamnexters.bidulgi.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.bumptech.glide.Glide;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class ClickAlbumActivity extends Activity {

	Intent intent;
	private ImageView imgTraining;
	private Bitmap bitmap;
	private ActionBar actionBar;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_click_album);

		actionBar = getActionBar();
		actionBar.setTitle(null);
		actionBar.setIcon(null);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setHomeButtonEnabled(false);

		intent = getIntent();
		imgTraining = (ImageView) findViewById(R.id.imgTraining);

		Glide.with(getApplicationContext()).load(intent.getExtras().getString("imgTrainingURL")).into(imgTraining);

		Log.d("aaaa", "외부 저장소 절대 경로는 " + Environment.getExternalStorageDirectory().toString());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.click_albumpicture, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		
		case R.id.itemExit:
			finish();
			break;
			

		case R.id.itemDownload:

			try {
				final File path = new File(
						Environment.getExternalStorageDirectory().toString() + "/Pictures/bidoolgi/");
				if (!path.isDirectory()) {
					path.mkdir();
				}
				new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						// TODO Auto-generated method stub
						try {
							bitmap = Glide.with(getApplicationContext())
									.load(intent.getExtras().getString("imgTrainingURL")).asBitmap().into(100, 100)
									.get();
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
							Toast.makeText(getApplicationContext(), "사진 저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
						} catch (Exception e) {
							Log.d("error", "비트맵 저장 시 에러내용은 " + e.toString());
						}
					};
				}.execute();

			} catch (Exception e) {
				Log.d("error", "프로필 사진 보기에서 Glide를 통해 bitmap을 추출하는 도중 오류 내용은 " + e.toString());

			}

			break;

		
		}
		return super.onOptionsItemSelected(item);
	}

}
