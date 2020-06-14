package com.dbmsproject.ignite.tutorials.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.dbmsproject.ignite.tutorials.model.Guardian;

@Component
public interface GuardianDao {
	
	public void addGuardian(Guardian guardian);
	 
    public List<Guardian> getAllGuardians();
	 
    public Guardian getGuardianByGuardianId(int GuardianId);
	 
    public void updateGuardian(Guardian guardian);
	 
    public void deleteGuardian(int GuardianId);
}
