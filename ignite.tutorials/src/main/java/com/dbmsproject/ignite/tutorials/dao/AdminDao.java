package com.dbmsproject.ignite.tutorials.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.dbmsproject.ignite.tutorials.model.Admin;

@Component
public interface AdminDao {
	
	public void addAdmin(Admin admin);
	 
    public List<Admin> getAllAdmins();
	 
    public Admin getAdminByAdminId(int AdminId);
	 
    public void updateAdmin(Admin admin);
	 
    public void deleteAdmin(int AdminId);
}
