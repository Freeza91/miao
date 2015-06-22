package com.meimiao;

import helpers.SaveImage;
import helpers.StatusCode;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class PostCameraPictures extends Activity implements StatusCode {

	private ImageView image;
	private Button post_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_camera);

		initView();
		addListener();
		openCamera();
	}

	private void initView() {
		// TODO Auto-generated method stub
		image = (ImageView) findViewById(R.id.camera_image);
		post_btn = (Button) findViewById(R.id.post_camera_btn);
	}

	private void addListener() {
		// TODO Auto-generated method stub
		post_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				PostCameraPictures.this.setResult(POST_CAMERA_SUCCESS);
			}
		});
	}

	private void openCamera() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, CAMERA_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		switch (resultCode) {
		case RESULT_OK:
			Bitmap bitmap = (Bitmap) data.getExtras().get("data");
			image.setImageBitmap(bitmap);
			SaveImage saveImage = new SaveImage();
			if (saveImage.save(bitmap)) {
				Toast.makeText(getApplicationContext(), "保存图片成功",
						Toast.LENGTH_SHORT)
						.show();
			} else {
				Toast.makeText(getApplicationContext(), "保存图片失败",
						Toast.LENGTH_SHORT)
						.show();
			}
			break;
		default:
			Toast.makeText(getApplicationContext(), "没有权限访问照相机",
					Toast.LENGTH_SHORT).show();
			break;
		}
	}

}
