package com.dbmsproject.ignite.tutorials.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dbmsproject.ignite.tutorials.model.TeacherAttendance;

@Component
public interface TeacherAttendanceDao {
	public void addTeacherAttendance(TeacherAttendance obj);

	public ArrayList<TeacherAttendance> getAttendance(Date date);

	public List<TeacherAttendance> getAllAttendanceByTeacherId(int teacherId);
}
