package com.dbmsproject.ignite.tutorials.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dbmsproject.ignite.tutorials.model.Staff;

@Component
public interface StaffService {
		   
    public void addStaff(Staff staff);
 
    public List<Staff> getAllStaffs();
 
    public Staff getStaffByStaffId(int StaffId);
 
    public void updateStaff(Staff staff);
 
    public void deleteStaff(int StaffId);
	 
}

