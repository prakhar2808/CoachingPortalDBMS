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

import com.dbmsproject.ignite.tutorials.dao.TeacherDao;
import com.dbmsproject.ignite.tutorials.model.Teacher;

@Component
public class TeacherDaoImpl implements TeacherDao{
	@Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    // Create of CRUD
    public void addTeacher(Teacher Teacher) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	Teacher.setPassword(encoder.encode(Teacher.getPassword()));
        sessionFactory.getCurrentSession().save(Teacher);
    }
    
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Teacher> getAllTeachers() {
		@SuppressWarnings("deprecation")
		Query<Teacher> query = getSession().createNativeQuery("select * from teacher").addEntity(Teacher.class);
    	List<Teacher> result = (List<Teacher>)(query.getResultList());
    	return result;
    }
    
    @SuppressWarnings("unchecked")
	@Transactional
    public Teacher getTeacherByTeacherId(int TeacherId) {
    	Teacher result = null;
    	@SuppressWarnings("deprecation")
		Query<Teacher> query = getSession().createNativeQuery("select * from teacher where TeacherId=:TeacherId").addEntity(Teacher.class);
    	query.setParameter("TeacherId", TeacherId);
    	result = (Teacher) query.getSingleResult();
        return result;
    }
    
    @Transactional
    public void updateTeacher(Teacher Teacher) {
        sessionFactory.getCurrentSession().update(Teacher);
    }
    
    @Transactional
    // Delete of CRUD
    public void deleteTeacher(int TeacherId) {
    	getSession().createNativeQuery("delete from teacher where TeacherId = :TeacherId").setParameter("TeacherId", TeacherId).executeUpdate();
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
