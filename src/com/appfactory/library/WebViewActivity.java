package com.appfactory.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appfactory.R;

public class WebViewActivity extends BaseActivity {
	private WebView webView;
	private String title;
	private String url;
	private LinearLayout loadingProgress;

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
		webView = (WebView) findViewById(R.id.webview);
		loadingProgress = (LinearLayout) findViewById(R.id.theme_loading_layout);

		webView.getSettings().setJavaScriptEnabled(true);
		findViewById(R.id.right_btn).setVisibility(View.GONE);
		titleView.setText(title);
	}

	private void loadWeb() {
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				loadingProgress.setVisibility(View.GONE);
				super.onPageFinished(view, url);
			}

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
