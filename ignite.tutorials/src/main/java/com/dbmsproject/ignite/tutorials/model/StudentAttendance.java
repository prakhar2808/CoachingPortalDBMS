package com.dbmsproject.ignite.tutorials.model;

import java.io.Serializable;
import java.sql.Date;

public class StudentAttendance implements Serializable{
	private int RollNo;
	private Date date;
	private String isPresent;
	private String CourseId;
	private int BatchId;
	public int getRollNo() {
		return RollNo;
	}
	public void setRollNo(int rollNo) {
		RollNo = rollNo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getIsPresent() {
		return isPresent;
	}
	public void setIsPresent(String isPresent) {
		this.isPresent = isPresent;
	}
	public String getCourseId() {
		return CourseId;
	}
	public void setCourseId(String courseId) {
		CourseId = courseId;
	}
	public int getBatchId() {
		return BatchId;
	}
	public void setBatchId(int batchId) {
		BatchId = batchId;
	}
}
