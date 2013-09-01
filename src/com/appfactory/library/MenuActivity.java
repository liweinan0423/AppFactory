package com.appfactory.library;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.appfactory.R;
import com.appfactory.library.adapter.MenuLayoutAdapter;
import com.appfactory.model.Hotline;
import com.appfactory.model.MenuCell;
import com.appfactory.service.CommunicateService;
import com.appfactory.service.ServiceCallUtil;
import com.appfactory.service.response.ContactInfoResponse;
import com.appfactory.service.response.MenuLayoutResponse;
import com.appfactory.util.Contants;
import com.appfactory.util.MenuFunctionCode;
import com.appfactory.util.ToastView;

public class MenuActivity extends Activity implements OnItemClickListener {
	private static final String GRID_9 = "GRID_9";
	private static final String GRID_16 = "GRID_16";
	private static final String LIST = "LIST";
	private ListView list;
	private GridView grid;
	private LinearLayout loadingProgress;
	private String layoutType;
	private List<MenuCell> menuData;
	private MenuLayoutAdapter adapter;
	private TextView title;
	private TextView empty;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_scroll);
		initView();
		menuData = new ArrayList<MenuCell>();
		getMenuInfo();
	}

	private void initView() {
		adapter = new MenuLayoutAdapter();
		title = (TextView) this.findViewById(R.id.title_name);
		list = (ListView) this.findViewById(R.id.gv_main_list);
		grid = (GridView) this.findViewById(R.id.gv_main_grid);
		empty = (TextView) findViewById(R.id.empty_text);
		loadingProgress = (LinearLayout) this
				.findViewById(R.id.theme_loading_layout);

		findViewById(R.id.gohome_btn).setVisibility(View.GONE);
		title.setText(getResources().getString(R.string.menu));
		list.setOnItemClickListener(this);
		grid.setOnItemClickListener(this);
	}

	private void getMenuInfo() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					MenuLayoutResponse layoutResponse = CommunicateService
							.getMenuLayout();
					layoutType = layoutResponse.getLayout_type();

					menuData = layoutResponse.getCells();
					dataHandler.sendEmptyMessage(Contants.GET_DATA_SUCUCESS);
				} catch (Exception e) {
					dataHandler.sendEmptyMessage(Contants.GET_DATA_FAIL);
					e.printStackTrace();
				}
			}
		}).start();
	}

	Handler dataHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			loadingProgress.setVisibility(View.GONE);
			switch (msg.what) {
			case Contants.GET_DATA_SUCUCESS:
				if (menuData.size() != 0) {
					if (layoutType.equals(LIST)) {
						adapter.setMenuData(menuData);
						list.setVisibility(View.VISIBLE);
						list.setAdapter(adapter);
					} else if (layoutType.equals(GRID_9)) {
						adapter.setMenuData(menuData);
						grid.setVisibility(View.VISIBLE);
						grid.setNumColumns(3);
						grid.setAdapter(adapter);
					} else if (layoutType.equals(GRID_16)) {
						adapter.setMenuData(menuData);
						grid.setVisibility(View.VISIBLE);
						grid.setNumColumns(4);
						grid.setAdapter(adapter);
					}
					adapter.notifyDataSetChanged();
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if (menuData != null && menuData.size() > 0) {
			final MenuCell cell = menuData.get(arg2);
			String functionCode = cell.getFunctionCode();
			if (functionCode == null) {
				return;
			}
			String tempCode = functionCode.split("/")[0];
			if (tempCode.equals(MenuFunctionCode.CONTACT_INFO)) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							ContactInfoResponse response = CommunicateService
									.getContactInfo();
							List<Hotline> hotlineList = response
									.getContact_info_list();
							if (hotlineList.size() == 1) {
								Intent intent = new Intent(Intent.ACTION_CALL,
										Uri.parse("tel:"
												+ hotlineList.get(0)
														.getPhoneNumber()));
								startActivity(intent);
							} else {
								Intent localIntent1 = new Intent(
										MenuActivity.this,
										CallHotlineActivity.class);
								localIntent1.putExtra("titlename",
										cell.getTitle());
								MenuActivity.this.startActivity(localIntent1);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
			} else if (tempCode.equals(MenuFunctionCode.COMPANY_INFO)) {

			} else if (tempCode.equals(MenuFunctionCode.POST)) {
				if (functionCode.length() > 2) {
					String idStr = functionCode.split("/")[1];
					if (idStr != null) {
						int id = Integer.parseInt(idStr);
						Intent intent = new Intent();
						intent.putExtra("url", ServiceCallUtil.articleURL + id);
						intent.putExtra("title", cell.getTitle());
						intent.setClass(MenuActivity.this,
								WebViewActivity.class);
						MenuActivity.this.startActivity(intent);
						// int id = Integer.parseInt(idStr);
						// Intent intent = new Intent();
						// intent.putExtra("id", id);
						// intent.putExtra("title", cell.getTitle());
						// intent.setClass(MenuActivity.this,
						// ArticleDetailActivity.class);
						// MenuActivity.this.startActivity(intent);
					} else {
						ToastView.show(MenuActivity.this, "无效的文章ID");
					}
				} else {
					ToastView.show(MenuActivity.this, "无效的文章ID");
				}
			} else if (tempCode.equals(MenuFunctionCode.POST_CATEGORY)) {
				if (functionCode.length() > 2) {
					String idStr = functionCode.split("/")[1];
					if (idStr != null) {
						final int id = Integer.parseInt(idStr);
						Intent intent = new Intent();
						intent.putExtra("id", id);
						intent.putExtra("title", cell.getTitle());
						intent.setClass(MenuActivity.this,
								PostCategotyActivity.class);
						MenuActivity.this.startActivity(intent);
					} else {
						ToastView.show(MenuActivity.this, "无效的文章ID");
					}
				} else {
					ToastView.show(MenuActivity.this, "无效的文章分类ID");
				}
			}
		}
	}
}
