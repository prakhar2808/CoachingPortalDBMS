package com.dbmsproject.ignite.tutorials.model;

import java.io.Serializable;
import java.sql.Date;

public class TeacherAttendance implements Serializable{
	private int TeacherId;
	private Date date;
	private String isPresent;
	public int getTeacherId() {
		return TeacherId;
	}
	public void setTeacherId(int teacherId) {
		TeacherId = teacherId;
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
