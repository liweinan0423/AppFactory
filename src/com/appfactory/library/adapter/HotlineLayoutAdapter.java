package com.appfactory.library.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appfactory.R;
import com.appfactory.model.Hotline;
import com.appfactory.util.App;

public class HotlineLayoutAdapter extends BaseAdapter {
	private List<Hotline> list;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HotlineItem item;
		if (convertView == null) {
			convertView = LayoutInflater.from(App.getContext()).inflate(
					R.layout.call_hotline_item, null);
			item = new HotlineItem();
			item.title = (TextView) convertView
					.findViewById(R.id.hotline_title);
			item.number = (TextView) convertView.findViewById(R.id.hotline_num);
			convertView.setTag(item);
		} else {
			item = (HotlineItem) convertView.getTag();
		}
		item.title.setText(list.get(position).getPhoneName());
		item.number.setText(list.get(position).getPhoneNumber());
		return convertView;
	}

	public void setDataList(List<Hotline> list) {
		this.list = list;
	}

	class HotlineItem {
		public TextView title;
		public TextView number;
	}

}
