package com.dbmsproject.ignite.tutorials.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.query.QueryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.model.Student;
import com.dbmsproject.ignite.tutorials.dao.StudentDao;

@Component
public class StudentDaoImpl implements StudentDao{
	@Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    // Create of CRUD
    public void addStudent(Student Student) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	Student.setPassword(encoder.encode(Student.getPassword()));
        sessionFactory.getCurrentSession().save(Student);
    }
    
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Student> getAllStudents() {
		@SuppressWarnings("deprecation")
		Query<Student> query = getSession().createNativeQuery("select * from student").addEntity(Student.class);
    	List<Student> result = (List<Student>)(query.getResultList());
    	return result;
    }
    
    @SuppressWarnings("unchecked")
	@Transactional
    public Student getStudentByRollNo(int RollNo) {
    	Student result = null;
    	@SuppressWarnings("deprecation")
		Query<Student> query = getSession().createNativeQuery("select * from student where RollNo=:RollNo").addEntity(Student.class);
    	query.setParameter("RollNo", RollNo);
    	result = (Student) query.getSingleResult();
        return result;
    }
    
    @Transactional
    public void updateStudent(Student Student) {
        sessionFactory.getCurrentSession().update(Student);
    }
    
    @Transactional
    // Delete of CRUD
    public void deleteStudent(int RollNo) {
    	getSession().createNativeQuery("delete from student where RollNo = :RollNo").setParameter("RollNo", RollNo).executeUpdate();
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

	@Transactional
	public List<Student> getStudentByGuardianId(int guardianId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Student> query = session.createNativeQuery("select * from student where guardianId=:guardianId",Student.class)
				.setParameter("guardianId", guardianId);
		List<Student> result = query.getResultList();
		return result;
	}
}
