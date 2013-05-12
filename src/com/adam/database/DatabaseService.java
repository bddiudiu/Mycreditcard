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
		// ����һ��DatabaseHelper����ִֻ����������ǲ��ᴴ��������ӵ�
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
			System.out.println("�½���");
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
	 * ��ִ����ɾ�Ĳ����ʱ��Ҫ�ȴ����ӣ��ڽ��в��� �������ݿ�����ӣ�ֻ�е�����DatabaseHelper��
	 */

	public DatabaseService open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	/**
	 * ִ������ɾ�Ĳ�󣬼�ס����Ҫ�ر�����
	 */
	public void close() {
		DBHelper.close();
	}

	/**
	 * ����һ����¼
	 * 
	 * @param info
	 * @return
	 */

//	public long insert(CardInfo info) {
//		// ����ContentValues���󣬲����ֵ�ԣ����б�������Ǽ���ֵ�����ͱ�������ݿ������Ե�����һ��
//		ContentValues cv = new ContentValues();
//		cv.put(CARD_NAME, info.cname);
//		cv.put(CARD_DATE, info.cdata);
//		cv.put(CARD_BACK, info.cback);
//		cv.put(CARD_GET, info.cget);
//		return wdb.insert(TABLE_NAME, null, cv);
//	}
	
	
	public long insertNew(String cno, String cname , String cdate , String cback, String cget) {
		// ����ContentValues���󣬲����ֵ�ԣ����б�������Ǽ���ֵ�����ͱ�������ݿ������Ե�����һ��
		ContentValues cv = new ContentValues();
		cv.put(Cardsbean.CNO, cno);
		cv.put(Cardsbean.CNAME, cname);
		cv.put(Cardsbean.CDATE, cdate);
		cv.put(Cardsbean.CBACK, cback);
		cv.put(Cardsbean.CGET, cget);
		return db.insert(Cardsbean.TABLE_NAME, null, cv);
	}

	/**
	 * ɾ��һ����¼
	 * 
	 * @param id
	 * @return
	 */
	public boolean  delete(int cid) {
		return db.delete(Cardsbean.TABLE_NAME, Cardsbean.ID + "="+ cid, null) > 0;
	}

	/**
	 * ����һ����¼
	 * 
	 * @param info
	 */
	public void update(String id,String cno, String cname , String cdate , String cback, String cget) {
		String isql = "UPDATE"+Cardsbean.TABLE_NAME+"set"+Cardsbean.CNO+"="+cno+","+Cardsbean.CNAME+"="+cname+","+Cardsbean.CDATE+"="+cdate+","
	+Cardsbean.CBACK+"="+cback+","+Cardsbean.CGET+"="+cget+"where"+Cardsbean.ID+"="+id;
		db.execSQL(isql);
	}
	
	
	
	/**
	 * ����������Ϣ
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
	 * ���Ҷ�Ӧ����Ϣ
	 * 
	 * @param ����
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
