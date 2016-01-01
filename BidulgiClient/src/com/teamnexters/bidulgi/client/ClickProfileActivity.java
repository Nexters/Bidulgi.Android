package com.teamnexters.bidulgi.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class ClickProfileActivity extends Activity {

	private Intent intent;
	private ImageView imgProfile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_click_profile);

		intent = getIntent();
		imgProfile = (ImageView) findViewById(R.id.imgProfile);
		Log.d("aaaa", "프로필 사진을 클릭했을때 넘어오는 URL은 " + intent.getExtras().getString("imgProfileURL"));
		if (intent.getExtras().getString("imgProfileURL") == null) {
			imgProfile.setImageResource(R.drawable.icon_noprofile);
		} else {
			Glide.with(getApplicationContext()).load(intent.getExtras().getString("imgProfileURL")).into(imgProfile);
		}
		/*try{
			File path = new File("/sdcard/image/bidoolgi/");
			if(!path.isDirectory()){
			path.mkdir();
			}
			File file = new File(path.getAbsolutePath(), "bidoolgi.PNG");
			OutputStream outputStream = new FileOutputStream(file);
		Bitmap bitmap = Glide.with(getApplicationContext()).load(intent.getExtras().getString("imgProfileURL")).asBitmap().into(100,100).get();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
		outputStream.flush();
		outputStream.close();
		
		}catch(Exception e){
			Log.d("error", "프로필 사진 보기에서 Glide를 통해 bitmap을 추출하는 도중 오류 내용은 " + e.toString());
		}*/
	}

}
