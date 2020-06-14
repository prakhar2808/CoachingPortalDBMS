package com.dbmsproject.ignite.tutorials.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.StaffAttendanceDao;
import com.dbmsproject.ignite.tutorials.model.StaffAttendance;
import com.dbmsproject.ignite.tutorials.service.StaffAttendanceService;


@Component
public class StaffAttendanceServiceImpl implements StaffAttendanceService{
	
	@Autowired
	StaffAttendanceDao staffAttendanceDao;
	
	@Transactional
	public void addStaffAttendance(StaffAttendance obj) {
		staffAttendanceDao.addStaffAttendance(obj);
	}

	@Transactional
	public ArrayList<StaffAttendance> getAttendance(Date date) {
		return staffAttendanceDao.getAttendance(date);
	}

	@Transactional
	public List<StaffAttendance> getAllAttendanceByStaffId(int staffId) {
		return staffAttendanceDao.getAllAttendanceByStaffId(staffId);
	}
}
