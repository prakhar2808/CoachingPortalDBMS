package com.dbmsproject.ignite.tutorials.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.service.GuardianService;
import com.dbmsproject.ignite.tutorials.dao.GuardianDao;
import com.dbmsproject.ignite.tutorials.model.Guardian;

@Component
public class GuardianServiceImpl implements GuardianService{

	@Autowired
	private GuardianDao GuardianDao;
	
	// Create of CRUD
	@Transactional
	public void addGuardian(Guardian guardian) {
	    GuardianDao.addGuardian(guardian);
	}
	
	// Read of CRUD
	@Transactional
	public List<Guardian> getAllGuardians() {
	    return GuardianDao.getAllGuardians();
	}
	
	// Read of CRUD
	@Transactional
	public Guardian getGuardianByGuardianId(int GuardianId) {
	    return GuardianDao.getGuardianByGuardianId(GuardianId);
	}
	
	// Update of CRUD
	@Transactional
	public void updateGuardian(Guardian guardian) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(guardian == null)
			return;
		Guardian updated_guardian = GuardianDao.getGuardianByGuardianId(guardian.getGuardianId());
		updated_guardian.setPassword(encoder.encode(guardian.getPassword()));
		updated_guardian.setFirstName(guardian.getFirstName());
		updated_guardian.setLastName(guardian.getLastName());
		updated_guardian.setGender(guardian.getGender());
		updated_guardian.setDob(guardian.getDob());
		updated_guardian.setHouseNo(guardian.getHouseNo());
		updated_guardian.setStreet(guardian.getStreet());
		updated_guardian.setCity(guardian.getCity());
		updated_guardian.setOccupation(guardian.getOccupation());
	    GuardianDao.updateGuardian(updated_guardian);
	}
	
	// Delete of CRUD
	@Transactional
	public void deleteGuardian(int GuardianId) {
	    GuardianDao.deleteGuardian(GuardianId);
	}
	
	public GuardianDao getGuardianDao() {
	    return GuardianDao;
	}
	
	@Autowired(required=true)
	public void setGuardianDao(GuardianDao GuardianDao) {
	    this.GuardianDao = GuardianDao;
	}
}
