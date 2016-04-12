package com.example.search;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText search_edit;
	private LinearLayout search_more;
	private LinearLayout mail_main;
	private View touch_out;
	private LinearLayout search_ll;
	private TextView send_btn;
	private TextView load_btn;
	private TextView theme_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draffbox);
		search_edit = (EditText) findViewById(R.id.act_mail_search_edit);
		search_more = (LinearLayout) findViewById(R.id.act_mail_search_more);
		mail_main = (LinearLayout) findViewById(R.id.act_main);
		touch_out = findViewById(R.id.act_mail_out);
		search_ll = (LinearLayout) findViewById(R.id.act_mail_search_ll);
		send_btn = (TextView) findViewById(R.id.act_mail_send_btn);
		load_btn = (TextView) findViewById(R.id.act_mail_load_btn);
		theme_btn = (TextView) findViewById(R.id.act_mail_theme_btn);
		initListenner();
	}

	private void initListenner() {

		mail_main.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mail_main.setFocusable(true);
				mail_main.setFocusableInTouchMode(true);
				mail_main.requestFocus();
				return false;
			}

		});

		touch_out.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int visibility = search_more.getVisibility();
				if (visibility == View.VISIBLE) {
					inVisibalSearch();
					mail_main.setFocusable(true);
					mail_main.setFocusableInTouchMode(true);
					mail_main.requestFocus();
				}
			}

		});

		search_edit.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					visibalSearch();

				} else {
					inVisibalSearch();
				}
			}

		});

		send_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "send", 0).show();
			}
		});

		load_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "load", 0).show();
			}
		});

		theme_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "theme", 0).show();
			}
		});
	}

	private void visibalSearch() {
		search_more.setVisibility(View.VISIBLE);
		search_edit.setBackgroundResource(R.drawable.edit_text_nrbg);
		search_ll.setBackgroundColor(Color.WHITE);
		translate();
	}

	private void inVisibalSearch() {
		search_more.setVisibility(View.GONE);
		search_edit.setBackgroundResource(R.drawable.edit_text);
		search_ll.setBackgroundColor(Color.TRANSPARENT);
	}

	public void translate() {

		// target 动画在哪个控件上播放:iv
		// propertyName 属性名称,控件的属性
		// values 可变参数,动画逐渐变化的属性值
		WindowManager winManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		int height = winManager.getDefaultDisplay().getHeight()
				- search_ll.getHeight();
		ObjectAnimator ta = ObjectAnimator.ofFloat(search_more, "translationY",
				height, 0);
		ta.setDuration(200);
		ta.setRepeatMode(ObjectAnimator.REVERSE);
		// 开始播放
		ta.start();
	}

}
