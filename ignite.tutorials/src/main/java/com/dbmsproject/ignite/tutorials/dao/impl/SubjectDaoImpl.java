package com.dbmsproject.ignite.tutorials.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.SubjectDao;
import com.dbmsproject.ignite.tutorials.model.Subject;

@Component
public class SubjectDaoImpl implements SubjectDao{
	@Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    public void addSubject(Subject subject) {
        Session session = sessionFactory.getCurrentSession();
        session.createNativeQuery("insert into subjects values(:courseId, :subjectName)")
        .setParameter("courseId",subject.getCourseId())
        .setParameter("subjectName",subject.getSubjectName())
        .executeUpdate();
    }

	@Transactional
	public void deleteSubject(String courseId, String subjectName) {
		sessionFactory.getCurrentSession().createNativeQuery("delete from subjects where courseId=:courseId and subjectName=:subjectName")
		.setParameter("courseId", courseId)
		.setParameter("subjectName", subjectName)
		.executeUpdate();
	}

	@Transactional
	public List<Subject> getSubjects(String courseId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Subject> query = session.createNativeQuery("select * from subjects where courseId=:courseId", Subject.class)
				.setParameter("courseId", courseId);
		List<Subject> result = query.getResultList();
		return result;
	}
}
