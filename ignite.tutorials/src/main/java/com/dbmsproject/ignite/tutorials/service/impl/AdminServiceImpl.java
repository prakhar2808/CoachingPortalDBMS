package com.dbmsproject.ignite.tutorials.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.service.AdminService;
import com.dbmsproject.ignite.tutorials.dao.AdminDao;
import com.dbmsproject.ignite.tutorials.model.Admin;

@Component
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao adminDao;
	
	// Create of CRUD
	@Transactional
	public void addAdmin(Admin admin) {
	    adminDao.addAdmin(admin);
	}
	
	// Read of CRUD
	@Transactional
	public List<Admin> getAllAdmins() {
	    return adminDao.getAllAdmins();
	}
	
	// Read of CRUD
	@Transactional
	public Admin getAdminByAdminId(int AdminId) {
	    return adminDao.getAdminByAdminId(AdminId);
	}
	
	// Update of CRUD
	@Transactional
	public void updateAdmin(Admin admin) {
		if(admin == null)
			return;
		Admin updated_admin = adminDao.getAdminByAdminId(admin.getAdminId());
		updated_admin.setPassword(admin.getPassword());
	    adminDao.updateAdmin(updated_admin);
	}
	
	// Delete of CRUD
	@Transactional
	public void deleteAdmin(int AdminId) {
	    adminDao.deleteAdmin(AdminId);
	}
	
	public AdminDao getAdminDao() {
	    return adminDao;
	}
	
	@Autowired(required=true)
	public void setAdminDao(AdminDao adminDao) {
	    this.adminDao = adminDao;
	}
}
