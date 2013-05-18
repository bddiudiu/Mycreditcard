package com.adam.database;

import com.adam.mycreditcards.entity.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Dbhelper部分暂时不会分离出来,后期可以考虑
 * @author Adam
 */
public class DatabaseService {
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DatabaseService(Context ctx) {
		super();
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
		// 创建一个DatabaseHelper对象，只执行这条语句是不会创建或打开连接的
		// databaseHelper = new DatabaseHelper_backup(context, DATABASE_NAME,
		// null, DATABASE_VERSION);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, Databasebean.DATABASE_NAME, null,
					Databasebean.DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		public DatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, Databasebean.DATABASE_NAME, null,
					Databasebean.DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			System.out.println("新建表");
			db.execSQL(Databasebean.createCards);
			db.execSQL(Databasebean.createBills);
		}

		/**
		 * 更新数据库
		 * @author Adam
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			// 更新数据库
		}
	}

	/**
	 * 在执行增删改查操作时，要先打开连接，在进行操作 打开与数据库的连接，只有调用了DatabaseHelper的
	 * @author Adam
	 */
	public DatabaseService open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	/**
	 * 执行玩增删改查后，记住必须要关闭连接
	 * @author Adam
	 */
	public void close() {
		DBHelper.close();
	}

	/**
	 * 插入一条记录
	 * @author Adam
	 */
	public long insertCards(String cno, String cname, String cdate, String cback,
			String cget) {
		// 创建ContentValues对象，插入键值对，其中表的列名是键，值的类型必须和数据库中属性的类型一致
		ContentValues cv = new ContentValues();
		cv.put(Cardsbean.CNO, cno);
		cv.put(Cardsbean.CNAME, cname);
		cv.put(Cardsbean.CDATE, cdate);
		cv.put(Cardsbean.CBACK, cback);
		cv.put(Cardsbean.CGET, cget);
		return db.insert(Databasebean.TABLE_NAME_CARDS, null, cv);
	}
	public long insertBills(String bno, String bname,Integer bcid,Double bcost,String bdate,
			String bplace, String btype,Integer binstallment) {
		// 创建ContentValues对象，插入键值对，其中表的列名是键，值的类型必须和数据库中属性的类型一致
		ContentValues cv = new ContentValues();
		cv.put(Billsbean.BNO, bno);
		cv.put(Billsbean.BNAME, bname);
		cv.put(Billsbean.BCID, bcid);
		cv.put(Billsbean.BCOST, bcost);
		cv.put(Billsbean.BDATE, bdate);
		cv.put(Billsbean.BPLACE, bplace);
		cv.put(Billsbean.BTYPE, btype);
		cv.put(Billsbean.BINSTALLMENT, binstallment);
		return db.insert(Databasebean.TABLE_NAME_BILLS, null, cv);
	}

	/**
	 * 删除一条记录
	 * @author Adam
	 */
	public boolean deletefromCards(int cid) {
		return db.delete(Databasebean.TABLE_NAME_CARDS, Cardsbean.ID + "=" + cid,
				null) > 0;
	}
	public boolean deletefromBills(int bid) {
		return db.delete(Databasebean.TABLE_NAME_BILLS, Billsbean.ID + "=" + bid,
				null) > 0;
	}

	/**
	 * 更新一条记录
	 * @author Adam
	 */
	public void updateCards(Integer id, String cno, String cname, String cdate,
			String cback, String cget) {
		String usql = "UPDATE" + Databasebean.TABLE_NAME_CARDS + "set"
				+ Cardsbean.CNO + "=" + cno + "," + Cardsbean.CNAME + "="
				+ cname + "," + Cardsbean.CDATE + "=" + cdate + ","
				+ Cardsbean.CBACK + "=" + cback + "," + Cardsbean.CGET + "="
				+ cget + "where" + Cardsbean.ID + "=" + id;
		db.execSQL(usql);
	}
	public void updateBills(Integer id, String bno, String bname,Integer bcid,Double bcost,String bdate,
			String bplace, String btype,Integer binstallment) {
		String usql = "UPDATE" + Databasebean.TABLE_NAME_BILLS + "set"
				+ Billsbean.BNO + "=" + bno + "," 
				+ Billsbean.BNAME + "="+ bname + "," 
				+ Billsbean.BCID + "=" + bcid + ","
				+ Billsbean.BCOST + "=" + bcost + "," 
				+ Billsbean.BDATE + "="+ bdate+","
				+ Billsbean.BPLACE + "=" + bplace+ ","
				+ Billsbean.BTYPE + "="+ btype+","
				+ Billsbean.BINSTALLMENT + "="+ binstallment
				+ "where" + Billsbean.ID + "=" + id;
		db.execSQL(usql);
	}

	/**
	 * 查找所有信息
	 * @author Adam
	 */
	public Cursor getAllCards() {
		return db.query(Databasebean.TABLE_NAME_CARDS, null, null, null, null, null,
				Cardsbean.ID + " DESC");
	}
	public Cursor getAllBills() {
		return db.query(Databasebean.TABLE_NAME_BILLS, null, null, null, null, null,
				Billsbean.ID + " DESC");
	}

	/**
	 * 查找一条信息
	 * @author Adam
	 */
	public Cursor getOneCards(Integer id) {
		Cursor cursor = db.query(true, Databasebean.TABLE_NAME_CARDS, new String[] {
				Cardsbean.ID, Cardsbean.CNO, Cardsbean.CNAME, Cardsbean.CDATE,
				Cardsbean.CBACK, Cardsbean.CGET }, Cardsbean.ID + "=" + id,
				null, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}
	public Cursor getOneBills(Integer id) {
		Cursor cursor = db.query(true, Databasebean.TABLE_NAME_BILLS, new String[] {
				Billsbean.ID, Billsbean.BNO,Billsbean.BNAME,Billsbean.BCID,Billsbean.BCOST,Billsbean.BDATE,Billsbean.BPLACE,Billsbean.BTYPE,Billsbean.BINSTALLMENT}, Billsbean.ID + "=" + id,
				null, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}

	/**
	 * 查找五条信息
	 * @author Adam
	 */
	public Cursor seletctTop5ofCards() {
		return db.query(Databasebean.TABLE_NAME_CARDS, null, "top 5", null, null, null,
				Cardsbean.ID + " DESC");
	}
	public Cursor seletctTop5ofBills() {
		return db.query(Databasebean.TABLE_NAME_BILLS, null, "top 5", null, null, null,
				Cardsbean.ID + " DESC");
	}

}
