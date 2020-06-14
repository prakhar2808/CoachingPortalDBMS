package com.dbmsproject.ignite.tutorials.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.BatchDao;
import com.dbmsproject.ignite.tutorials.dao.CourseDao;
import com.dbmsproject.ignite.tutorials.model.Batch;
import com.dbmsproject.ignite.tutorials.model.Course;
import com.dbmsproject.ignite.tutorials.service.BatchService;

@Component
public class BatchServiceImpl implements BatchService{
	
	@Autowired
	private BatchDao batchDao;
	
	@Transactional
	public void addBatch(Batch batch) {
	    batchDao.addBatch(batch);
	}

	@Transactional
	public List<Batch> getAllBatchesByCourseId(String courseId) {
		return batchDao.getAllBatchesByCourseId(courseId);
	}
	
	@Transactional
	public List<Batch> getAllBatches(){
		return batchDao.getAllBatches();
	}

	@Transactional
	public void deleteBatch(int batchId, String courseId) {
		batchDao.deleteBatch(batchId,courseId);
		
	}

	@Transactional
	public Batch getBatchByBatchIdAndCourseId(int batchId, String courseId) {
		return batchDao.getBatchByBatchIdAndCourseId(batchId,courseId);
	}

	@Transactional
	public void updateBatch(Batch batch) {
		if(batch == null)
			return;
		Batch updated_batch = batchDao.getBatchByBatchIdAndCourseId(batch.getBatchId(),batch.getCourseId());
		updated_batch.setEndTiming(batch.getEndTiming());
		updated_batch.setStartTiming(batch.getStartTiming());
		updated_batch.setRoomNo(batch.getRoomNo());
		updated_batch.setRepresentation(batch.getRepresentation());
		batchDao.updateBatch(updated_batch);
	}
}
