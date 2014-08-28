package com.example.creditcounter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class InputActivity extends Activity implements OnClickListener{

	Spinner spineer;
	EditText et_subj, et_num, et_credite;
	Button bt_confirm, bt_cancel;
	TextView tv_content;
	SQLite database;
	String sub = "";
	double num, credit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.input);
		
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		spineer = (Spinner) findViewById(R.id.spinner);
		et_subj = (EditText) findViewById(R.id.et_subject);
		et_num = (EditText) findViewById(R.id.et_num);
		et_credite = (EditText) findViewById(R.id.et_credit);
		tv_content = (TextView) findViewById(R.id.tv_content);
		bt_confirm = (Button) findViewById(R.id.bt_input_ok);
		bt_cancel = (Button) findViewById(R.id.bt_input_cancel);
		database = new SQLite(this);
		
		ArrayAdapter<String> timeli = new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_item, Show.time);
		spineer.setAdapter(timeli);
		
		bt_cancel.setOnClickListener(this);
		bt_confirm.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_input_ok:
			sub = et_subj.getText().toString();
			num = Double.valueOf(et_num.getText().toString());
			credit = Double.valueOf(et_credite.getText().toString());
			database.input(sub, num, credit);
			break;
		case R.id.bt_input_cancel:
			
			break;
		}
	}
}
