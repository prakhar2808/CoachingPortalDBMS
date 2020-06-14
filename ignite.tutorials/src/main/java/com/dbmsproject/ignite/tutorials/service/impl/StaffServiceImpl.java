package com.dbmsproject.ignite.tutorials.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.service.StaffService;
import com.dbmsproject.ignite.tutorials.dao.StaffDao;
import com.dbmsproject.ignite.tutorials.model.Staff;

@Component
public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffDao StaffDao;
	
	// Create of CRUD
	@Transactional
	public void addStaff(Staff staff) {
	    StaffDao.addStaff(staff);
	}
	
	// Read of CRUD
	@Transactional
	public List<Staff> getAllStaffs() {
	    return StaffDao.getAllStaffs();
	}
	
	// Read of CRUD
	@Transactional
	public Staff getStaffByStaffId(int StaffId) {
	    return StaffDao.getStaffByStaffId(StaffId);
	}
	
	// Update of CRUD
	@Transactional
	public void updateStaff(Staff staff) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(staff == null)
			return;
		Staff updated_staff = StaffDao.getStaffByStaffId(staff.getStaffId());
		updated_staff.setPassword(encoder.encode(staff.getPassword()));
		updated_staff.setFirstName(staff.getFirstName());
		updated_staff.setLastName(staff.getLastName());
		updated_staff.setGender(staff.getGender());
		updated_staff.setDob(staff.getDob());
		updated_staff.setHouseNo(staff.getHouseNo());
		updated_staff.setStreet(staff.getStreet());
		updated_staff.setCity(staff.getCity());
		updated_staff.setJob(staff.getJob());
	    StaffDao.updateStaff(updated_staff);
	}
	
	// Delete of CRUD
	@Transactional
	public void deleteStaff(int StaffId) {
	    StaffDao.deleteStaff(StaffId);
	}
	
	public StaffDao getStaffDao() {
	    return StaffDao;
	}

	@Autowired(required=true)
	public void setStaffDao(StaffDao StaffDao) {
	    this.StaffDao = StaffDao;
	}
}
