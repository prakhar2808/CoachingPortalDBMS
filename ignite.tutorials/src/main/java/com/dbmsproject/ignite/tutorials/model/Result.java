package com.dbmsproject.ignite.tutorials.model;

import java.io.Serializable;
import java.sql.Date;

public class Result implements Serializable{
	private String ExamId;
	private String CourseId;
	private Date ResultDeclarationDate;
	private int StudentRank;
	private int RollNo;
	private int Marks;
	public String getExamId() {
		return ExamId;
	}
	public void setExamId(String examId) {
		ExamId = examId;
	}
	public String getCourseId() {
		return CourseId;
	}
	public void setCourseId(String courseId) {
		CourseId = courseId;
	}
	public Date getResultDeclarationDate() {
		return ResultDeclarationDate;
	}
	public void setResultDeclarationDate(Date resultDeclarationDate) {
		ResultDeclarationDate = resultDeclarationDate;
	}
	public int getStudentRank() {
		return StudentRank;
	}
	public void setStudentRank(int studentRank) {
		StudentRank = studentRank;
	}
	public int getMarks() {
		return Marks;
	}
	public void setMarks(int marks) {
		Marks = marks;
	}
	public int getRollNo() {
		return RollNo;
	}
	public void setRollNo(int rollNo) {
		RollNo = rollNo;
	}
}
