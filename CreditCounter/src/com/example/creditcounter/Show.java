package com.example.creditcounter;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

public class Show extends Activity implements OnClickListener{

	ImageButton but_show_add;
	ListView creditelist;
	Spinner spinner;
	static String[] time = {"大一 上学期", "大一 下学期", "大二 上学期", "大二 下学期", "大三 上学期", "大三 下学期"};
	ShowAdapter myadapter;
	public ArrayAdapter<String> timelist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		but_show_add = (ImageButton) findViewById(R.id.imgb_show_add);
		creditelist = (ListView) findViewById(R.id.lv_show);
		spinner = (Spinner) findViewById(R.id.spinner_show);
		
		but_show_add.setOnClickListener(this);
		
		myadapter = new ShowAdapter(this);
		creditelist.setAdapter(myadapter);
		
		timelist = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, time);
		spinner.setAdapter(timelist);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imgb_show_add:
			Intent intent = new Intent(Show.this, InputActivity.class);
			intent.putExtra("show", 0);
			startActivity(intent);
			break;
		}
	}
}
