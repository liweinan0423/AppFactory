package com.appfactory.library.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TabView extends RelativeLayout {
	private ImageView image;
	private TextView text;

	public TabView(Context context, String str, int imageId) {
		super(context);
		this.image = new ImageView(context);
		this.text = new TextView(context);
		RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		imageParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,
				RelativeLayout.TRUE);
		imageParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		textParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
				RelativeLayout.TRUE);
		textParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		image.setImageResource(imageId);
		text.setText(str);
		text.setTextColor(context.getResources().getColorStateList(android.R.color.white));
		text.setTextSize(12.0F);
		this.addView(image, imageParams);
		this.addView(text, textParams);
	}
}
