package com.dbmsproject.ignite.tutorials.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Exam implements Serializable{
	private String ExamId;
	private String CourseId;
	private String ExamName;
	private Date ExamDate;
	private Time StartTiming;
	private Time EndTiming;
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
	public String getExamName() {
		return ExamName;
	}
	public void setExamName(String examName) {
		ExamName = examName;
	}
	public Date getExamDate() {
		return ExamDate;
	}
	public void setExamDate(Date examDate) {
		ExamDate = examDate;
	}
	public Time getStartTiming() {
		return StartTiming;
	}
	public void setStartTiming(Time startTiming) {
		StartTiming = startTiming;
	}
	public Time getEndTiming() {
		return EndTiming;
	}
	public void setEndTiming(Time endTiming) {
		EndTiming = endTiming;
	}
}
