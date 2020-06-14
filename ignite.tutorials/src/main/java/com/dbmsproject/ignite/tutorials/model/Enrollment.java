package com.dbmsproject.ignite.tutorials.model;

import java.io.Serializable;
import java.sql.Date;

public class Enrollment implements Serializable{
	private String TransactionNo;
	private Date JoinDate;
	private Date EndDate;
	private int EnrolledBy;
	private int BatchId;
	private String CourseId;
	private int RollNo;
	public String getTransactionNo() {
		return TransactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		TransactionNo = transactionNo;
	}
	public Date getJoinDate() {
		return JoinDate;
	}
	public void setJoinDate(Date joinDate) {
		JoinDate = joinDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public int getEnrolledBy() {
		return EnrolledBy;
	}
	public void setEnrolledBy(int enrolledBy) {
		EnrolledBy = enrolledBy;
	}
	public String getCourseId() {
		return CourseId;
	}
	public void setCourseId(String courseId) {
		CourseId = courseId;
	}
	public int getRollNo() {
		return RollNo;
	}
	public void setRollNo(int rollNo) {
		RollNo = rollNo;
	}
	public int getBatchId() {
		return BatchId;
	}
	public void setBatchId(int batchId) {
		BatchId = batchId;
	}
	
}
