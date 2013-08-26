package com.appfactory.library;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.appfactory.R;
import com.appfactory.library.adapter.HotlineLayoutAdapter;
import com.appfactory.model.Hotline;
import com.appfactory.service.CommunicateService;
import com.appfactory.service.response.ContactInfoResponse;
import com.appfactory.util.Contants;
import com.appfactory.util.ToastView;

public class CallHotlineActivity extends BaseActivity implements
		OnItemClickListener {
	private LinearLayout loadingProgress;
	private ListView hotlineListView;
	private List<Hotline> hotlineList;
	private HotlineLayoutAdapter adapter;
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.call_hotline);

		hotlineList = new ArrayList<Hotline>();
		initView();
		String str = getIntent().getStringExtra("titlename");
		this.title.setText(str);

		getData();
	}

	private void initView() {
		loadingProgress = (LinearLayout) findViewById(R.id.theme_loading_layout);
		hotlineListView = (ListView) findViewById(R.id.hotline_ist);
		title = (TextView) findViewById(R.id.title_name);
		Button rightButton = (Button) findViewById(R.id.right_btn);
		rightButton.setVisibility(View.GONE);

		adapter = new HotlineLayoutAdapter();
		hotlineListView.setOnItemClickListener(this);
	}

	private void getData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					ContactInfoResponse response = CommunicateService
							.getContactInfo();
					hotlineList = response.getContact_info_list();
					hotlineHandler.sendEmptyMessage(Contants.GET_DATA_SUCUCESS);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	Handler hotlineHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Contants.GET_DATA_SUCUCESS:
				loadingProgress.setVisibility(View.GONE);
				if (hotlineList.size() == 0) {
					ToastView.show(CallHotlineActivity.this, "没有可用号码");
				}else {
					adapter.setDataList(hotlineList);
					hotlineListView.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				}
				break;

			case Contants.GET_DATA_FAIL:
				ToastView.show(CallHotlineActivity.this, "获取号码失败");
				break;
			}
		}
	};

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Hotline line = hotlineList.get(arg2);
		if (PhoneNumberUtils.isGlobalPhoneNumber(line.getPhoneNumber())) {
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ line.getPhoneNumber()));
			startActivity(intent);
		} else {
		}
	}
}
