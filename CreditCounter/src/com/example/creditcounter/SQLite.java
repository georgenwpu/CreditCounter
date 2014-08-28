package com.example.creditcounter;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class SQLite {

	private static final String KEY_ROWID = "id";
	private static final String KEY_SUBJ = "subjects";
	private static final String KEY_NUM = "number";
	private static final String KEY_CREDIT = "credit";
	
	private static final String DATABASE_NAME = "database";
	private static final String TABLE_NAME = "tablename";
	private static final int DATABASE_VERSION = 1;
	
	DBHelper helper;
	SQLiteDatabase mydatabase;
	Context myContext;
	
	private static class DBHelper extends SQLiteOpenHelper{

		public DBHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE "+TABLE_NAME+" ( "
					+KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+KEY_SUBJ + " VARCHAR NOT NULL, "
					+KEY_NUM + " DOUBLE NOT NULL, "
					+KEY_CREDIT + " DOUBLE NOT NULL );");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXITS " + TABLE_NAME);
			onCreate(db);
		}
/**
 * open（）是不会自己出现的，一定手动建立，否则打不开数据库		
 */
		@Override
		public void onOpen(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			super.onOpen(db);
		}
		
	}

	public SQLite(Context context){
		myContext = context;
	}
	
	public SQLite open() {
		helper = new DBHelper(myContext, DATABASE_NAME, null, DATABASE_VERSION);
		mydatabase = helper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		helper.close();
	}
	
	public long input(String sub, double num, double credit) {
		open();
		ContentValues cv = new ContentValues();
		cv.put(KEY_SUBJ, sub);
		cv.put(KEY_NUM, num);
		cv.put(KEY_CREDIT, credit);
		showMsg(sub +"-"+ num +"-"+ credit);
		long result = mydatabase.insert(TABLE_NAME, null, cv);
		Log.d("SQL", result + "");
		close();
		return result;
	}
/**
 * 在这里虽然没有明显使用SQLitedatabase，但是cursor的初始化使用的mydatabase，也就是说，一样要调用open函数
 */
	public ArrayList<Showxx> read(String selection) {
		open();
		String[] columns = {KEY_SUBJ, KEY_NUM, KEY_CREDIT};
		ArrayList<Showxx> showlist = new ArrayList<Showxx>();
		Cursor cursor = mydatabase.query(TABLE_NAME, columns, selection, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()&&cursor.getString(1)!=null){
			Showxx showxx = new Showxx();
			showxx.setSubject(cursor.getString(1));
			showxx.setFenshu(cursor.getDouble(2));
			showxx.setCredite(cursor.getDouble(3));
			showlist.add(showxx);
			cursor.moveToNext();
		}
		cursor.close();
		return showlist;
		
	}
	
	public void deleterow(int id) {
		mydatabase.delete(DATABASE_NAME, KEY_ROWID +"="+id, null);
	}
	
	public void showMsg(String text) {
		Toast.makeText(myContext, text, Toast.LENGTH_LONG).show();
	}
}
