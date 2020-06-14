package com.dbmsproject.ignite.tutorials.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.TeachesDao;
import com.dbmsproject.ignite.tutorials.model.Teaches;

@Component
public class TeachesDaoImpl implements TeachesDao{
	@Autowired
	private SessionFactory sessionFactory;
    
    @Transactional
    public void addTeaches(Teaches teaches) {
        Session session= sessionFactory.getCurrentSession();
        session.createNativeQuery("insert into teaches values(:BatchId,:CourseId,:TeacherId)")
        .setParameter("BatchId", teaches.getBatchId())
        .setParameter("CourseId", teaches.getCourseId())
        .setParameter("TeacherId", teaches.getTeacherId())
        .executeUpdate();
    }

	@Transactional
	public List<Teaches> getTeachers(String courseId, int batchId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Teaches> query = session.createNativeQuery("select * from teaches where courseId=:courseId and batchId=:batchId",Teaches.class)
				.setParameter("courseId", courseId)
				.setParameter("batchId", batchId);
		List<Teaches> result = query.getResultList();
		return result;
	}

	@Transactional
	public void deleteTeaches(String courseId, int teacherId, int batchId) {
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery("delete from teaches where courseId=:courseId and teacherId=:teacherId and batchId=:batchId")
		.setParameter("courseId",courseId)
		.setParameter("teacherId", teacherId)
		.setParameter("batchId", batchId)
		.executeUpdate();
	}

	@Override
	public List<Teaches> getCourseAndBatch(int teacherId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Teaches> query = session.createNativeQuery("select * from teaches where teacherId=:teacherId",Teaches.class)
		.setParameter("teacherId", teacherId);
		List<Teaches> result = query.getResultList();
		return result;
	}
}
