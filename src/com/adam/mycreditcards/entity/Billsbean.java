package com.adam.mycreditcards.entity;

public class Billsbean {
	public static final String ID = "_id ";
	public static final String BNO = "bno";
	public static final String BNAME = "bname";
	public static final String BCID= "bcid";
	public static final String BCOST = "bcost";
	public static final String BDATE= "bdate";
	public static final String BPLACE= "bplace";
	public static final String BTYPE= "btype";
	public static final String BINSTALLMENT= "binstallment";
	
	private Integer id;
	private String bno;
	private String bname;
	private Integer bcid;
	private Double bcost;
	private String bdate;
	private String bplace;
	private String btype;
	private Integer binstallment;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public Integer getBcid() {
		return bcid;
	}
	public void setBcid(Integer bcid) {
		this.bcid = bcid;
	}
	public Double getBcost() {
		return bcost;
	}
	public void setBcost(Double bcost) {
		this.bcost = bcost;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public String getBplace() {
		return bplace;
	}
	public void setBplace(String bplace) {
		this.bplace = bplace;
	}
	public String getBtype() {
		return btype;
	}
	public void setBtype(String btype) {
		this.btype = btype;
	}
	public Integer getBinstallment() {
		return binstallment;
	}
	public void setBinstallment(Integer binstallment) {
		this.binstallment = binstallment;
	}
	
	
}
