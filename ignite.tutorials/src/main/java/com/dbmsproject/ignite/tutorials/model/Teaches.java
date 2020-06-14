package com.dbmsproject.ignite.tutorials.model;

import java.io.Serializable;

public class Teaches implements Serializable{
	private int BatchId;
	private String CourseId;
	private int TeacherId;
	public int getBatchId() {
		return BatchId;
	}
	public void setBatchId(int batchId) {
		BatchId = batchId;
	}
	public String getCourseId() {
		return CourseId;
	}
	public void setCourseId(String courseId) {
		CourseId = courseId;
	}
	public int getTeacherId() {
		return TeacherId;
	}
	public void setTeacherId(int teacherId) {
		TeacherId = teacherId;
	}
}
