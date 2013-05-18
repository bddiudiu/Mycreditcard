package com.adam.mycreditcards.entity;

public class Databasebean {
	public static final String DATABASE_NAME = "mcard_db";//���ݿ������
	public static final int DATABASE_VERSION = 1;//���ݿ�İ汾
	public static final String TABLE_NAME_CARDS= "cards";//����
	public static final String TABLE_NAME_BILLS= "bills";//����	
	
	/**
	 * �½����ݿ����
	 * @author Adam
	 */
	//cards�� [id cno���� cname���� cdate������ cback������ cget������]
	public static final String createCards= "Create table "+TABLE_NAME_CARDS+" (_id  integer primary key autoincrement," +
		    "cno text ,cname text ,cdate text,cback text ,cget text)" ;
	//bills�� [id bno�˵��� bname�˵��� bcid��ƬID bcost���ѽ�� bdate���� bplace���ѵص� btype�������� binstallment�Ƿ����]
	public static final String createBills = "Create table "+TABLE_NAME_BILLS+" (_id  integer primary key autoincrement," +
		    "bno text ,bname text ,bcid integer,bcost double ,bdate text,bplace text,btype text,binstallment integer)" ;	
	/**
	 * ��ѯ������Ϣ���
	 */
	public static final String selectOnefromCards = "select * frome"+TABLE_NAME_CARDS+"where _id =" ;
	public static final String selectOnefromBills = "select * frome"+TABLE_NAME_CARDS+"where _id =" ;
}
