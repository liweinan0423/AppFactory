package com.appfactory.library;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView t = new TextView(this);
		t.setText("Order Activity");
		t.setTextSize(30);
		setContentView(t);

	}
}
