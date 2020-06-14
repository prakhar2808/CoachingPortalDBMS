package com.dbmsproject.ignite.tutorials.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dbmsproject.ignite.tutorials.model.StudentAttendance;

@Component
public interface StudentAttendanceService {
	public void addStudentAttendance(StudentAttendance obj);

	public ArrayList<StudentAttendance> getAttendance(String courseId, int batchId, Date date);

	public List<StudentAttendance> getAttendanceByRollAndCourse(int rollNo, String courseId);
}	
