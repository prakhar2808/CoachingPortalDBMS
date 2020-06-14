package com.dbmsproject.ignite.tutorials.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.TeacherAttendanceDao;
import com.dbmsproject.ignite.tutorials.model.TeacherAttendance;
import com.dbmsproject.ignite.tutorials.service.TeacherAttendanceService;

@Component
public class TeacherAttendanceServiceImpl implements TeacherAttendanceService{
	
	@Autowired
	TeacherAttendanceDao teacherAttendanceDao;
	
	@Transactional
	public void addTeacherAttendance(TeacherAttendance obj) {
		teacherAttendanceDao.addTeacherAttendance(obj);
	}

	@Transactional
	public ArrayList<TeacherAttendance> getAttendance(Date date) {
		return teacherAttendanceDao.getAttendance(date);
	}

	@Transactional
	public List<TeacherAttendance> getAllAttendanceByTeacherId(int teacherId) {
		return teacherAttendanceDao.getAllAttendanceByTeacherId(teacherId);
	}
}
