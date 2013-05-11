package com.adam.mycreditcards.entity;

public class Cardsbean {
	public static final String ID = "_id";
	public static final String CNO= "cno";	
	public static final String CNAME= "cname";
	public static final String CDATE = "cdate";
	public static final String CBACK = "cback";
	public static final String CGET = "cget";
	public static final String DATABASE_NAME = "mcard_db";//���ݿ������
	public static final int DATABASE_VERSION = 1;//���ݿ�İ汾
	public static final String TABLE_NAME = "cards";
	public static final String createsql = "Create table "+TABLE_NAME+" (_id  integer primary key autoincrement," +
		    "cno text ,cname text ,cdate text,cback text ,cget text)" ;
	public static final String selectOne = "select * frome"+TABLE_NAME+"where _id =" ;
	
	private String id;
	private String cno;	
	private String cname;
	private String cdate;
	private String cback;
	private String cget;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdata) {
		this.cdate = cdata;
	}
	public String getCback() {
		return cback;
	}
	public void setCback(String cback) {
		this.cback = cback;
	}
	public String getCget() {
		return cget;
	}
	public void setCget(String cget) {
		this.cget = cget;
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	
}
