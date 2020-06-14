package com.dbmsproject.ignite.tutorials.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.StudentAttendanceDao;
import com.dbmsproject.ignite.tutorials.model.StudentAttendance;
import com.dbmsproject.ignite.tutorials.service.StudentAttendanceService;

@Component
public class StudentAttendanceServiceImpl implements StudentAttendanceService{
	@Autowired
	StudentAttendanceDao studentAttendancedao;
	
	@Transactional
	public void addStudentAttendance(StudentAttendance obj) {
		studentAttendancedao.addStudentAttendance(obj);
	}

	@Transactional
	public ArrayList<StudentAttendance> getAttendance(String courseId, int batchId, Date date) {
		return studentAttendancedao.getAttendance(courseId,batchId,date);
	}

	@Transactional
	public List<StudentAttendance> getAttendanceByRollAndCourse(int rollNo, String courseId) {
		return studentAttendancedao.getAttendanceByRollAndCourse(rollNo, courseId);
	}
}
