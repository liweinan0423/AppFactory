package com.appfactory.library;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appfactory.R;
import com.appfactory.model.Article;
import com.appfactory.service.CommunicateService;
import com.appfactory.service.ServiceCallUtil;
import com.appfactory.service.response.PostResponse;
import com.appfactory.util.Contants;

public class ArticleDetailActivity extends BaseActivity {
	private Article article;
	private TextView article_content;
	private TextView article_detail_time;
	private WebView article_detail_wv;
	private TextView article_title;
	private TextView empty;
	private LinearLayout loaddinglayout;
	private int id;
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		id = getIntent().getIntExtra("id", -1);
		String titleStr = getIntent().getStringExtra("title");
		setContentView(R.layout.article_detail);
		initView();
		title.setText(titleStr);
		// getData();
	}

	private void initView() {
		loaddinglayout = (LinearLayout) findViewById(R.id.theme_loading_layout);
		article_content = (TextView) findViewById(R.id.article_detail_content);
		article_detail_time = (TextView) findViewById(R.id.article_detail_time);
		article_detail_wv = (WebView) findViewById(R.id.article_detail_wv);
		article_title = (TextView) findViewById(R.id.article_detail_title);
		empty = (TextView) findViewById(R.id.empty);
		title = (TextView) findViewById(R.id.title_name);

		article_detail_wv.getSettings().setJavaScriptEnabled(true);
		article_detail_wv.setVisibility(View.VISIBLE);
		article_detail_wv.setWebViewClient(new WebViewClient() {

			@Override
			public void onPageFinished(WebView view, String url) {
				loaddinglayout.setVisibility(View.GONE);
				super.onPageFinished(view, url);
			}

			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		article_detail_wv.loadUrl(ServiceCallUtil.articleURL + id);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && article_detail_wv.canGoBack()) {
			article_detail_wv.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	// private void getData() {
	// if (id == -1) {
	// empty.setVisibility(View.VISIBLE);
	// } else {
	// new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	// try {
	// PostResponse response = CommunicateService
	// .getArticle(id);
	// article = response.getPosts();
	// articleHandler
	// .sendEmptyMessage(Contants.GET_DATA_SUCUCESS);
	// } catch (Exception e) {
	// articleHandler.sendEmptyMessage(Contants.GET_DATA_FAIL);
	// }
	// }
	// }).start();
	// }
	// }

	// Handler articleHandler = new Handler() {
	// @Override
	// public void handleMessage(Message msg) {
	// loaddinglayout.setVisibility(View.GONE);
	// switch (msg.what) {
	// case Contants.GET_DATA_SUCUCESS:
	// title.setText(article.getTitle());
	// article_title.setText(article.getTitle());
	// article_detail_wv.loadUrl(article.getUrl());
	// break;
	//
	// case Contants.GET_DATA_FAIL:
	// empty.setVisibility(View.VISIBLE);
	// break;
	// }
	// }
	// };
}
