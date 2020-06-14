package com.dbmsproject.ignite.tutorials.model;

import java.io.Serializable;

public class StudyMaterial implements Serializable{
	private String MaterialCode;
	private String CourseId;
	private String TopicName;
	private int Level;
	public String getMaterialCode() {
		return MaterialCode;
	}
	public void setMaterialCode(String materialCode) {
		MaterialCode = materialCode;
	}
	public String getCourseId() {
		return CourseId;
	}
	public void setCourseId(String courseId) {
		CourseId = courseId;
	}
	public String getTopicName() {
		return TopicName;
	}
	public void setTopicName(String topicName) {
		TopicName = topicName;
	}
	public int getLevel() {
		return Level;
	}
	public void setLevel(int level) {
		Level = level;
	}
}
