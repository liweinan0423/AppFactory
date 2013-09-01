package com.appfactory.library.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appfactory.R;
import com.appfactory.model.Article;
import com.appfactory.model.Hotline;
import com.appfactory.util.App;

public class ArticleCategoryAdapter extends BaseAdapter {
	private List<Article> list;

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
		ArticleItem item;
		if (convertView == null) {
			convertView = LayoutInflater.from(App.getContext()).inflate(
					R.layout.article_list_item, null);
			item = new ArticleItem();
			item.title = (TextView) convertView
					.findViewById(R.id.article_title);
			convertView.setTag(item);
		} else {
			item = (ArticleItem) convertView.getTag();
		}
		item.title.setText(list.get(position).getTitle());
		return convertView;
	}

	public void setDataList(List<Article> list) {
		this.list = list;
	}

	class ArticleItem {
		public TextView title;
	}

}
