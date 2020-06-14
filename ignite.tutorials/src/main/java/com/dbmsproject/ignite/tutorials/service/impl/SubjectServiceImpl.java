package com.dbmsproject.ignite.tutorials.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbmsproject.ignite.tutorials.dao.SubjectDao;
import com.dbmsproject.ignite.tutorials.model.Subject;
import com.dbmsproject.ignite.tutorials.service.SubjectService;

@Component
public class SubjectServiceImpl implements SubjectService{
	@Autowired
	private SubjectDao subjectDao;
	
	@Transactional
	public void addSubject(Subject subject) {
		subjectDao.addSubject(subject);
	}

	@Transactional
	public List<Subject> getSubjects(String courseId) {
		return subjectDao.getSubjects(courseId);
	}

	@Transactional
	public void deleteSubject(String courseId, String subjectName) {
		subjectDao.deleteSubject(courseId, subjectName);
	}
}
