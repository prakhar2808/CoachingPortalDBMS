package com.dbmsproject.ignite.tutorials.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dbmsproject.ignite.tutorials.model.Guardian;

@Component
public interface GuardianService {
		   
    public void addGuardian(Guardian guardian);
 
    public List<Guardian> getAllGuardians();
 
    public Guardian getGuardianByGuardianId(int GuardianId);
 
    public void updateGuardian(Guardian guardian);
 
    public void deleteGuardian(int GuardianId);
	 
}

