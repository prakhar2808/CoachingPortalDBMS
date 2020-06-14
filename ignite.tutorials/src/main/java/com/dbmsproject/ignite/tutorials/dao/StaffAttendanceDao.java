package com.dbmsproject.ignite.tutorials.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dbmsproject.ignite.tutorials.model.StaffAttendance;


@Component
public interface StaffAttendanceDao {
	public void addStaffAttendance(StaffAttendance obj);

	public ArrayList<StaffAttendance> getAttendance(Date date);

	public List<StaffAttendance> getAllAttendanceByStaffId(int staffId);
}
