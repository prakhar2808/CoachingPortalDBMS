package com.dbmsproject.ignite.tutorials.model;

public class StaffSalaryPayment {
	private String TransactionNo;
	private int Amount;
	private java.sql.Date Date;
	private int StaffId;
	public String getTransactionNo() {
		return TransactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		TransactionNo = transactionNo;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public java.sql.Date getDate() {
		return Date;
	}
	public void setDate(java.sql.Date date) {
		Date = date;
	}
	public int getStaffId() {
		return StaffId;
	}
	public void setStaffId(int staffId) {
		StaffId = staffId;
	}
}
