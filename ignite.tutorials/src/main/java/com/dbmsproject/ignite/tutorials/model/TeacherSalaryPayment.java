package com.dbmsproject.ignite.tutorials.model;

public class TeacherSalaryPayment {
	private String TransactionNo;
	private int Amount;
	private java.sql.Date Date;
	private int TeacherId;
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
	public int getTeacherId() {
		return TeacherId;
	}
	public void setTeacherId(int teacherId) {
		TeacherId = teacherId;
	}
}
