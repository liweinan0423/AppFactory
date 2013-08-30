package com.appfactory.library;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.appfactory.R;

public class WebViewActivity extends Activity {
	private WebView webView;
	private Button back;
	private String title;
	private String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		title = intent.getStringExtra("title");
		url = intent.getStringExtra("url");
		setContentView(R.layout.webactivity_layout);
		initView();
		loadWeb();
	}

	private void initView() {
		TextView titleView = (TextView) findViewById(R.id.title_name);
		back = (Button) findViewById(R.id.gohome_btn);
		webView = (WebView) findViewById(R.id.webview);

		findViewById(R.id.right_btn).setVisibility(View.GONE);
		titleView.setText(title);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				WebViewActivity.this.finish();
			}
		});
	}

	private void loadWeb() {
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		webView.loadUrl(url);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
