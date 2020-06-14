package com.dbmsproject.ignite.tutorials.model;

import java.io.Serializable;

public class Subject implements Serializable{
	private String CourseId;
	private String SubjectName;
	public String getCourseId() {
		return CourseId;
	}
	public void setCourseId(String courseId) {
		CourseId = courseId;
	}
	public String getSubjectName() {
		return SubjectName;
	}
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}
}
