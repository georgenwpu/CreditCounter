package com.example.creditcounter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ShowAdapter extends BaseAdapter{

	ArrayList<Showxx> showlist;
	Context context;
	SQLite mysql;
	
	public ShowAdapter(Context context){
		this.context = context;
		showlist = new ArrayList<Showxx>();
		mysql = new SQLite(context);
		showlist = mysql.read("id = 1");
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return showlist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return showlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
		TextView s_subj = (TextView) convertView.findViewById(R.id.tv_item_sunj);
		TextView s_credit = (TextView) convertView.findViewById(R.id.tv_item_credit);
		TextView s_num = (TextView) convertView.findViewById(R.id.tv_item_num);
		Showxx showxx = new Showxx();
		showxx = showlist.get(position);
		s_subj.setText(showxx.getSubject());
		s_num.setText(String.valueOf(showxx.getFenshu()));
		s_credit.setText(String.valueOf(showxx.getCredite()));
		return convertView;
	}


}
