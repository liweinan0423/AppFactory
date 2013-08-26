package com.appfactory.library;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView t = new TextView(this);
		t.setText("Home Activity");
		t.setTextSize(30);
		setContentView(t);

	}
}
