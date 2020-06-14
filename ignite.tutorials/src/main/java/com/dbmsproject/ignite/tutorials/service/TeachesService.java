package com.dbmsproject.ignite.tutorials.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dbmsproject.ignite.tutorials.model.Teaches;

@Component
public interface TeachesService {
	public void addTeaches(Teaches teaches);

	public List<Teaches> getTeachers(String courseId, int batchId);

	public void deleteTeaches(String courseId, int teacherId, int batchId);

	public List<Teaches> getCourseAndBatch(int teacherId);
}
