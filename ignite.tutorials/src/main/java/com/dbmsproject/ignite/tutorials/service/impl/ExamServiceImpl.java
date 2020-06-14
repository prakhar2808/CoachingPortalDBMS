package com.dbmsproject.ignite.tutorials.service.impl;
import com.dbmsproject.ignite.tutorials.dao.ExamDao;
import com.dbmsproject.ignite.tutorials.model.Exam;
import com.dbmsproject.ignite.tutorials.service.ExamService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ExamServiceImpl implements ExamService{
	
	@Autowired
	ExamDao examDao;
	
	@Transactional
	public void addExam(Exam exam) {
		examDao.addExam(exam);
	}
	
	@Transactional
	public List<Exam> getAllExamsByCourseId(String courseId){
		return examDao.getAllExamsByCourseId(courseId);
	}

	@Transactional
	public void deleteExam(String examId, String courseId) {
		examDao.deleteExam(examId,courseId);
	}

	@Transactional
	public Exam getExam(String examId, String courseId) {
		return examDao.getExam(examId, courseId);
	}

	@Transactional
	public void updateExam(Exam exam) {
		examDao.updateExam(exam);
	}

	@Transactional
	public List<Exam> getAllExams() {
		return examDao.getAllExams();
	}

}
