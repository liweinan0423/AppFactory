package com.appfactory.library;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MoreActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView t = new TextView(this);
		t.setText("More Activity");
		t.setTextSize(30);
		setContentView(t);
	}
}
