package com.meimiao;

import helpers.StatusCode;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostShortMessage extends Activity implements StatusCode {

	private EditText et;
	private Button btn;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_short_message);

		initView();
		addListener();
	}

	private void initView() {
		// TODO Auto-generated method stub
		et = (EditText) findViewById(R.id.post_message);
		btn = (Button) findViewById(R.id.post_btn);
	}

	private void addListener() {
		// TODO Auto-generated method stub
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent = new Intent();
				PostShortMessage.this.setResult(POST_MESSAGE_SUCCESS);
				PostShortMessage.this.finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		intent = new Intent();
		this.setResult(QUIT_POST_MESSAGE);
		Toast.makeText(getApplicationContext(), "确定要放弃修改吗？", Toast.LENGTH_LONG)
				.show();
		this.finish();
	}

}
