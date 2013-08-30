package com.appfactory.library;

import com.appfactory.R;
import com.appfactory.library.view.AnimationTabHost;
import com.appfactory.library.view.TabView;
import com.appfactory.util.App;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.TabActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

public class MainActivity extends TabActivity {
	private AnimationTabHost tabhost;
	private int pressBackCount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.d("test", "server ip " + App.getServerIP());
		tabhost = (AnimationTabHost) findViewById(android.R.id.tabhost);
		tabhost.setup();
		tabhost.setOpenAnimation(true);

		TabView menuTable = new TabView(this, getString(R.string.menu),
				R.drawable.icon_03);
		Intent menuIntent = new Intent().setClass(this, MenuActivity.class);
		tabhost.addTab(tabhost.newTabSpec("MENU").setIndicator(menuTable)
				.setContent(menuIntent));

		TabView homeTable = new TabView(this, getString(R.string.home),
				R.drawable.icon_04);
		Intent homeIntent = new Intent().setClass(this, HomeActivity.class);
		tabhost.addTab(tabhost.newTabSpec("HOME").setIndicator(homeTable)
				.setContent(homeIntent));

		TabView shoppingTrolleyTable = new TabView(this,
				getString(R.string.shopping_trolley), R.drawable.icon_05);
		Intent shoppingTrolleyIntent = new Intent().setClass(this,
				ShoppingTrolleyActivity.class);
		tabhost.addTab(tabhost.newTabSpec("ShoppingTrolley")
				.setIndicator(shoppingTrolleyTable)
				.setContent(shoppingTrolleyIntent));

		TabView orderTable = new TabView(this, getString(R.string.order),
				R.drawable.icon_06);
		Intent orderIntent = new Intent().setClass(this, OrderActivity.class);
		tabhost.addTab(tabhost.newTabSpec("ORDER").setIndicator(orderTable)
				.setContent(orderIntent));

		TabView moreTable = new TabView(this, getString(R.string.more),
				R.drawable.more);
		Intent moreIntent = new Intent().setClass(this, MoreActivity.class);
		tabhost.addTab(tabhost.newTabSpec("MORE").setIndicator(moreTable)
				.setContent(moreIntent));

		tabhost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				Log.d("test", "tap button " + tabId);
			}
		});
		tabhost.setCurrentTab(0);
	}
	
	@Override
	public void onBackPressed() {
		pressBackCount++;
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				SystemClock.sleep(3000);
				pressBackCount = 0;
			}
		});
		if (pressBackCount < 2) {
			Toast.makeText(MainActivity.this,
					R.string.double_press_exit_app, Toast.LENGTH_SHORT).show();
			thread.start();
		} else {
			if (thread.isAlive()) {
				thread.stop();
			}
			finish();
			System.exit(0);
		}
	}
}
