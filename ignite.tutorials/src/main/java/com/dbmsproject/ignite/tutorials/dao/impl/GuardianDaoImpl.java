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

import com.dbmsproject.ignite.tutorials.dao.GuardianDao;
import com.dbmsproject.ignite.tutorials.model.Guardian;

@Component
public class GuardianDaoImpl implements GuardianDao{
	@Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    // Create of CRUD
    public void addGuardian(Guardian Guardian) {

    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	Guardian.setPassword(encoder.encode(Guardian.getPassword()));
        sessionFactory.getCurrentSession().save(Guardian);
    }
    
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Guardian> getAllGuardians() {
		@SuppressWarnings("deprecation")
		Query<Guardian> query = getSession().createNativeQuery("select * from guardian").addEntity(Guardian.class);
    	List<Guardian> result = (List<Guardian>)(query.getResultList());
    	return result;
    }
    
    @SuppressWarnings("unchecked")
	@Transactional
    public Guardian getGuardianByGuardianId(int GuardianId) {
    	Guardian result = null;
    	@SuppressWarnings("deprecation")
		Query<Guardian> query = getSession().createNativeQuery("select * from guardian where GuardianId=:GuardianId").addEntity(Guardian.class);
    	query.setParameter("GuardianId", GuardianId);
    	result = (Guardian) query.getSingleResult();
        return result;
    }
    
    @Transactional
    public void updateGuardian(Guardian Guardian) {
        sessionFactory.getCurrentSession().update(Guardian);
    }
    
    @Transactional
    // Delete of CRUD
    public void deleteGuardian(int GuardianId) {
    	getSession().createNativeQuery("delete from guardian where GuardianId = :GuardianId").setParameter("GuardianId", GuardianId).executeUpdate();
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

