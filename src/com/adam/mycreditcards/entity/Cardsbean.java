package com.adam.mycreditcards.entity;

public class Cardsbean {
	public static final String ID = "_id";
	public static final String CNO= "cno";	
	public static final String CNAME= "cname";
	public static final String CDATE = "cdate";
	public static final String CBACK = "cback";
	public static final String CGET = "cget";

	
	private Integer id;
	private String cno;	
	private String cname;
	private String cdate;
	private String cback;
	private String cget;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
