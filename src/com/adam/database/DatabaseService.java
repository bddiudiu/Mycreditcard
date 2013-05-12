package com.adam.database;

import com.adam.mycreditcards.entity.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseService {
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	public DatabaseService(Context ctx) {
		super();
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
		// 创建一个DatabaseHelper对象，只执行这条语句是不会创建或打开连接的
//		databaseHelper = new DatabaseHelper_backup(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper{

		public DatabaseHelper(Context context) {
			super(context,Cardsbean.DATABASE_NAME,null,Cardsbean.DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}
		
		public DatabaseHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, Cardsbean.DATABASE_NAME, null, Cardsbean.DATABASE_VERSION);}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			System.out.println("新建表");
			db.execSQL(Cardsbean.createsql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS titles");
			onCreate(db);

		}
		
		
	}
	
	
	/**
	 * 在执行增删改查操作时，要先打开连接，在进行操作 打开与数据库的连接，只有调用了DatabaseHelper的
	 */

	public DatabaseService open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	/**
	 * 执行玩增删改查后，记住必须要关闭连接
	 */
	public void close() {
		DBHelper.close();
	}

	/**
	 * 插入一条记录
	 * 
	 * @param info
	 * @return
	 */

//	public long insert(CardInfo info) {
//		// 创建ContentValues对象，插入键值对，其中表的列名是键，值的类型必须和数据库中属性的类型一致
//		ContentValues cv = new ContentValues();
//		cv.put(CARD_NAME, info.cname);
//		cv.put(CARD_DATE, info.cdata);
//		cv.put(CARD_BACK, info.cback);
//		cv.put(CARD_GET, info.cget);
//		return wdb.insert(TABLE_NAME, null, cv);
//	}
	
	
	public long insertNew(String cno, String cname , String cdate , String cback, String cget) {
		// 创建ContentValues对象，插入键值对，其中表的列名是键，值的类型必须和数据库中属性的类型一致
		ContentValues cv = new ContentValues();
		cv.put(Cardsbean.CNO, cno);
		cv.put(Cardsbean.CNAME, cname);
		cv.put(Cardsbean.CDATE, cdate);
		cv.put(Cardsbean.CBACK, cback);
		cv.put(Cardsbean.CGET, cget);
		return db.insert(Cardsbean.TABLE_NAME, null, cv);
	}

	/**
	 * 删除一条记录
	 * 
	 * @param id
	 * @return
	 */
	public boolean  delete(int cid) {
		return db.delete(Cardsbean.TABLE_NAME, Cardsbean.ID + "="+ cid, null) > 0;
	}

	/**
	 * 更新一条记录
	 * 
	 * @param info
	 */
	public void update(String id,String cno, String cname , String cdate , String cback, String cget) {
		String isql = "UPDATE"+Cardsbean.TABLE_NAME+"set"+Cardsbean.CNO+"="+cno+","+Cardsbean.CNAME+"="+cname+","+Cardsbean.CDATE+"="+cdate+","
	+Cardsbean.CBACK+"="+cback+","+Cardsbean.CGET+"="+cget+"where"+Cardsbean.ID+"="+id;
		db.execSQL(isql);
	}
	
	
	
	/**
	 * 查找所有信息
	 */
	public Cursor getAll(){
		return db.query(Cardsbean.TABLE_NAME, null, null, null, null,
				null, Cardsbean.ID + " DESC");
	}
	
	
	public Cursor getOne(String id){
		Cursor cursor = db.query(true, Cardsbean.TABLE_NAME, new String[]{Cardsbean.ID,Cardsbean.CNO,Cardsbean.CNAME,Cardsbean.CDATE,Cardsbean.CBACK,Cardsbean.CGET},
				Cardsbean.ID+ "=" + id,
				null, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}

	/**
	 * 查找对应的信息
	 * 
	 * @param 暂无
	 * @return
	 */
//	public Cursor select(String queryThreadId) {
//		String queryStr = null;
//		if (queryThreadId != null && !"".equals(queryThreadId)) {
//			queryStr = "thread_id = " + queryThreadId;
//		}
//		return rdb.query(TABLE_NAME, null, queryStr, null, null, null,
//				"date desc");
//	}
	

	
	
}
