package com.dbmsproject.ignite.tutorials.model;

public class Course {
	
	private String CourseId;
	private String CourseName;
	private int FeesPerMonth;
	private String Representation;

	public String getCourseId() {
		return CourseId;
	}

	public void setCourseId(String courseId) {
		CourseId = courseId;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public int getFeesPerMonth() {
		return FeesPerMonth;
	}

	public void setFeesPerMonth(int feesPerMonth) {
		FeesPerMonth = feesPerMonth;
	}

	public String getRepresentation() {
		return Representation = getCourseId()+": "+getCourseName();
	}

	public void setRepresentation(String representation) {
		Representation = representation;
	}
}
