package com.appfactory.library.adapter;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appfactory.R;
import com.appfactory.model.MenuCell;
import com.appfactory.util.App;
import com.appfactory.util.SyncImageLoader;
import com.appfactory.util.SyncImageLoader.OnImageLoadListener;

public class MenuLayoutAdapter extends BaseAdapter {
	private List<MenuCell> datas;
	private SyncImageLoader loader;

	public MenuLayoutAdapter() {
		datas = new ArrayList<MenuCell>();
		loader = new SyncImageLoader();
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MenuView view;
		if (convertView == null) {
			convertView = LayoutInflater.from(App.getContext()).inflate(
					R.layout.menu_app_item, null);
			view = new MenuView();
			view.image = (ImageView) convertView.findViewById(R.id.ivAppIcon);
			view.text = (TextView) convertView.findViewById(R.id.tvAppName);
			convertView.setTag(view);
		} else {
			view = (MenuView) convertView.getTag();
		}
		view.image.setImageResource(R.drawable.def_icon);
		view.text.setText(datas.get(position).getTitle());
		loader.loadImage(position, view.image,
				datas.get(position).getIconURL(), loadListener);
		return convertView;
	}

	OnImageLoadListener loadListener = new OnImageLoadListener() {

		@Override
		public void onImageLoad(View view, Drawable drawable) {
			if (view instanceof ImageView) {
				((ImageView) view).setImageDrawable(drawable);
			} else {
				view.setBackgroundDrawable(drawable);
			}

		}

		@Override
		public void onError(Integer t) {

		}
	};

	public void setMenuData(List<MenuCell> datas) {
		this.datas = datas;
	}

	class MenuView {
		public ImageView image;
		public TextView text;
	}

}
