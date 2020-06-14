package com.dbmsproject.ignite.tutorials.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dbmsproject.ignite.tutorials.model.Exam;

@Component
public interface ExamService {
	
	public void addExam(Exam exam);
	
	public List<Exam> getAllExamsByCourseId(String courseId);

	public void deleteExam(String examId, String courseId);

	public Exam getExam(String examId, String courseId);

	public void updateExam(Exam exam);

	public List<Exam> getAllExams();

}
