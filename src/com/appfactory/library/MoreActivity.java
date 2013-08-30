package com.appfactory.library;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.appfactory.R;

public class MoreActivity extends Activity {
	private Button company_info;
	private Button contact_us;
	private Button update_version;
	private Button recent_view;
	private Button favorite;
	private Button support;
	private Button about;
	private TextView title;
	private Button refresh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_scroll);
		initView();
	}

	private void initView() {
		company_info = (Button) this.findViewById(R.id.company_info_btn);
		contact_us = (Button) this.findViewById(R.id.contact_us_btn);
		update_version = (Button) this.findViewById(R.id.update_version_btn);
		recent_view = (Button) this.findViewById(R.id.recent_view_btn);
		favorite = (Button) this.findViewById(R.id.favorit_btn);
		support = (Button) this.findViewById(R.id.support_btn);
		about = (Button) this.findViewById(R.id.about_btn);
		refresh = (Button) this.findViewById(R.id.right_btn);

		title = (TextView) this.findViewById(R.id.title_name);
		title.setText(getResources().getString(R.string.more));

		company_info.setOnClickListener(buttonListener);
		contact_us.setOnClickListener(buttonListener);
		update_version.setOnClickListener(buttonListener);
		recent_view.setOnClickListener(buttonListener);
		favorite.setOnClickListener(buttonListener);
		support.setOnClickListener(buttonListener);
		about.setOnClickListener(buttonListener);
		refresh.setOnClickListener(buttonListener);

		findViewById(R.id.gohome_btn).setVisibility(View.GONE);
	}

	OnClickListener buttonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v == refresh) {

			} else if (v == recent_view) {

			} else if (v == favorite) {

			} else if (v == update_version) {

			} else {
				Intent goWebIntent = new Intent();
				goWebIntent.setClass(MoreActivity.this, WebViewActivity.class);
				if (v == company_info) {
					goWebIntent.putExtra("title", MoreActivity.this
							.getResources()
							.getString(R.string.company_info_str));
					goWebIntent.putExtra("url", "http://www.baidu.com");
				} else if (v == contact_us) {
					goWebIntent.putExtra("title", MoreActivity.this
							.getResources().getString(R.string.contact_us_str));
					goWebIntent.putExtra("url", "http://www.baidu.com");
				} else if (v == about) {
					goWebIntent.putExtra("title", MoreActivity.this
							.getResources().getString(R.string.about_str));
					goWebIntent.putExtra("url", "http://www.baidu.com");
				} else if (v == support) {
					goWebIntent.putExtra("title", MoreActivity.this
							.getResources().getString(R.string.support_str));
					goWebIntent.putExtra("url", "http://www.baidu.com");
				}
				MoreActivity.this.startActivity(goWebIntent);
			}
		}
	};
}
