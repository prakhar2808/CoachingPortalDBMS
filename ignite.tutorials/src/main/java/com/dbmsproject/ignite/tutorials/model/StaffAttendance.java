package com.dbmsproject.ignite.tutorials.model;

import java.io.Serializable;
import java.sql.Date;

public class StaffAttendance implements Serializable{
	private int StaffId;
	private Date date;
	private String isPresent;
	public int getStaffId() {
		return StaffId;
	}
	public void setStaffId(int staffId) {
		StaffId = staffId;
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
}
