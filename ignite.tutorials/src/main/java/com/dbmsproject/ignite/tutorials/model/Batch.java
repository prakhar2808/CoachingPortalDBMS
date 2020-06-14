package com.dbmsproject.ignite.tutorials.model;

import java.io.Serializable;
import java.sql.Time;
import javax.persistence.Entity;

@Entity
public class Batch implements Serializable{
	private int BatchId;
	private String CourseId;
	private int RoomNo;
	private Time StartTiming;
	private Time EndTiming;
	private String Representation;
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
	public int getRoomNo() {
		return RoomNo;
	}
	public void setRoomNo(int roomNo) {
		RoomNo = roomNo;
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
	public String getRepresentation() {
		return Representation = getBatchId()+" : "+getCourseId();
	}
	public void setRepresentation(String representation) {
		Representation = representation;
	}
}
