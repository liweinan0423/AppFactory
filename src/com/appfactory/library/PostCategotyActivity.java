package com.appfactory.library;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.appfactory.R;
import com.appfactory.library.adapter.ArticleCategoryAdapter;
import com.appfactory.model.Article;
import com.appfactory.service.CommunicateService;
import com.appfactory.service.ServiceCallUtil;
import com.appfactory.service.response.PostCategoryResponse;
import com.appfactory.util.Contants;

public class PostCategotyActivity extends BaseActivity {
	private ListView artList;
	private LinearLayout loadingProgress;
	private TextView empty;
	private int id;
	private String titleStr;
	private List<Article> articleDataList;
	private ArticleCategoryAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		id = getIntent().getIntExtra("id", -1);
		titleStr = getIntent().getStringExtra("title");
		setContentView(R.layout.article_list);
		initView();
		getData();
	}

	private void initView() {
		adapter = new ArticleCategoryAdapter();
		TextView title = (TextView) findViewById(R.id.title_name);
		title.setText(titleStr);

		loadingProgress = (LinearLayout) findViewById(R.id.theme_loading_layout);
		artList = (ListView) findViewById(R.id.article_list);
		empty = (TextView) findViewById(R.id.empty_view);

		artList.setOnItemClickListener(itemClick);
	}

	private void getData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					PostCategoryResponse response = CommunicateService
							.getArticleCategory(id);
					articleDataList = response.getPosts();
					handler.sendEmptyMessage(Contants.GET_DATA_SUCUCESS);
				} catch (Exception e) {
					handler.sendEmptyMessage(Contants.GET_DATA_FAIL);
				}
			}
		}).start();
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			loadingProgress.setVisibility(View.GONE);
			switch (msg.what) {
			case Contants.GET_DATA_SUCUCESS:
				if (articleDataList.size() != 0) {
					adapter.setDataList(articleDataList);
					artList.setAdapter(adapter);
				} else {
					empty.setVisibility(View.VISIBLE);
				}
				break;

			case Contants.GET_DATA_FAIL:
				empty.setVisibility(View.VISIBLE);
				break;
			}
		}
	};

	OnItemClickListener itemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Article art = articleDataList.get(arg2);
			Intent intent = new Intent();
			intent.setClass(PostCategotyActivity.this, WebViewActivity.class);
			intent.putExtra("title", art.getTitle());
			intent.putExtra("url", ServiceCallUtil.baseServiceUrl + art.getUrl());
			PostCategotyActivity.this.startActivity(intent);
		}
	};
}
