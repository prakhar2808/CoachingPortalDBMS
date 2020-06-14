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

import com.dbmsproject.ignite.tutorials.dao.AdminDao;
import com.dbmsproject.ignite.tutorials.model.Admin;

@Component
public class AdminDaoImpl implements AdminDao{
	@Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    // Create of CRUD
    public void addAdmin(Admin Admin) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	Admin.setPassword(encoder.encode(Admin.getPassword()));
        sessionFactory.getCurrentSession().save(Admin);
    }
    
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Admin> getAllAdmins() {
		@SuppressWarnings("deprecation")
		Query<Admin> query = getSession().createNativeQuery("select * from admin").addEntity(Admin.class);
    	List<Admin> result = (List<Admin>)(query.getResultList());
    	return result;
    }
    
    @SuppressWarnings("unchecked")
	@Transactional
    public Admin getAdminByAdminId(int AdminId) {
    	Admin result = null;
    	@SuppressWarnings("deprecation")
		Query<Admin> query = getSession().createNativeQuery("select * from Admin where AdminId=:AdminId").addEntity(Admin.class);
    	query.setParameter("AdminId", AdminId);
    	result = (Admin) query.getSingleResult();
    	System.out.println(result);
        return result;
    }
    
    @Transactional
    public void updateAdmin(Admin Admin) {
        sessionFactory.getCurrentSession().update(Admin);
    }
    
    @Transactional
    // Delete of CRUD
    public void deleteAdmin(int AdminId) {
    	getSession().createNativeQuery("delete from admin where AdminId = :AdminId").setParameter("AdminId", AdminId).executeUpdate();
    }
    
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public QueryProducer getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Autowired(required=true)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}

