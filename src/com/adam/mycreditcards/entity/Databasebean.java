package com.adam.mycreditcards.entity;

public class Databasebean {
	public static final String DATABASE_NAME = "mcard_db";//数据库的名字
	public static final int DATABASE_VERSION = 1;//数据库的版本
	public static final String TABLE_NAME_CARDS= "cards";//表名
	public static final String TABLE_NAME_BILLS= "bills";//表名	
	
	/**
	 * 新建数据库语句
	 * @author Adam
	 */
	//cards表 [id cno卡号 cname卡名 cdate记账日 cback还款日 cget记账日]
	public static final String createCards= "Create table "+TABLE_NAME_CARDS+" (_id  integer primary key autoincrement," +
		    "cno text ,cname text ,cdate text,cback text ,cget text)" ;
	//bills表 [id bno账单号 bname账单名 bcid卡片ID bcost消费金额 bdate日期 bplace消费地点 btype消费类型 binstallment是否分期]
	public static final String createBills = "Create table "+TABLE_NAME_BILLS+" (_id  integer primary key autoincrement," +
		    "bno text ,bname text ,bcid integer,bcost double ,bdate text,bplace text,btype text,binstallment integer)" ;	
	/**
	 * 查询单条信息语句
	 */
	public static final String selectOnefromCards = "select * frome"+TABLE_NAME_CARDS+"where _id =" ;
	public static final String selectOnefromBills = "select * frome"+TABLE_NAME_CARDS+"where _id =" ;
}
