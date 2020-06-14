package com.dbmsproject.ignite.tutorials.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.query.QueryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.StaffDao;
import com.dbmsproject.ignite.tutorials.model.Staff;

@Component
public class StaffDaoImpl implements StaffDao{
	@Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    // Create of CRUD
    public void addStaff(Staff Staff) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	Staff.setPassword(encoder.encode(Staff.getPassword()));
        sessionFactory.getCurrentSession().save(Staff);
    }
    
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Staff> getAllStaffs() {
		@SuppressWarnings("deprecation")
		Query<Staff> query = getSession().createNativeQuery("select * from staff").addEntity(Staff.class);
    	List<Staff> result = (List<Staff>)(query.getResultList());
    	return result;
    }
    
    @SuppressWarnings("unchecked")
	@Transactional
    public Staff getStaffByStaffId(int StaffId) {
    	Staff result = null;
    	@SuppressWarnings("deprecation")
		Query<Staff> query = getSession().createNativeQuery("select * from staff where StaffId=:StaffId").addEntity(Staff.class);
    	query.setParameter("StaffId", StaffId);
    	result = (Staff) query.getSingleResult();
        return result;
    }
    
    @Transactional
    public void updateStaff(Staff Staff) {
        sessionFactory.getCurrentSession().update(Staff);
    }
    
    @Transactional
    // Delete of CRUD
    public void deleteStaff(int StaffId) {
    	getSession().createNativeQuery("delete from staff where StaffId = :StaffId").setParameter("StaffId", StaffId).executeUpdate();
    }
    
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public QueryProducer getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
