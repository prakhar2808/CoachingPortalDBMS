package com.dbmsproject.ignite.tutorials.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.BatchDao;
import com.dbmsproject.ignite.tutorials.model.Batch;
import com.dbmsproject.ignite.tutorials.model.Course;

@Component
public class BatchDaoImpl implements BatchDao{
	
	@Autowired
	private SessionFactory sessionFactory;
    
    @Transactional
    public void addBatch(Batch batch) {
        Session session = sessionFactory.getCurrentSession();
        session.createNativeQuery("insert into batch values(:BatchId, :CourseId, :RoomNo, :StartTiming, :EndTiming)")
        .setParameter("CourseId", batch.getCourseId())
        .setParameter("BatchId", batch.getBatchId())
        .setParameter("RoomNo", batch.getRoomNo())
        .setParameter("StartTiming", batch.getStartTiming())
        .setParameter("EndTiming", batch.getEndTiming())
        .executeUpdate();
    }
    
    @SuppressWarnings("unchecked")
	@Transactional
	public List<Batch> getAllBatchesByCourseId(String courseId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Batch> query = session.createNativeQuery("select * from batch where CourseId=:CourseId",Batch.class)
		.setParameter("CourseId", courseId);
    	List<Batch> result = (List<Batch>)query.getResultList();
    	return result;
	}
    
    @SuppressWarnings("unchecked")
	@Transactional
	public List<Batch> getAllBatches() {
		Session session = sessionFactory.getCurrentSession();
		Query<Batch> query = session.createNativeQuery("select * from batch",Batch.class);
    	List<Batch> result = (List<Batch>)query.getResultList();
    	return result;
	}

	@Override
	public void deleteBatch(int batchId, String courseId) {
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery("delete from batch where CourseId=:courseId and BatchId=:batchId")
		.setParameter("courseId", courseId)
		.setParameter("batchId", batchId)
		.executeUpdate();
	}

	@Override
	public Batch getBatchByBatchIdAndCourseId(int batchId, String courseId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Batch> query = session.createNativeQuery("select * from batch where batchId=:batchId and courseId=:courseId", Batch.class)
				.setParameter("courseId", courseId)
				.setParameter("batchId", batchId);
		
		Batch result = query.getSingleResult();
		return result;
	}

	@Override
	public void updateBatch(Batch batch) {
		sessionFactory.getCurrentSession().update(batch);
	}
    
    
}
