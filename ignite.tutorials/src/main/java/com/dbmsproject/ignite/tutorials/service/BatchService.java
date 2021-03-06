package com.dbmsproject.ignite.tutorials.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dbmsproject.ignite.tutorials.model.Batch;
import com.dbmsproject.ignite.tutorials.model.Course;

@Component
public interface BatchService {
	
	public void addBatch(Batch batch);
    
    public List<Batch> getAllBatchesByCourseId(String courseId);
    
    public List<Batch> getAllBatches();

	public void deleteBatch(int batchId, String courseId);

	public Batch getBatchByBatchIdAndCourseId(int batchId, String courseId);

	public void updateBatch(Batch batch);
}
