package com.dbmsproject.ignite.tutorials.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.TeachesDao;
import com.dbmsproject.ignite.tutorials.model.Teaches;
import com.dbmsproject.ignite.tutorials.service.TeachesService;

@Component
public class TeachesServiceImpl implements TeachesService{
	
	@Autowired
	private TeachesDao teachesDao;
	
	@Transactional
	public void addTeaches(Teaches teaches) {
		teachesDao.addTeaches(teaches);
	}

	@Transactional
	public List<Teaches> getTeachers(String courseId, int batchId) {
		return teachesDao.getTeachers(courseId,batchId);
	}

	@Transactional
	public void deleteTeaches(String courseId, int teacherId, int batchId) {
		teachesDao.deleteTeaches(courseId,teacherId,batchId);
	}

	@Transactional
	public List<Teaches> getCourseAndBatch(int teacherId) {
		return teachesDao.getCourseAndBatch(teacherId);
	}

}
